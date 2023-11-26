package com.ryu.common.entity;

import java.io.Serializable;
import java.time.ZonedDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.*;
import lombok.Data;

/**
 * BaseEntity
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
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