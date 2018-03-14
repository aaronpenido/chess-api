package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/games",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResponse createGame(GameRequest request) {
        return new GameResponse(GameStatus.CREATED);
    }
}
