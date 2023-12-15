package com.ryu.common.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * BaseEntity
 */
// @Data
// @MappedSuperclass
// @EntityListeners(AuditingEntityListener.class)
@SuperBuilder
@NoArgsConstructor
@Getter
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @CreatedDate
    @Column(name = "created_at")
    protected ZonedDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    protected ZonedDateTime updatedAt;

    @Column
    protected boolean isDeleted = false;
}