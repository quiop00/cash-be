package com.ryu.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
public class OfferwallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    @Column
    private String offerPublisherId;

    @Column
    private String offerAppId;

    @Column
    private String offerKey;

    @Column
    private String offerPostbackUrl;

    @Column
    private String adGetMediaCode;

    @Column
    private String adGetMediaPostbackUrl;

    @Column
    private String ayetPostbackUrl;

}
