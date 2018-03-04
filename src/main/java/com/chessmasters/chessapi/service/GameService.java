package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.Player;
import com.chessmasters.chessapi.exception.GameNotFoundException;
import com.chessmasters.chessapi.exception.PlayerNotFoundException;
import com.chessmasters.chessapi.repository.GameRepository;
import com.chessmasters.chessapi.repository.PlayerRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.springframework.stereotype.Service;

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

        Long playerId = gameRequest.getPlayerId() != null ?
                gameRequest.getPlayerId() :
                gameRequest.getPlayer2Id();

        Player player = playerById(playerId);
        game.movePiece(player, gameRequest.getFrom(), gameRequest.getDestination());

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
}
