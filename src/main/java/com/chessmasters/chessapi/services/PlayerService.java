package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.exceptions.PlayerNotFoundException;
import com.chessmasters.chessapi.models.Player;
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

    public Player createPlayer(PlayerRequest playerRequest) {
        PlayerEntity player = repository.save(new PlayerEntity(playerRequest.getName()));

        if(player != null) {
            return new Player(player);
        }

        return null;
    }

    public List<Player> getPlayers() {
        List<PlayerEntity> players = repository.findAll();

        return players
                .stream()
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public PlayerEntity getById(Long playerId) {
        PlayerEntity player = repository.findOne(playerId);

        if(player == null) {
            throw new PlayerNotFoundException(playerId);
        }

        return player;
    }
}
