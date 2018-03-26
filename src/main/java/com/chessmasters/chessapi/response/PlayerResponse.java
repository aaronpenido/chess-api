package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.models.Player;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerResponse {

    private final Long id;
    private final String name;
    private final Color color;

    public PlayerResponse(@JsonProperty("player") Player player) {
        this.id = player.getId();
        this.name = player.getName();
        this.color = player.getColor();
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