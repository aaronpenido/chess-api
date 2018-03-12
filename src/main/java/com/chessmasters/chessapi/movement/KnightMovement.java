package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.entity.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KnightMovement extends Movement {

    public KnightMovement(Board board, Square square) {
        super(board, square);
    }

    @Override
    public List<Square> path() {
        List<Square> moves = new ArrayList<>();

        moves.addAll(leftAhead());
        moves.addAll(leftBehind());
        moves.addAll(rightAhead());
        moves.addAll(rightBehind());

        return moves;
    }

    private List<Square> leftAhead() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination =
                squareByFunction(Square::twoNextNumberAndOnePreviousLetter);
        destination.ifPresent(moves::add);

        destination = squareByFunction(Square::twoPreviousLetterAndOneNextNumber);
        destination.ifPresent(moves::add);

        return moves;
    }

    private List<Square> leftBehind() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination =
                squareByFunction(Square::twoPreviousLetterAndOnePreviousNumber);
        destination.ifPresent(moves::add);

        destination = squareByFunction(Square::twoPreviousNumberAndOnePreviousLetter);
        destination.ifPresent(moves::add);

        return moves;
    }

    private List<Square> rightAhead() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination =
                squareByFunction(Square::twoNextNumberAndOneNextLetter);
        destination.ifPresent(moves::add);

        destination = squareByFunction(Square::twoNextLetterAndOneNextNumber);
        destination.ifPresent(moves::add);

        return moves;
    }

    private List<Square> rightBehind() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination = squareByFunction(Square::twoPreviousNumberAndOneNextLetter);
        destination.ifPresent(moves::add);

        destination = squareByFunction(Square::twoNextLetterAndOnePreviousNumber);
        destination.ifPresent(moves::add);

        return moves;
    }
}
