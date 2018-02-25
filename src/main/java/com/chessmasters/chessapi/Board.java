package com.chessmasters.chessapi;

import com.chessmasters.chessapi.exception.InvalidMoveException;
import com.chessmasters.chessapi.exception.PieceNotFoundException;
import com.chessmasters.chessapi.piece.Pawn;
import com.chessmasters.chessapi.piece.Piece;
import com.chessmasters.chessapi.piece.move.DiagonalMove;
import com.chessmasters.chessapi.piece.move.Move;
import com.chessmasters.chessapi.piece.move.StraightMove;
import javax.validation.constraints.NotNull;
import java.util.List;

public class Board {
    private List<Piece> pieces;

    public Board(List<Piece> pieces) {
        this.pieces = pieces;
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void movePiece(@NotNull final Square from, @NotNull final Square destination) {
        Piece pieceFrom = getPieceFromSquare(from);
        Piece pieceTo = getPieceFromSquare(destination);

        if(pieceFrom == null) {
            throw new PieceNotFoundException(from);
        }

        if(!isMoveValid(pieceFrom, destination)) {
            throw new InvalidMoveException();
        }

        if(pathContainsPiece(from, destination)) {
            throw new InvalidMoveException();
        }

        if(isDestinationSquareFilledWithSameColorPiece(pieceFrom, pieceTo)) {
            throw new InvalidMoveException();
        }

        if(pieceFrom instanceof Pawn) {
            boolean isDiagonal = !from.getLetter().equals(destination.getLetter());

            if(isDiagonal && pieceTo == null || (!isDiagonal && pieceTo !=  null)) {
                throw new InvalidMoveException();
            }
        }

        pieceFrom.setSquare(destination);

        if(pieceTo != null) {
            pieces.remove(pieceTo);
        }
    }

    private boolean isDestinationSquareFilledWithSameColorPiece(Piece pieceFrom, Piece pieceTo) {
        return pieceTo != null &&
                pieceTo.getColor().equals(pieceFrom.getColor());
    }

    private boolean isMoveValid(@NotNull final Piece piece, @NotNull Square destination) {
        return piece.moves()
                .stream()
                .anyMatch(square -> square.equals(destination));
    }

    private boolean pathContainsPiece(@NotNull final Square from, @NotNull final Square destination) {
        Move move;
        boolean isDiagonal = !from.getLetter().equals(destination.getLetter());

        if(isDiagonal) {
            move = new DiagonalMove(from);
        } else {
            move = new StraightMove(from);
        }

        final List<Square> path = move.path(destination);

        return pieces
                .stream()
                .filter(piece -> piece.getSquare() != null)
                .anyMatch(piece -> path.contains(piece.getSquare()));
    }

    private Piece getPieceFromSquare(Square square) {

        Piece piece = pieces
                .stream()
                .filter(p -> p != null)
                .filter(p -> p.getSquare().equals(square))
                .findFirst()
                .orElse(null);

        return piece;
    }
}
