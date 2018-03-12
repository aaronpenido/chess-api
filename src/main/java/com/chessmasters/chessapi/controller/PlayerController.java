package com.chessmasters.chessapi.controller;

import com.chessmasters.chessapi.entity.Player;
import com.chessmasters.chessapi.request.PlayerRequest;
import com.chessmasters.chessapi.response.PlayerResponse;
import com.chessmasters.chessapi.service.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlayerController {

    private PlayerService service;

    public PlayerController(PlayerService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/players", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public PlayerResponse registerPlayer(@RequestBody PlayerRequest playerRequest) {
        Player player = service.registerPlayer(playerRequest);
        return new PlayerResponse(player);
    }
}
