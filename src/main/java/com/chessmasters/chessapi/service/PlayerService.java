package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.Player;
import com.chessmasters.chessapi.repository.PlayerRepository;
import com.chessmasters.chessapi.request.PlayerRequest;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    public PlayerService(PlayerRepository repository) {
        this.repository = repository;
    }

    public Player registerPlayer(PlayerRequest playerRequest) {
        Player player = new Player(playerRequest);
        return repository.save(player);
    }
}
