package com.chessmasters.chessapi;

import com.chessmasters.chessapi.exception.InvalidMoveExcpetion;
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

    public void movePiece(@NotNull final Square from, @NotNull final Square to) {
        Piece pieceFrom = getPieceFromSquare(from);
        Piece pieceTo = getPieceFromSquare(to);

        if(pieceFrom == null) {
            throw new PieceNotFoundException(from);
        }

        boolean isMoveValid = pieceFrom.moves()
                .stream()
                .anyMatch(square -> square.equals(to));

        if(!isMoveValid) {
            throw new InvalidMoveExcpetion();
        }

        if(pieceFrom instanceof Pawn) {
            boolean isDiagonal = !from.getLetter().equals(to.getLetter());

            if(isDiagonal && pieceTo == null) {
                throw new InvalidMoveExcpetion();
            }
        }

        pieceFrom.setSquare(to);

        if(pieceTo != null) {
            pieces.remove(pieceTo);
        }
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
