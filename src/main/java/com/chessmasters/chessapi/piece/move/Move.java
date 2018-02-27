package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class Move {

    Board board;
    Square square;

    public Move(Board board, Square square) {
        this.board = board;
        this.square = square;
    }

    public abstract List<Square> path();

    List<Square> pathBySquareFunction(Function<Square, Optional<Square>> f) {
        List<Square> path = new ArrayList<>();
        Optional<Square> destination = Optional.of(square);

        while(destination.isPresent()) {
            destination = squareByFunction(destination, f);
            if(destination.isPresent()) {
                path.add(destination.get());
            }
        }

        return path;
    }

    Optional<Square> squareByFunction(Optional<Square> destination, Function<Square, Optional<Square>> f) {
        if(destination.isPresent()) {
            Piece piece = board.getPieceFromSquare(destination.get());

            if(isSquareFilledWithEnemyPiece(piece)) {
                return Optional.empty();
            }

            Optional<Square> squareToMove = f.apply(destination.get());

            if(squareToMove.isPresent()) {
                Piece pieceToMove = board.getPieceFromSquare(squareToMove.get());

                if(pieceToMove == null || isSquareFilledWithEnemyPiece(pieceToMove)) {
                    return squareToMove;
                }
            }
        }

        return Optional.empty();
    }

    boolean isSquareFilledWithEnemyPiece(Piece destinationPiece) {
        Piece piece = board.getPieceFromSquare(square);

        return destinationPiece != null &&
                !destinationPiece.getColor().equals(piece.getColor());
    }
}
