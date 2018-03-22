package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.Player;
import com.chessmasters.chessapi.exceptions.PlayerNotFoundException;
import com.chessmasters.chessapi.models.PlayerModel;
import com.chessmasters.chessapi.repositories.PlayerRepository;
import com.chessmasters.chessapi.request.PlayerRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public PlayerModel createPlayer(PlayerRequest playerRequest) {
        Player player = repository.save(new Player(playerRequest.getName()));

        if(player != null) {
            return new PlayerModel(player);
        }

        return null;
    }

    public List<PlayerModel> getPlayers() {
        List<Player> players = repository.findAll();

        return players
                .stream()
                .map(PlayerModel::new)
                .collect(Collectors.toList());
    }

    public Player getById(Long playerId) {
        Player player = repository.findOne(playerId);

        if(player == null) {
            throw new PlayerNotFoundException(playerId);
        }

        return player;
    }
}
