package com.chessmasters.chessapi.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;

import java.util.ArrayList;
import java.util.List;

public class DiagonalMove extends Move {

    public DiagonalMove(Board board, Square square) {
        super(board, square);
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        path.addAll(leftAhead());
        path.addAll(rightAhead());
        path.addAll(leftBehind());
        path.addAll(rightBehind());

        return path;
    }

    private List<Square> leftAhead() {
        return pathBySquareFunction(Square::nextNumberAndPreviousLetter);
    }

    private List<Square> leftBehind() {
        return pathBySquareFunction(Square::previousNumberAndLetter);
    }

    private List<Square> rightAhead() {
        return pathBySquareFunction(Square::nextNumberAndLetter);
    }

    private List<Square> rightBehind() {
        return pathBySquareFunction(Square::previousNumberAndNextLetter);
    }
}
