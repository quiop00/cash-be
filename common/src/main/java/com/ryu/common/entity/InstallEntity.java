package com.ryu.common.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class InstallEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    
    private String name;

    private String url;

    private String logo;

    private String point;
}
