package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private Long id;
    private GameStatus status;
    private Player player;
    private Player player2;
    private List<Piece> pieces;

    public Game(@JsonProperty("gameEntity") GameEntity gameEntity) {
        this.id = gameEntity.getId();
        this.status = gameEntity.getStatus();
        this.player = new Player(gameEntity.getPlayer());
        this.player2 = new Player(gameEntity.getPlayer2());

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

    public Player getPlayer() {
        return player;
    }

    public Player getPlayer2() {
        return player2;
    }

    public List<Piece> getPieces() {
        return pieces;
    }
}
