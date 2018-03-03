package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.Player;
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
        Player player = playerRepository.findOne(gameRequest.getPlayerId());
        Game game = new Game(player);
        return repository.save(game);
    }

    public Game startGame(Long gameId, GameRequest gameRequest) {
        Player player = playerRepository.findOne(gameRequest.getPlayerId());
        Game game = gameById(gameId);
        game.setPlayer2(player);
        game.start();

        return repository.save(game);
    }

    public Game movePiece(Long gameId, GameRequest gameRequest) {
        Game game = gameById(gameId);

        game.movePiece(gameRequest.getFrom(), gameRequest.getDestination());

        return save(game);
    }

    public Game gameById(Long id) {
        return repository.findOne(id);
    }

    public Game save(Game game) {
        return repository.save(game);
    }
}
