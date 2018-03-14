package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.models.PlayerModel;

public class PlayerResponse {

    private final String name;
    private final Long id;

    public PlayerResponse(PlayerModel playerModel) {
        this.id = playerModel.getId();
        this.name = playerModel.getName();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}