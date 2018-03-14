package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.enums.GameStatus;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class GameController {

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/games",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResponse createGame(GameRequest request) {
        return new GameResponse(1L, GameStatus.CREATED);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/games")
    public List<GameResponse> listGames() {
        List<GameResponse> responseList = new ArrayList<>();
        responseList.add(new GameResponse(1L, GameStatus.CREATED));

        return responseList;
    }
}
