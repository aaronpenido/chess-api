package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.Game;
import com.chessmasters.chessapi.models.Piece;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GameResponse {

    private Long id;
    private GameStatus status;
    private Long playerId;
    private Long player2Id;
    private List<Piece> pieces;

    public GameResponse(@JsonProperty("game") Game game) {
        this.id = game.getId();
        this.status = game.getStatus();
        this.playerId = game.getPlayer().getId();
        if(game.getPlayer2() != null) {
            this.player2Id = game.getPlayer2().getId();
        }
        this.pieces = game.getPieces();
    }

    public Long getId() {
        return id;
    }

    public GameStatus getStatus() {
        return status;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public Long getPlayer2Id() {
        return player2Id;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
