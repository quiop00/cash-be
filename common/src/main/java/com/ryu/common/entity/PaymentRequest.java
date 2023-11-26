package com.ryu.common.entity;

import com.ryu.common.enums.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper=false)
public class PaymentRequest extends BaseEntity {

    @Column
    private long amount;
    
    @Column
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column
    private String message;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
}
