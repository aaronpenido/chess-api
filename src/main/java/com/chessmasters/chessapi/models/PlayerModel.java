package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PlayerEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerModel {

    private Long id;
    private String name;

    public PlayerModel(@JsonProperty("player") PlayerEntity player) {
        this.id = player.getId();
        this.name = player.getName();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
