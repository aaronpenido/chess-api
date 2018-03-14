package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.request.PlayerRequest;
import com.chessmasters.chessapi.response.PlayerResponse;
import com.chessmasters.chessapi.services.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class PlayerController {

    private PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/players", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerResponse createPlayer(@RequestBody PlayerRequest playerRequest) {
        return new PlayerResponse(playerService.createPlayer(playerRequest));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/players")
    public List<PlayerResponse> listPlayers() {
        List<PlayerResponse> responseList = playerService.getPlayers()
                .stream()
                .map(PlayerResponse::new)
                .collect(Collectors.toList());

        return responseList;
    }
}
