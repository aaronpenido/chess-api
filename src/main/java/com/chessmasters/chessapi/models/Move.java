package com.chessmasters.chessapi.models;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.MoveEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.entities.SquareEntity;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.enums.ErrorMessage;
import com.chessmasters.chessapi.exceptions.InvalidMoveException;

import java.util.Comparator;
import java.util.Optional;

public class Move {

    private GameEntity gameEntity;
    private MoveEntity moveEntity;
    private Long gameId;
    private Piece piece;
    private Square destination;
    private int order;

    public Move(MoveEntity moveEntity) {
        this.moveEntity = moveEntity;

        if(moveEntity.getGame() != null) {
            this.gameEntity = moveEntity.getGame();
            this.gameId = gameEntity.getId();
            this.piece = new Piece(moveEntity.getPiece());
            this.order = moveEntity.getMoveOrder();
            this.destination = new Square(moveEntity.getDestination());
        }
    }

    public void movePiece(PlayerEntity playerEntity) {
        throwExceptionIfPlayerTriesToMoveOpponentsPiece(playerEntity);
        throwExceptionIfMoveIsDoneSequentiallyByThePlayer(playerEntity);

        order = getNextMoveOrder();
        piece.setSquare(destination);
        moveEntity.setMoveOrder(order);

        updatePieceSquare();
    }

    private void throwExceptionIfPlayerTriesToMoveOpponentsPiece(PlayerEntity playerEntity) {
        if(!piece.getColor().equals(playerEntity.getColor())) {
            throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_PIECE));
        }
    }

    private void throwExceptionIfMoveIsDoneSequentiallyByThePlayer(PlayerEntity playerEntity) {
        MoveEntity previousMove = previousMove();

        if(previousMove != null) {
            final boolean lastMoveColorIsEqualsToPlayerColor =
                    previousMove.getPiece().getColor().equals(playerEntity.getColor());

            if(lastMoveColorIsEqualsToPlayerColor) {
                throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
            }
        } else if (!playerEntity.getColor().equals(Color.WHITE)) {
            throw new InvalidMoveException(String.valueOf(ErrorMessage.INVALID_MOVE_ITS_OPPONENTS_TURN));
        }
    }

    private int getNextMoveOrder() {
        MoveEntity previousMove = previousMove();

        if(previousMove != null) {
            return previousMove.getMoveOrder() + 1;
        }

        return 1;
    }

    private MoveEntity previousMove() {
        Optional<MoveEntity> previousMoveEntity = gameEntity.getMoves()
                .stream()
                .max(Comparator.comparing(MoveEntity::getMoveOrder));

        return previousMoveEntity.orElse(null);
    }

    private void updatePieceSquare() {
        SquareEntity newPieceSquare = new SquareEntity(
                piece.getSquare().getNumber(),
                piece.getSquare().getLetter());

        moveEntity.getPiece().setSquare(newPieceSquare);
    }

    public Long getGameId() {
        return gameId;
    }

    public Piece getPiece() {
        return piece;
    }

    public Square getDestination() {
        return destination;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
