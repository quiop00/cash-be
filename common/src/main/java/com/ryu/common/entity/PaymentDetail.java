package com.ryu.common.entity;

import com.ryu.common.enums.PaymentMethod;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class PaymentDetail extends BaseEntity {

    @Column
    private String fullname;

    @Column
    private String number;

    @Column
    @Enumerated(EnumType.STRING)
    private PaymentMethod method;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

}
