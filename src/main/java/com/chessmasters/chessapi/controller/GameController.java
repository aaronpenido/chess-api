package com.chessmasters.chessapi.controller;

import com.chessmasters.chessapi.Game;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import com.chessmasters.chessapi.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    private GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/games", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResponse registerGame(@RequestBody GameRequest gameRequest) {
        Game game = service.registerGame(gameRequest);
        return new GameResponse(game);
    }
}
