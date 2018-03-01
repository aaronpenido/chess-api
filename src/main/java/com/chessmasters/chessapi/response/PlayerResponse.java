package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.Player;

public class PlayerResponse {

    private final String name;
    private final Long id;

    public PlayerResponse(Player player) {
        this.id = player.getId();
        this.name = player.getName();
    }

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
