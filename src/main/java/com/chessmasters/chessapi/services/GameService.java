package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Game;
import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.models.GameModel;
import com.chessmasters.chessapi.repositories.GameRepository;
import com.chessmasters.chessapi.request.GameRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameService {

    private final GameRepository repository;

    public GameService(GameRepository repository) {
        this.repository = repository;
    }

    public GameModel createGame(GameRequest gameRequest) {
        Game game = repository.save(new Game(GameStatus.CREATED));

        if(game != null) {
            return new GameModel(game);
        }

        return new GameModel();
    }

    public List<GameModel> getGames() {
        List<Game> games = repository.findAll();

        return games
                .stream()
                .map(GameModel::new)
                .collect(Collectors.toList());
    }
}
