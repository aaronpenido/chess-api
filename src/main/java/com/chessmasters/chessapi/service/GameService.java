package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.Board;
import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.Player;
import com.chessmasters.chessapi.repository.GameRepository;
import com.chessmasters.chessapi.repository.PlayerRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
        Game game = new Game(player, new Board(new ArrayList<>()));
        return repository.save(game);
    }

    public Game startGame(Long gameId, GameRequest gameRequest) {
        Player player = playerRepository.findOne(gameRequest.getPlayerId());
        Game game = gameById(gameId);
        game.setPlayer2(player);

        return repository.save(game);
    }

    public Game gameById(Long id) {
        return repository.findOne(id);
    }
}
