package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.Color;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Player {

    private Long id;
    private String name;
    private Color color;

    public Player(@JsonProperty("playerEntity") PlayerEntity playerEntity) {
        this.id = playerEntity.getId();
        this.name = playerEntity.getName();
        this.color = playerEntity.getColor();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}
