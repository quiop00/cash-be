package com.ryu.tobybe.services.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ryu.common.entity.PaymentDetail;
import com.ryu.common.entity.UserEntity;
import com.ryu.common.enums.EStatus;
import com.ryu.tobybe.models.PaymentInfo;
import com.ryu.tobybe.models.UserDto;
import com.ryu.tobybe.repositories.UserRepository;
import com.ryu.tobybe.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean updateStatus(long id, boolean isBlock) throws Exception {
        UserEntity user = userRepository.findById(id).get();

        if (user == null) {
            throw new Exception();
        }

        user.setStatus(isBlock && user.getStatus() == EStatus.ACTIVE ? EStatus.BLOCKED : EStatus.ACTIVE);
        return true;
    }

    @Override
    public List<UserDto> getVerifyUsers() {
        return userRepository.findAllByStatusIn(Arrays.asList(EStatus.ACTIVE, EStatus.BLOCKED)).stream()
                .map(e -> covertEntityToDto(e)).collect(Collectors.toList());
    }

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

    private UserDto covertEntityToDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
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
