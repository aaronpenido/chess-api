package com.chessmasters.chessapi.piece;

import com.chessmasters.chessapi.*;
import com.chessmasters.chessapi.piece.move.BlackPawnMove;
import com.chessmasters.chessapi.piece.move.PawnMove;
import com.chessmasters.chessapi.piece.move.WhitePawnMove;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.chessmasters.chessapi.Color.*;

public class Pawn extends Piece {

    private PawnMove pawnMove;

    public Pawn(Color color, Square square) {
        super(color, square);

        if(color.equals(WHITE)) {
            pawnMove = new WhitePawnMove(square);
        } else {
            pawnMove = new BlackPawnMove(square);
        }
    }

    @Override
    public List<Square> moves(Board board) {
        List<Square> moves = new ArrayList<>();

        if(square.getNumber() != pawnMove.getPromotionNumber()) {
            Square oneSquareAhead = new Square(square.getLetter(), pawnMove.getNextNumber());
            moves.addAll(getSquareIfIsNotFilled(board, oneSquareAhead));

            if(square.getNumber() == pawnMove.getInitialNumber()) {
                Square twoSquaresAhead = new Square(square.getLetter(), pawnMove.getNextTwoNumber());
                moves.addAll(getSquareIfIsNotFilled(board, twoSquaresAhead));
            }

            List<Square> squaresFilledWithEnemyPiece = attackMoves()
                    .stream()
                    .filter(s -> isSquareFilledWithEnemyPiece(board, s))
                    .collect(Collectors.toList());

            moves.addAll(squaresFilledWithEnemyPiece);
        }

        return moves;
    }

    public List<Square> attackMoves() {
        List<Square> moves = new ArrayList<>();
        final Letter leftBorderLetter = Letter.A;
        final Letter rightBorderLetter = Letter.H;

        if(!square.getLetter().equals(rightBorderLetter)) {
            final Letter nextLetter = Letter.nextLetter(square.getLetter());
            Square rightDiagonalSquare = new Square(nextLetter, pawnMove.getNextNumber());

            moves.add(rightDiagonalSquare);
        }
        if(!square.getLetter().equals(leftBorderLetter)) {
            final Letter previousLetter = Letter.previousLetter(square.getLetter());
            Square leftDiagonalSquare = new Square(previousLetter, pawnMove.getNextNumber());

            moves.add(leftDiagonalSquare);
        }

        return moves;
    }

    private List<Square> getSquareIfIsNotFilled(Board board, Square destination) {
        if(!isSquareFilled(board, destination)) {
            return Collections.singletonList(destination);
        }

        return Collections.emptyList();
    }

    private boolean isSquareFilled(Board board, Square destination) {
        return board.getPieceFromSquare(destination) != null;
    }

    private boolean isSquareFilledWithEnemyPiece(Board board, Square destination) {
        Piece piece = board.getPieceFromSquare(square);
        Piece destinationPiece = board.getPieceFromSquare(destination);
        boolean isEnemyPiece = destinationPiece != null &&
                !destinationPiece.getColor().equals(piece.getColor());

        return isEnemyPiece;

    }
}
