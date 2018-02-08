package com.chessmasters.chessapi.model.piece;

import com.chessmasters.chessapi.enums.PieceColor;
import com.chessmasters.chessapi.model.Coordinate;
import com.chessmasters.chessapi.model.piece.move.BlackPawnMove;
import com.chessmasters.chessapi.model.piece.move.PawnMove;
import com.chessmasters.chessapi.model.piece.move.WhitePawnMove;

import static com.chessmasters.chessapi.model.Coordinate.*;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {

    private PawnMove pawnMove;
    private Coordinate actualCoordinate;

    public Pawn(PieceColor color) {
        super(color);
    }

    @Override
    void move() {

    }

    @Override
    public List<Coordinate> getValidCoordinates(Coordinate actualCoordinate) {
        this.actualCoordinate = actualCoordinate;
        if(this.getColor().equals(PieceColor.WHITE)) {
            pawnMove = new WhitePawnMove(actualCoordinate);
        } else {
            pawnMove = new BlackPawnMove(actualCoordinate);
        }
        return getValidCoordinates();
    }

    private List<Coordinate> getValidCoordinates() {
        final int actualNumber = actualCoordinate.getNumber();
        final List<Coordinate> coordinates = new ArrayList<>();

        if(actualNumber == pawnMove.getMaxNumber()) {
            return coordinates;
        }

        final Coordinate nextSquareCoordinate = new Coordinate(actualCoordinate.getLetter(), pawnMove.getNextNumber());
        coordinates.add(nextSquareCoordinate);
        coordinates.addAll(nextTwoSquaresCoordinate(actualCoordinate));
        coordinates.addAll(leftDiagonalCoordinate(actualCoordinate));
        coordinates.addAll(rightDiagonalCoordinate(actualCoordinate));

        return coordinates;
    }

    private List<Coordinate> nextTwoSquaresCoordinate(final Coordinate actualCoordinate) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final int actualNumber = actualCoordinate.getNumber();
        final LetterFile actualLetter = actualCoordinate.getLetter();

        if(actualNumber == pawnMove.getInitialNumber()) {
            coordinates.add(new Coordinate(actualLetter, pawnMove.getNextTwoNumbers()));
        }
        return coordinates;
    }

    private List<Coordinate> leftDiagonalCoordinate(final Coordinate actualCoordinate) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final int letterIndex = actualCoordinate.getLetter().ordinal();
        final LetterFile[] letterValues = Coordinate.LetterFile.values();

        final boolean isLetterNotOnLeftBorder = letterIndex >= 1;
        if(isLetterNotOnLeftBorder) {
            final LetterFile leftLetter = letterValues[letterIndex - 1];
            coordinates.add(new Coordinate(leftLetter, pawnMove.getNextNumber()));
        }

        return coordinates;
    }

    private List<Coordinate> rightDiagonalCoordinate(final Coordinate actualCoordinate) {
        final List<Coordinate> coordinates = new ArrayList<>();
        final int letterIndex = actualCoordinate.getLetter().ordinal();
        final LetterFile[] letterValues = Coordinate.LetterFile.values();
        final boolean isLetterNotOnRightBorder = letterIndex < letterValues.length - 1;

        if(isLetterNotOnRightBorder) {
            final LetterFile rightLetter = letterValues[letterIndex + 1];
            coordinates.add(new Coordinate(rightLetter, pawnMove.getNextNumber()));
        }

        return coordinates;
    }

    @Override
    public String toString() {
        return String.format("%s Pawn", getColor());
    }
}
