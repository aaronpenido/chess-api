package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class PawnMove extends Move {

    int initialNumber;
    int promotionNumber;
    Function<Square, Optional<Square>> moveFunction;

    public PawnMove(Board board,
                    Square square,
                    int initialNumber,
                    int promotionNumber,
                    int nextNumber,
                    int nextTwoNumber,
                    Function<Square, Optional<Square>> moveFunction) {
        super(board, square);
        this.initialNumber = initialNumber;
        this.promotionNumber = promotionNumber;
        this.moveFunction = moveFunction;
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        Optional<Square> destination = squareByFunction(moveFunction, false);
        destination.ifPresent(path::add);

        if(square.getNumber() == initialNumber) {
            destination = squareByFunction(destination, moveFunction, false);
            destination.ifPresent(path::add);
        }

        path.addAll(squaresFilledWithEnemyPieces());

        return path;
    }

    private List<Square> squaresFilledWithEnemyPieces() {
        return attackMoves()
                .stream()
                .map(board::getPieceFromSquare)
                .filter(this::isSquareFilledWithEnemyPiece)
                .map(Piece::getSquare)
                .collect(Collectors.toList());
    }

    public List<Square> attackMoves() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination = squareByFunction(moveFunction);

        if(destination.isPresent()) {
            Optional<Square> attackDestination = squareByFunction(destination, Square::nextLetter);
            attackDestination.ifPresent(moves::add);

            attackDestination = squareByFunction(destination, Square::previousLetter);
            attackDestination.ifPresent(moves::add);
        }

        return moves;
    }
}
