package com.ryu.common.entity;

import java.util.List;
import java.util.Set;

import com.ryu.common.enums.ERole;
import com.ryu.common.enums.EStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class UserEntity extends BaseEntity {
    @Column(name = "username",nullable = false)
    private String username;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String image;

    @Column
    private String otp;

    @Column
    @Enumerated(EnumType.STRING)
    private ERole role;

    @Column
    @Enumerated(EnumType.STRING)
    private EStatus status;

    @Column
    private int refer;

    @Column
    private long currentPoint;

    @Column
    private String referCode;

    @Column
    private long referUser;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private GameHistory history;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private PaymentDetail paymentInfo;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PaymentRequest> paymentRequests;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<TrackingEntity> track;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserNotification> notifications;

    @OneToMany(mappedBy = "user")
    private Set<UserRefer> referQuests;
}
