package com.ryu.auth.services.impl;

import java.time.Duration;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.ryu.auth.models.Login;
import com.ryu.auth.models.Registration;
import com.ryu.auth.repositories.UserRepository;
import com.ryu.auth.services.AuthService;
import com.ryu.common.entity.UserEntity;
import com.ryu.common.enums.ERole;
import com.ryu.common.enums.EStatus;
import com.ryu.common.exception.AppError;
import com.ryu.common.exception.AppException;
import com.ryu.common.models.RequestOtp;
import com.ryu.common.sercurity.AuthUserDetail;
import com.ryu.common.sercurity.CustomPasswordEncoder;
import com.ryu.common.utils.DataUtils;
import com.ryu.common.utils.JsonConverter;
import com.ryu.common.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final CustomPasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final RedisTemplate<String, String> redisTemplate;
    private final KafkaTemplate<String, Object> mailKafkaTemplate;

    @Override
    public String login(Login login) {
        UserEntity user = userRepository.findAllByEmailAndStatus(login.getEmail(), EStatus.ACTIVE).stream()
                .filter(userData -> passwordEncoder.matches(login.getPassword(), userData.getPassword())).findFirst()
                .orElseThrow(() -> new AppException(AppError.LOGIN_INFO_INVALID));
        String jwt = jwtUtils.encode(user.getEmail());

        AuthUserDetail userDetail = AuthUserDetail.builder()
                .id(user.getId())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
        String jsonStr = JsonConverter.serializeObject(userDetail);
        redisTemplate.opsForValue().set(jwt, jsonStr, Duration.ofSeconds(jwtUtils.getValidSeconds()));

        return jwt;
    }

    @Override
    public boolean register(Registration registration) {
        userRepository.findByEmail(registration.getEmail()).stream().findAny().ifPresent(entity -> {
            throw new AppException(AppError.DUPLICATED_USER);
        });
        userRepository.findByUsername(registration.getUsername()).stream().findAny().ifPresent(entity -> {
            throw new AppException(AppError.DUPLICATED_USER);
        });

        UserEntity referUser;
        long referId = -1; 
        if (StringUtils.isNotBlank(registration.getReferCode())) {
            referUser = userRepository.findByReferCode(registration.getReferCode());

            if (referUser != null) {
                referId = referUser.getId();
            }
        }

        UserEntity userEntity = UserEntity.builder()
                .username(registration.getUsername())
                .email(registration.getEmail())
                .password(passwordEncoder.encode(registration.getPassword()))
                .role(ERole.ROLE_USER)
                .image("")
                .currentPoint(0)
                .referUser(referId)
                .status(EStatus.INACTIVE)
                .build();
        String otp = DataUtils.generateOtp();
        userEntity.setOtp(otp);

        userEntity = userRepository.save(userEntity);
        // Send email to confirm
        RequestOtp data = RequestOtp.builder().email(userEntity.getEmail()).otp(otp).build();
        // TODO Create constants for TOPIC
        mailKafkaTemplate.send("verify-email", JsonConverter.serializeObject(data));
        // TODO 

        return true;
    }

    @Override
    public boolean verifyAccount(RequestOtp data) {
        UserEntity user = userRepository.findByEmail(data.getEmail()).stream().findFirst().orElse(null);
        if (user != null 
            && StringUtils.isNotEmpty(user.getOtp()) 
            && StringUtils.isNotEmpty(data.getOtp())) {
                if (user.getOtp().equals(data.getOtp())) {
                    user.setStatus(EStatus.ACTIVE);
                    userRepository.save(user);
                    // TODO send email success

                    if (user.getReferUser() != -1) {
                        // TODO call api update refer
                    }

                    return true;
                }
                return false;
        }

        return false;
    }

}
