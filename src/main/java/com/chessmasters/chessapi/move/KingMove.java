package com.chessmasters.chessapi.move;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.Square;
import com.chessmasters.chessapi.piece.King;
import com.chessmasters.chessapi.piece.Pawn;
import com.chessmasters.chessapi.piece.Piece;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class KingMove extends Move {

    public KingMove(Board board, Square square) {
        super(board, square);
    }

    @Override
    public List<Square> path() {
        List<Square> path = new ArrayList<>();

        oneSquareAhead().ifPresent(path::add);
        oneSquareBehind().ifPresent(path::add);
        oneSquareLeft().ifPresent(path::add);
        oneSquareRight().ifPresent(path::add);

        diagonalLeftAhead().ifPresent(path::add);
        diagonalLeftBehind().ifPresent(path::add);
        diagonalRightAhead().ifPresent(path::add);
        diagonalRightBehind().ifPresent(path::add);

        return path;
    }

    private Optional<Square> oneSquareAhead() {
        return pathBySquareFunctionIfIsAllowedToMove(Square::nextNumber);
    }

    private Optional<Square> oneSquareBehind() {
        return pathBySquareFunctionIfIsAllowedToMove(Square::previousNumber);
    }

    private Optional<Square> oneSquareLeft() {
        return pathBySquareFunctionIfIsAllowedToMove(Square::previousLetter);
    }

    private Optional<Square> oneSquareRight() {
        return pathBySquareFunctionIfIsAllowedToMove(Square::nextLetter);
    }

    private Optional<Square> diagonalLeftAhead() {
        return pathBySquareFunctionIfIsAllowedToMove(Square::nextNumberAndPreviousLetter);
    }

    private Optional<Square> diagonalLeftBehind() {
        return pathBySquareFunctionIfIsAllowedToMove(Square::previousNumberAndLetter);
    }

    private Optional<Square> diagonalRightAhead() {
        return pathBySquareFunctionIfIsAllowedToMove(Square::nextNumberAndLetter);
    }

    private Optional<Square> diagonalRightBehind() {
        return pathBySquareFunctionIfIsAllowedToMove(Square::previousNumberAndNextLetter);
    }

    private Optional<Square> pathBySquareFunctionIfIsAllowedToMove(Function<Square, Optional<Square>> f) {
        List<Square> path = pathBySquareFunction(f);

        if(!path.isEmpty()) {
            return oneSquareIfIsAllowedToMove(path.get(0));
        }

        return Optional.empty();
    }

    private Optional<Square> oneSquareIfIsAllowedToMove(Square moveSquare) {
        final Piece king = board.getPieceFromSquare(square);
        final Piece pieceFromMoveSquare = board.getPieceFromSquare(moveSquare);
        final boolean squareIsEmpty = pieceFromMoveSquare == null;
        final boolean pieceCanBeCaptured = (pieceFromMoveSquare != null &&
                !pieceFromMoveSquare.getColor().equals(king.getColor()));

        if(!isSquareUnderAttack(moveSquare) && (squareIsEmpty || pieceCanBeCaptured)) {
            return Optional.of(moveSquare);
        }

        return Optional.empty();
    }

    private boolean isSquareUnderAttack(Square destination) {
        final Piece pieceInActualSquare = board.getPieceFromSquare(square);

        boolean isUnderAttack = board.getPieces()
                .stream()
                .filter(piece -> !piece.getColor().equals(pieceInActualSquare.getColor()))
                .filter(piece -> !(piece instanceof King))
                .filter(piece -> !(piece instanceof Pawn))
                .anyMatch(piece -> piece.moves(board).contains(destination));

        if(!isUnderAttack) {
            if (isSquareUnderAttackByEnemyKing(destination)) {
                return true;
            }
            if(isSquareUnderAttackByEnemyPawn(destination)) {
                return true;
            }
        }

        return isUnderAttack;
    }

    private boolean isSquareUnderAttackByEnemyPawn(Square destination) {
        final Piece pieceInActualSquare = board.getPieceFromSquare(square);

        boolean isUnderAttack = board.getPieces()
                .stream()
                .filter(piece -> piece instanceof Pawn)
                .filter(piece -> !piece.getColor().equals(pieceInActualSquare.getColor()))
                .map(pawn -> new Pawn(pawn.getColor(), pawn.getSquare()))
                .anyMatch(piece -> piece.attackMoves(board).contains(destination));

        return isUnderAttack;
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
            Game game = new Game();
            game.setPieces(Collections.singletonList(enemyKing));

            Board newBoard = new Board(game);

            if(enemyKing.moves(newBoard).contains(destination)) {
                return true;
            }
        }
        return false;
    }
}
