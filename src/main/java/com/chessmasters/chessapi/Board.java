package com.chessmasters.chessapi;

import com.chessmasters.chessapi.exception.InvalidMoveException;
import com.chessmasters.chessapi.exception.PieceNotFoundException;
import com.chessmasters.chessapi.piece.Piece;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

        if(pieceTo != null) {
            removePieceFromList(pieceTo);
        }

        pieceFrom.setSquare(destination);

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

    private boolean isMoveValid(@NotNull final Piece piece, @NotNull Square destination) {
        return piece.moves(this)
                .stream()
                .anyMatch(square -> square.equals(destination));
    }

    private void removePieceFromList(@NotNull final Piece piece) {
        if(piece != null) {
            List<Piece> newPieces = pieces
                    .stream()
                    .filter(p -> !p.equals(piece))
                    .collect(Collectors.toList());

            pieces = new ArrayList<>(newPieces);
        }
    }
}
