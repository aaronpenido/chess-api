package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.GameEntity;
import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.enums.ErrorMessage;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.exceptions.GameNotFoundException;
import com.chessmasters.chessapi.exceptions.GameStartedException;
import com.chessmasters.chessapi.models.Game;
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

    public Game createGame(GameRequest gameRequest) {
        PlayerEntity player = playerService.getById(gameRequest.getPlayerId());

        GameEntity game = gameRepository.save(new GameEntity(player, GameStatus.CREATED));

        if(game == null) {
            throw new RuntimeException(String.valueOf(ErrorMessage.CREATE_GAME_ERROR));
        }

        return new Game(game);
    }

    public List<Game> getGames() {
        List<GameEntity> games = gameRepository.findAll();

        return games
                .stream()
                .map(Game::new)
                .collect(Collectors.toList());
    }

    public Game startGame(Long gameId, GameRequest gameRequest) {
        GameEntity gameEntity = getById(gameId);

        if(gameEntity.getStatus().equals(GameStatus.STARTED)) {
            throw new GameStartedException(gameId);
        }

        PlayerEntity player2 = playerService.getById(gameRequest.getPlayerId());

        Game game = new Game(gameEntity);
        game.start(player2);

        gameRepository.save(gameEntity);

        return game;
    }

    public GameEntity getById(Long gameId) {
        GameEntity game = gameRepository.findOne(gameId);

        if(game == null) {
            throw new GameNotFoundException(gameId);
        }

        return game;
    }
}
