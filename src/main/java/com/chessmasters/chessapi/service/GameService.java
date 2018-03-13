package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.entity.Move;
import com.chessmasters.chessapi.entity.Player;
import com.chessmasters.chessapi.exception.GameNotFoundException;
import com.chessmasters.chessapi.exception.GameNotStartedException;
import com.chessmasters.chessapi.exception.PlayerNotFoundException;
import com.chessmasters.chessapi.model.Board;
import com.chessmasters.chessapi.repository.GameRepository;
import com.chessmasters.chessapi.repository.PlayerRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

    private final GameRepository repository;
    private final PlayerRepository playerRepository;

    public GameService(GameRepository repository, PlayerRepository playerRepository) {
        this.repository = repository;
        this.playerRepository = playerRepository;
    }

    public Game registerGame(GameRequest gameRequest) {
        Player player = playerById(gameRequest.getPlayerId());
        Game game = new Game(player);

        return repository.save(game);
    }

    public Game startGame(Long gameId, GameRequest gameRequest) {
        Game game = gameById(gameId);
        Player player = playerById(gameRequest.getPlayerId());
        game.start(player);

        return repository.save(game);
    }

    public Game movePiece(Long gameId, GameRequest gameRequest) {
        Game game = gameById(gameId);

        if(game.getPieces() == null) {
            throw new GameNotStartedException(gameId);
        }

        Player player = playerById(gameRequest.getPlayerId());

        Board board = new Board(game);
        board.movePiece(player, gameRequest.getMove());

        game.setPieces(board.getPieces());

        Move move = new Move(gameRequest.getMove().getOrigin(), gameRequest.getMove().getDestination());
        move.setMoveOrder(generateMoveOrder(game.moves()));
        game.moves().add(move);

        return save(game);
    }

    public Game gameById(Long id) {
        Game game = repository.findOne(id);

        if(game == null) {
            throw new GameNotFoundException();
        }
        return game;
    }

    public Game save(Game game) {
        return repository.save(game);
    }

    private Player playerById(final Long id) {
        Player player = playerRepository.findOne(id);

        if(player == null) {
            throw new PlayerNotFoundException();
        }

        return player;
    }

    private int generateMoveOrder(List<Move> moves) {
        if(moves != null) {
            return (int) moves.stream().count() + 1;
        }

        return 0;
    }
}
