package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KnightMove extends Move {

    public KnightMove(Board board, Square square) {
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
                squareByFunction(s -> s.twoNextNumberAndOnePreviousLetter());
        destination.ifPresent(moves::add);

        destination = squareByFunction(s -> s.twoPreviousLetterAndOneNextNumber());
        destination.ifPresent(moves::add);

        return moves;
    }

    private List<Square> leftBehind() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination =
                squareByFunction(s -> s.twoPreviousLetterAndOnePreviousNumber());
        destination.ifPresent(moves::add);

        destination = squareByFunction(s -> s.twoPreviousNumberAndOnePreviousLetter());
        destination.ifPresent(moves::add);

        return moves;
    }

    private List<Square> rightAhead() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination =
                squareByFunction(s -> s.twoNextNumberAndOneNextLetter());
        destination.ifPresent(moves::add);

        destination = squareByFunction(s -> s.twoNextLetterAndOneNextNumber());
        destination.ifPresent(moves::add);

        return moves;
    }

    private List<Square> rightBehind() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination = squareByFunction(s -> s.twoPreviousNumberAndOneNextLetter());
        destination.ifPresent(moves::add);

        destination = squareByFunction(s -> s.twoNextLetterAndOnePreviousNumber());
        destination.ifPresent(moves::add);

        return moves;
    }
}
