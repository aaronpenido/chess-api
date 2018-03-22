package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PlayerEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private Long id;
    private String name;

    public Player(@JsonProperty("playerEntity") PlayerEntity playerEntity) {
        this.id = playerEntity.getId();
        this.name = playerEntity.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
