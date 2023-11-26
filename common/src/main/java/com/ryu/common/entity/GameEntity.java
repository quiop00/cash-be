package com.ryu.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;

    @Column
    private String name;

    @Column
    private String imgUrl;

    @Column
    private String gameUrl;

    @Column
    private String gameTime;

    @Column
    private int point;
}
