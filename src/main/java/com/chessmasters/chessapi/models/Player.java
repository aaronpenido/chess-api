package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.Color;

public class Player {

    private Long id;
    private String name;
    private Color color;

    public Player(PlayerEntity playerEntity) {
        if(playerEntity != null) {
            this.id = playerEntity.getId();
            this.name = playerEntity.getName();
            this.color = playerEntity.getColor();
        }
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

    public void setColor(Color color) {
        this.color = color;
    }
}
