package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.Game;

public class GameResponse {

    private final Long id;

    public GameResponse(Game game) {
        this.id = game.getId();
    }

    public Long getId() {
        return id;
    }
}
