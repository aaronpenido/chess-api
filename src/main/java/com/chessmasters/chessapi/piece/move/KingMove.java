package com.chessmasters.chessapi.piece.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.enums.Letter;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.King;
import com.chessmasters.chessapi.piece.Pawn;
import com.chessmasters.chessapi.piece.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class KingMove extends Move {

    public KingMove(Board board, Square square) {
        super(board, square);
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        path.addAll(oneSquareAhead());
        path.addAll(oneSquareBehind());
        path.addAll(oneSquareLeft());
        path.addAll(oneSquareRight());

        path.addAll(diagonalLeftAhead());
        path.addAll(diagonalLeftBehind());
        path.addAll(diagonalRightAhead());
        path.addAll(diagonalRightBehind());

        return path;
    }

    private List<Square> oneSquareAhead() {
        final int topBorderNumber = 8;

        if(square.getNumber() != topBorderNumber) {
            final int nextNumber = square.getNumber() + 1;
            return oneSquareIfIsAllowedToMove(new Square(square.getLetter(), nextNumber));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareBehind() {
        final int downBorderNumber = 1;

        if(square.getNumber() != downBorderNumber) {
            final int previousNumber = square.getNumber() - 1;
            return oneSquareIfIsAllowedToMove(new Square(square.getLetter(), previousNumber));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareLeft() {
        final Letter leftBorderLetter = Letter.A;

        if(!square.getLetter().equals(leftBorderLetter)) {
            final Letter previousLetter = Letter.previousLetter(square.getLetter());
            return oneSquareIfIsAllowedToMove(new Square(previousLetter, square.getNumber()));
        }

        return Collections.emptyList();
    }

    private List<Square> oneSquareRight() {
        final Letter rightBorderLetter = Letter.H;

        if(!square.getLetter().equals(rightBorderLetter)) {
            final Letter nextLetter = Letter.nextLetter(square.getLetter());
            return oneSquareIfIsAllowedToMove(new Square(nextLetter, square.getNumber()));
        }

        return Collections.emptyList();
    }

    private List<Square> diagonalLeftAhead() {
        final Letter leftBorderLetter = Letter.A;
        final int topBorderNumber = 8;

        if(!square.getLetter().equals(leftBorderLetter) && square.getNumber() < topBorderNumber) {
            int nextNumber = square.getNumber() + 1;
            return oneSquareLeft(nextNumber);
        }

        return Collections.emptyList();
    }

    private List<Square> diagonalLeftBehind() {
        final Letter leftBorderLetter = Letter.A;
        final int bottomBorderNumber = 1;

        if(!square.getLetter().equals(leftBorderLetter) && square.getNumber() > bottomBorderNumber) {
            int previousNumber = square.getNumber() - 1;
            return oneSquareLeft(previousNumber);
        }
        return Collections.emptyList();
    }

    private List<Square> diagonalRightAhead() {
        final Letter rightBorderLetter = Letter.H;
        final int topBorderNumber = 8;

        if(!square.getLetter().equals(rightBorderLetter) && square.getNumber() < topBorderNumber) {
            int nextNumber = square.getNumber() + 1;
            return oneSquareRight(nextNumber);
        }
        return Collections.emptyList();
    }

    private List<Square> diagonalRightBehind() {
        final Letter rightBorderLetter = Letter.H;
        final int bottomBorderNumber = 1;

        if(!square.getLetter().equals(rightBorderLetter) && square.getNumber() > bottomBorderNumber) {
            int previousNumber = square.getNumber() - 1;
            return oneSquareRight(previousNumber);
        }
        return Collections.emptyList();
    }

    private List<Square> oneSquareLeft(final int number) {
        final Letter previousLetter = Letter.previousLetter(square.getLetter());
        return oneSquareIfIsAllowedToMove(new Square(previousLetter, number));
    }

    private List<Square> oneSquareRight(final int number) {
        final Letter nextLetter = Letter.nextLetter(square.getLetter());
        return oneSquareIfIsAllowedToMove(new Square(nextLetter, number));
    }

    private List<Square> oneSquareIfIsAllowedToMove(Square moveSquare) {
        final List<Square> path = new ArrayList<>();
        final Piece king = board.getPieceFromSquare(square);
        final Piece pieceFromMoveSquare = board.getPieceFromSquare(moveSquare);
        final boolean squareIsEmpty = pieceFromMoveSquare == null;
        final boolean pieceCanBeCaptured = (pieceFromMoveSquare != null &&
                !pieceFromMoveSquare.getColor().equals(king.getColor()));

        if(!isSquareUnderAttack(moveSquare) && (squareIsEmpty || pieceCanBeCaptured)) {
            path.add(moveSquare);
        }

        return path;
    }

    private boolean isSquareUnderAttack(Square moveSquare) {
        final Piece pieceInActualSquare = board.getPieceFromSquare(square);

        List<Piece> enemyPieces = board.getPieces()
                .stream()
                .filter(piece -> !piece.getColor().equals(pieceInActualSquare.getColor()))
                .filter(piece -> !(piece instanceof King))
                .filter(piece -> !(piece instanceof Pawn))
                .collect(Collectors.toList());

        boolean isUnderAttack = enemyPieces
                .stream()
                .anyMatch(piece -> piece.moves(board).contains(moveSquare));

        if(!isUnderAttack) {
            if (isSquareUnderAttackByEnemyKing(moveSquare)) {
                return true;
            }
            if(isSquareUnderAttackByEnemyPawn(moveSquare)) {
                return true;
            }
        }

        return isUnderAttack;
    }

    private boolean isSquareUnderAttackByEnemyPawn(Square destination) {
        final Piece pieceInActualSquare = board.getPieceFromSquare(square);

        Pawn enemyPawn = board.getPieces()
                .stream()
                .filter(piece -> piece instanceof Pawn)
                .filter(piece -> !piece.getColor().equals(pieceInActualSquare.getColor()))
                .map(pawn -> new Pawn(pawn.getColor(), pawn.getSquare()))
                .findFirst()
                .orElse(null);

        if(enemyPawn != null) {
            return enemyPawn.attackMoves(board).contains(destination);
        }

        return false;
    }

    private boolean isSquareUnderAttackByEnemyKing(Square destination) {
        final Piece pieceInActualSquare = board.getPieceFromSquare(square);

        Piece enemyKing = board.getPieces()
                .stream()
                .filter(piece -> piece instanceof King)
                .filter(piece -> !piece.getColor().equals(pieceInActualSquare.getColor()))
                .findFirst()
                .orElse(null);

        if(enemyKing != null) {
            Board newBoard = new Board(Collections.singletonList(enemyKing));
            if(enemyKing.moves(newBoard).contains(destination)) {
                return true;
            }
        }
        return false;
    }
}
