package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.GameStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {

    private Long id;
    private GameStatus status;
    private Player player;
    private Player player2;
    private List<Piece> pieces;
    private GameEntity gameEntity;

    public Game(@JsonProperty("gameEntity") GameEntity gameEntity) {
        this.gameEntity = gameEntity;
        this.id = gameEntity.getId();
        this.status = gameEntity.getStatus();
        this.player = new Player(gameEntity.getPlayer());
        this.player2 = new Player(gameEntity.getPlayer2());

        mapPieceEntityListToPieceList();
    }

    public void start(PlayerEntity player2) {
        this.status = GameStatus.STARTED;
        this.player2 = new Player(player2);

        setPlayersColors();
        updateGameEntity(player2);
        updatePieces();
    }

    private void mapPieceEntityListToPieceList() {
        if(gameEntity.getPieces() != null) {
            this.pieces = gameEntity.getPieces()
                    .stream()
                    .map(Pawn::new)
                    .collect(Collectors.toList());
        }
    }

    private void setPlayersColors() {
        player.setColor(getRandomColor());
        player2.setColor(player.getColor().opposite());
    }

    private void updateGameEntity(PlayerEntity player2) {
        gameEntity.getPlayer().setColor(this.player.getColor());
        gameEntity.setPlayer2(player2);
        gameEntity.getPlayer2().setColor(this.player2.getColor());
        gameEntity.setStatus(this.status);
    }

    private Color getRandomColor() {
        final int randomNumber = new Random().nextInt(1);
        return Color.values()[randomNumber];
    }

    private void updatePieces() {
        BoardInitializer boardInitializer = new BoardInitializer(gameEntity);
        boardInitializer.initialize();
        mapPieceEntityListToPieceList();
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
