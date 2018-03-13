package com.chessmasters.chessapi.movement;

import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.entity.Square;
import java.util.*;

public class StraightMovement extends Movement {

    public StraightMovement(Board board, Square square) {
        super(board, square);
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        path.addAll(leftPath());
        path.addAll(rightPath());
        path.addAll(aheadPath());
        path.addAll(behindPath());

        return path;
    }

    private List<Square> leftPath() {
        return pathBySquareFunction(Square::previousLetter);
    }

    private List<Square> rightPath() {
        return pathBySquareFunction(Square::nextLetter);
    }

    private List<Square> aheadPath() {
        return pathBySquareFunction(Square::nextNumber);
    }

    private List<Square> behindPath() {
        return pathBySquareFunction(Square::previousNumber);
    }
}
