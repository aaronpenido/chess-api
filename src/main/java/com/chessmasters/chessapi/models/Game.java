package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private Long id;
    private GameStatus status;
    private Long playerId;
    private Long player2Id;
    private List<Piece> pieces;

    public Game(@JsonProperty("gameEntity") GameEntity gameEntity) {
        this.id = gameEntity.getId();
        this.status = gameEntity.getStatus();
        this.playerId = gameEntity.getPlayer().getId();

        if(gameEntity.getPlayer2() != null) {
            this.player2Id = gameEntity.getPlayer2().getId();
        }

        if(gameEntity.getPieces() != null) {
            this.pieces = gameEntity.getPieces()
                    .stream()
                    .map(Pawn::new)
                    .collect(Collectors.toList());
        }
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
