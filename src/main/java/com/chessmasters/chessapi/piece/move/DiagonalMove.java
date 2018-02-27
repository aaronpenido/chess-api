package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.Piece;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

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
        return pathBySquareFunction(s -> s.nextNumberAndPreviousLetter());
    }

    private List<Square> leftBehind() {
        return pathBySquareFunction(s -> s.previousNumberAndLetter());
    }

    private List<Square> rightAhead() {
        return pathBySquareFunction(s -> s.nextNumberAndLetter());
    }

    private List<Square> rightBehind() {
        return pathBySquareFunction(s -> s.previousNumberAndNextLetter());
    }

    private List<Square> pathBySquareFunction(Function<Square, Optional<Square>> f) {
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

    private Optional<Square> squareByFunction(Optional<Square> destination, Function<Square, Optional<Square>> f) {
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

    private boolean isSquareFilledWithEnemyPiece(Piece destinationPiece) {
        Piece piece = board.getPieceFromSquare(square);

        return destinationPiece != null &&
                !destinationPiece.getColor().equals(piece.getColor());
    }
}
