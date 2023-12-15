package com.ryu.tobybe.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.PaymentDetail;
import com.ryu.common.entity.UserEntity;
import com.ryu.common.enums.EStatus;
import com.ryu.common.repository.UserRepository;
import com.ryu.common.sercurity.CustomPasswordEncoder;
import com.ryu.tobybe.models.PasswordRequest;
import com.ryu.tobybe.models.PaymentInfo;
import com.ryu.tobybe.models.UserDto;
import com.ryu.tobybe.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomPasswordEncoder passwordEncoder;

    @Override
    public String updateStatus(long id) throws Exception {
        UserEntity user = userRepository.findById(id).get();

        if (user == null) {
            throw new Exception();
        }

        user.setStatus(user.getStatus() == EStatus.ACTIVE ? EStatus.BLOCKED : EStatus.ACTIVE);
        user = userRepository.save(user);

        return user.getStatus().name();
    }

    @Override
    public List<UserDto> getVerifyUsers() {
        return userRepository.findAllByStatusIn(Arrays.asList(EStatus.ACTIVE, EStatus.BLOCKED)).stream()
                .map(e -> covertEntityToDto(e)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUser(long id) throws Exception {
        Optional<UserEntity> user = userRepository.findById(id);
        if (!user.isPresent()) {
            throw new Exception("USER NOT FOUND");
        }
        return covertEntityToDto(user.get());
    }

    // TODO FIX NOT FOUND BY ID
    @Override
    public PaymentInfo getPaymentInfo(long id) {
        UserEntity user = userRepository.findById(id).get();
        PaymentDetail payment = user.getPaymentInfo();

        if (payment == null) {
            return PaymentInfo
                    .builder()
                    .userId(id)
                    .build();
        }

        return PaymentInfo
                .builder()
                .userId(id)
                .fullname(payment.getFullname())
                .value(payment.getNumber())
                .method(payment.getMethod())
                .build();
    }

    @Override
    public PaymentInfo updatePaymentInfo(PaymentInfo data) throws Exception {
        Long id = data.getUserId();

        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            throw new Exception("USER NOT FOUND");
        }

        PaymentDetail paymentDetail = userEntity.getPaymentInfo();
        if (paymentDetail == null) {
            paymentDetail = new PaymentDetail();
            paymentDetail.setUser(userEntity);
        }
        paymentDetail.setFullname(data.getFullname());
        paymentDetail.setNumber(data.getValue());
        paymentDetail.setMethod(data.getMethod());

        userEntity.setPaymentInfo(paymentDetail);

        userRepository.save(userEntity);

        return data;
    }

    @Override
    public boolean changePassword(long userId, PasswordRequest data) {
        UserEntity user = userRepository.findById(userId).get();
        if (user != null) {
            if (passwordEncoder.matches(data.getOldPassword(), user.getPassword())) {
                user.setPassword(passwordEncoder.encode(data.getNewPassword()));
                userRepository.save(user);
                return true;
            }
        }
        return false;
    }

    private UserDto covertEntityToDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .username(entity.getUsername())
                .email(entity.getEmail())
                .refer(entity.getRefer())
                .status(entity.getStatus())
                .image(entity.getImage())
                .point(entity.getCurrentPoint())
                .referCode(entity.getReferCode())
                .referUser(entity.getReferUser())
                .build();
    }

}
