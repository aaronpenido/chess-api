package com.chessmasters.chessapi.model;

import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.entity.Player;
import com.chessmasters.chessapi.entity.Square;
import com.chessmasters.chessapi.entity.piece.Piece;
import com.chessmasters.chessapi.enums.Color;
import com.chessmasters.chessapi.exception.InvalidMoveException;
import com.chessmasters.chessapi.exception.PieceNotFoundException;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Board {

    private Color nextMoveColor = Color.WHITE;
    private List<Piece> pieces;
    private Game game;

    public Board(Game game) {
        this.game = game;
        this.pieces = game.getPieces();
    }

    public List<Piece> getPieces() {
        return pieces;
    }

    public void movePiece(@NotNull final Player player,
                          @NotNull GameMove move) {
        Piece originPiece = getPieceFromSquare(move.getOrigin());
        Piece destinationPiece = getPieceFromSquare(move.getDestination());

        if(originPiece == null) {
            throw new PieceNotFoundException(move.getOrigin());
        }

        if(!isColorMoveValid(player, originPiece)) {
            throw new InvalidMoveException("It's opponent's turn");
        }

        if(!isMoveValid(originPiece, move.getDestination())) {
            throw new InvalidMoveException("Invalid move");
        }

        if(destinationPiece != null) {
            removePieceFromList(destinationPiece);
        }

        originPiece.setSquare(move.getDestination());
        nextMoveColor = Color.opposite(originPiece.getColor());
    }

    private boolean isColorMoveValid(@NotNull final Player player, @NotNull final Piece piece) {
        return piece.getColor().equals(nextMoveColor) && piece.getColor().equals(player.getColor());
    }

    public Piece getPieceFromSquare(Square square) {
        Piece piece = pieces
                .stream()
                .filter(Objects::nonNull)
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
