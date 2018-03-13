package com.chessmasters.chessapi.response;

import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.model.GameMove;
import com.chessmasters.chessapi.model.GamePiece;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class GameResponse {

    private final Long id;
    private final List<GamePiece> pieces;
    private final List<GameMove> moves;

    public GameResponse(Game game) {
        this.id = game.getId();
        this.pieces = piecesFromGame(game);
        this.moves = movesFromGame(game);
    }
    public Long getId() {
        return id;
    }

    public List<GamePiece> getPieces() {
        return pieces;
    }

    public List<GameMove> getMoves() {
        return moves;
    }

    private List<GamePiece> piecesFromGame(Game game) {
        if(game.getPieces() != null) {
            return game.getPieces()
                    .stream()
                    .map(p -> new GamePiece(p.getColor(), p.getSquare()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    private List<GameMove> movesFromGame(Game game) {
        if(game.moves() != null) {
            return game.moves()
                    .stream()
                    .map(m -> new GameMove(m.getOrigin(), m.getDestination()))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
