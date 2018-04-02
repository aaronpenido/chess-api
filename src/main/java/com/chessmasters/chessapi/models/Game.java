package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.ErrorMessage;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.InvalidMoveException;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

public class Game {

    private Long id;
    private GameStatus status;
    private Player player;
    private Player player2;
    private List<Piece> pieces;
    private GameEntity gameEntity;
    private List<Move> moves;

    public Game(@JsonProperty("gameEntity") GameEntity gameEntity) {
        this.gameEntity = gameEntity;
        this.id = gameEntity.getId();
        this.status = gameEntity.getStatus();
        this.player = new Player(gameEntity.getPlayer());
        this.player2 = new Player(gameEntity.getPlayer2());

        mapPieceEntityListToPieceList();
        mapMoveEntityListToMoveList();
    }

    public void start(PlayerEntity player2) {
        this.status = GameStatus.STARTED;
        this.player2 = new Player(player2);

        setPlayersColors();
        updateGameEntity(player2);
        initializePieces();
    }

    public Move move(Player player, Move move) {
        throwExceptionIfPlayerTriesToMoveOpponentsPiece(player, move.getPiece());
        throwExceptionIfMoveIsDoneSequentiallyByThePlayer(player);

        move.setOrder(getNextMoveOrder());
        move.getPiece().setSquare(move.getDestination());

        return move;
    }

    private void mapPieceEntityListToPieceList() {
        if(gameEntity.getPieces() != null) {
            this.pieces = gameEntity.getPieces()
                    .stream()
                    .map(Piece::new)
                    .collect(Collectors.toList());
        }
    }

    private void mapMoveEntityListToMoveList() {
        if(gameEntity.getMoves() != null) {
            this.moves = gameEntity.getMoves()
                    .stream()
                    .map(Move::new)
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

    private void initializePieces() {
        BoardInitializer boardInitializer = new BoardInitializer(gameEntity);
        boardInitializer.initialize();
        mapPieceEntityListToPieceList();
    }

    private void throwExceptionIfPlayerTriesToMoveOpponentsPiece(Player player, Piece piece) {
        if(!piece.getColor().equals(player.getColor())) {
            throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_PIECE));
        }
    }

    private void throwExceptionIfMoveIsDoneSequentiallyByThePlayer(Player player) {
        Move previousMove = previousMove();

        if(previousMove != null) {
            final boolean lastMoveColorIsEqualsToPlayerColor =
                    previousMove.getPiece().getColor().equals(player.getColor());

            if(lastMoveColorIsEqualsToPlayerColor) {
                throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
            }
        } else if (!player.getColor().equals(Color.WHITE)) {
            throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
        }
    }

    private Move previousMove() {
        Optional<Move> previousMove = moves
                .stream()
                .max(Comparator.comparing(Move::getOrder));

        return previousMove.orElse(null);
    }

    private int getNextMoveOrder() {
        Move previousMove = previousMove();

        if(previousMove != null) {
            return previousMove.getOrder() + 1;
        }

        return 1;
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

    public List<Move> getMoves() {
        return moves;
    }
}
