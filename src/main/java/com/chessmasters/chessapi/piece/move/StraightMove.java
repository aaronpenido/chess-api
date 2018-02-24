package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Letter;
import com.chessmasters.chessapi.Square;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StraightMove {

    private Square square;
    private boolean isOneSquarePerMove;

    public StraightMove(Square square) {
        this.square = square;
    }

    public StraightMove(Square square, boolean isOneSquarePerMove) {
        this.square = square;
        this.isOneSquarePerMove = isOneSquarePerMove;
    }

    public List<Square> moves() {
        if(isOneSquarePerMove) {
            return oneSquarePerMove(square);
        }

        List<Square> moves = new ArrayList<>();

        moves.addAll(horizontalMoves());
        moves.addAll(verticalMoves());
        moves.removeAll(Collections.singleton(square));

        return moves;
    }

    private List<Square> horizontalMoves() {
        List<Square> squares = new ArrayList<>();

        Arrays.stream(Letter.values())
                .forEachOrdered(letter -> squares.add(
                        new Square(letter, square.getNumber()))
                );

        return squares;
    }

    private List<Square> verticalMoves() {
        List<Square> squares = new ArrayList<>();

        IntStream.range(1, 9)
                .forEach(number -> squares.add(
                        new Square(square.getLetter(), number))
                );

        return squares;
    }

    private List<Square> oneSquarePerMove(final Square square) {
        List<Square> moves = new ArrayList<>();

        moves.addAll(oneSquareAhead(square));
        moves.addAll(oneSquareBehind(square));
        moves.addAll(oneSquareLeft(square));
        moves.addAll(oneSquareRight(square));

        return moves;
    }

    private List<Square> oneSquareAhead(final Square square) {
        final int topBorderNumber = 8;

        if(square.getNumber() != topBorderNumber) {
            final int nextNumber = square.getNumber() + 1;
            return Collections.singletonList(new Square(square.getLetter(), nextNumber));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareBehind(final Square square) {
        final int downBorderNumber = 1;

        if(square.getNumber() != downBorderNumber) {
            final int previousNumber = square.getNumber() - 1;
            return Collections.singletonList(new Square(square.getLetter(), previousNumber));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareLeft(final Square square) {
        final Letter leftBorderLetter = Letter.A;

        if(!square.getLetter().equals(leftBorderLetter)) {
            final Letter previousLetter = Letter.previousLetter(square.getLetter());
            return Collections.singletonList(new Square(previousLetter, square.getNumber()));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareRight(final Square square) {
        final Letter rightBorderLetter = Letter.H;

        if(!square.getLetter().equals(rightBorderLetter)) {
            final Letter nextLetter = Letter.nextLetter(square.getLetter());
            return Collections.singletonList(new Square(nextLetter, square.getNumber()));
        }

        return Collections.emptyList();
    }

    public List<Square> path(Square destination) {
        if(!moves().contains(destination)) {
            return new ArrayList<>();
        }

        final boolean isMoveVertical = destination.getNumber() != square.getNumber();

        if(isMoveVertical) {
            return verticalPath(destination);
        }

        return horizontalPath(destination);
    }

    private List<Square> verticalPath(Square destination) {
        Predicate<Square> predicate;
        final boolean isMoveAhead =
                destination.getNumber() > square.getNumber();

        if(isMoveAhead) {
            predicate = filterAllSquaresAheadFromSquareToDestination(destination);
        } else {
            predicate = filterAllSquaresBehindFromSquareToDestination(destination);
        }

        return verticalMoves()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private List<Square> horizontalPath(Square destination) {
        final boolean isMoveLeft =
                destination.getLetter().ordinal() < square.getLetter().ordinal();
        Predicate<Square> predicate;

        if(isMoveLeft) {
            predicate = filterAllSquaresLeftFromSquareToDestination(destination);
        } else {
            predicate = filterAllSquaresRightFromSquareToDestination(destination);
        }

        return horizontalMoves()
                .stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }

    private Predicate<Square> filterAllSquaresLeftFromSquareToDestination(Square destination) {
        return s -> s.getLetter().ordinal() < square.getLetter().ordinal() &&
                s.getLetter().ordinal() > destination.getLetter().ordinal();
    }

    private Predicate<Square> filterAllSquaresRightFromSquareToDestination(Square destination) {
        return s -> s.getLetter().ordinal() > square.getLetter().ordinal() &&
                s.getLetter().ordinal() < destination.getLetter().ordinal();
    }

    private Predicate<Square> filterAllSquaresBehindFromSquareToDestination(Square destination) {
        return s -> s.getNumber() < square.getNumber() &&
                s.getNumber() > destination.getNumber();
    }

    private Predicate<Square> filterAllSquaresAheadFromSquareToDestination(Square destination) {
        return s -> s.getNumber() > square.getNumber() &&
                s.getNumber() < destination.getNumber();
    }
}
