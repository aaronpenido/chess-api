package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.entity.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public abstract class Movement {

    Board board;
    Square square;

    public Movement(Board board, Square square) {
        this.board = board;
        this.square = square;
    }

    public abstract List<Square> path();

    List<Square> pathBySquareFunction(Function<Square, Optional<Square>> f) {
        List<Square> path = new ArrayList<>();
        Optional<Square> destination = Optional.of(square);

        while(destination.isPresent() && !haveFoundPieceThatEndsPath(destination)) {
            destination = squareByFunction(destination, f);
            destination.ifPresent(path::add);
        }

        return path;
    }

    Optional<Square> squareByFunction(Function<Square, Optional<Square>> f) {
        return squareByFunction(Optional.of(square), f, true);
    }

    Optional<Square> squareByFunction(Function<Square, Optional<Square>> f, boolean isEnemyPieceCanBeCaptured) {
        return squareByFunction(Optional.of(square), f, isEnemyPieceCanBeCaptured);
    }

    Optional<Square> squareByFunction(Optional<Square> destination,
                                              Function<Square, Optional<Square>> f) {
        return squareByFunction(destination, f, true);
    }

    Optional<Square> squareByFunction(Optional<Square> destination,
                                              Function<Square, Optional<Square>> f,
                                              boolean isEnemyPieceCanBeCaptured) {
        if(destination.isPresent()) {
            Optional<Square> squareToMove = f.apply(destination.get());

            if(squareToMove.isPresent()) {
                Piece pieceToMove = board.getPieceFromSquare(squareToMove.get());

                if(pieceToMove == null || (isEnemyPieceCanBeCaptured && isSquareFilledWithEnemyPiece(pieceToMove))) {
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

    private boolean haveFoundPieceThatEndsPath(Optional<Square> destination) {
        return destination
                .map(board::getPieceFromSquare)
                .map(this::isSquareFilledWithEnemyPiece)
                .orElse(false);
    }
}
