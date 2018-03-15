package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.entities.Player;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotFoundException;
import com.chessmasters.chessapi.exceptions.GameStartedException;
import com.chessmasters.chessapi.models.GameModel;
import com.chessmasters.chessapi.repositories.GameRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository gameRepository;
    private final PlayerService playerService;

    public GameService(GameRepository gameRepository, PlayerService playerService) {
        this.gameRepository = gameRepository;
        this.playerService = playerService;
    }

    public GameModel createGame(GameRequest gameRequest) {
        Player player = playerService.getById(gameRequest.getPlayerId());

        Game game = gameRepository.save(new Game(player, GameStatus.CREATED));

        if(game == null) {
            throw new RuntimeException("An error occurred when trying to create a game.");
        }

        return new GameModel(game);
    }

    public List<GameModel> getGames() {
        List<Game> games = gameRepository.findAll();

        return games
                .stream()
                .map(GameModel::new)
                .collect(Collectors.toList());
    }

    public GameModel startGame(Long gameId, GameRequest gameRequest) {
        Game game = gameRepository.findOne(gameId);

        if(game == null) {
            throw new GameNotFoundException(gameId);
        }

        if(game.getStatus().equals(GameStatus.STARTED)) {
            throw new GameStartedException(gameId);
        }

        Player player2 = playerService.getById(gameRequest.getPlayerId());

        game.setStatus(GameStatus.STARTED);
        game.setPlayer2(player2);
        game = gameRepository.save(game);

        return new GameModel(game);
    }
}
