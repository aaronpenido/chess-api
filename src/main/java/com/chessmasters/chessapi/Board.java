package com.chessmasters.chessapi;

import com.chessmasters.chessapi.exception.InvalidMoveException;
import com.chessmasters.chessapi.exception.PieceNotFoundException;
import com.chessmasters.chessapi.piece.Pawn;
import com.chessmasters.chessapi.piece.Piece;
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

    public Piece getPieceFromSquare(Square square) {

        Piece piece = pieces
                .stream()
                .filter(p -> p != null)
                .filter(p -> p.getSquare().equals(square))
                .findFirst()
                .orElse(null);

        return piece;
    }

    private boolean isDestinationSquareFilledWithSameColorPiece(Piece pieceFrom, Piece pieceTo) {
        return pieceTo != null &&
                pieceTo.getColor().equals(pieceFrom.getColor());
    }

    private boolean isMoveValid(@NotNull final Piece piece, @NotNull Square destination) {
        return piece.moves(this)
                .stream()
                .anyMatch(square -> square.equals(destination));
    }
}
