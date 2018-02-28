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

        Optional<Square> destination = squareByFunction(
                Optional.of(square), s -> s.twoNextNumberAndOnePreviousLetter());
        if(destination.isPresent()) {
            moves.add(destination.get());
        }

        destination = squareByFunction(Optional.of(square), s -> s.twoPreviousLetterAndOneNextNumber());
        if(destination.isPresent()) {
            moves.add(destination.get());
        }

        return moves;
    }

    private List<Square> leftBehind() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination = squareByFunction(
                Optional.of(square), s -> s.twoPreviousLetterAndOnePreviousNumber());
        if(destination.isPresent()) {
            moves.add(destination.get());
        }

        destination = squareByFunction(Optional.of(square), s -> s.twoPreviousNumberAndOnePreviousLetter());
        if(destination.isPresent()) {
            moves.add(destination.get());
        }

        return moves;
    }

    private List<Square> rightAhead() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination = squareByFunction(
                Optional.of(square), s -> s.twoNextNumberAndOneNextLetter());
        if(destination.isPresent()) {
            moves.add(destination.get());
        }

        destination = squareByFunction(Optional.of(square), s -> s.twoNextLetterAndOneNextNumber());
        if(destination.isPresent()) {
            moves.add(destination.get());
        }

        return moves;
    }

    private List<Square> rightBehind() {
        List<Square> moves = new ArrayList<>();

        Optional<Square> destination = squareByFunction(Optional.of(square), s -> s.twoPreviousNumberAndOneNextLetter());
        if(destination.isPresent()) {
            moves.add(destination.get());
        }

        destination = squareByFunction(Optional.of(square), s -> s.twoNextLetterAndOnePreviousNumber());
        if(destination.isPresent()) {
            moves.add(destination.get());
        }

        return moves;
    }
}
