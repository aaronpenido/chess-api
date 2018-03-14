package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import com.chessmasters.chessapi.services.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class GameController {

    private GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/games",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResponse createGame(@RequestBody GameRequest request) {
        return new GameResponse(gameService.createGame(request));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/games")
    public List<GameResponse> listGames() {
        List<GameResponse> responseList = gameService.getGames()
                .stream()
                .map(GameResponse::new)
                .collect(Collectors.toList());

        return responseList;
    }
}
