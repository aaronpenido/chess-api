package com.chessmasters.chessapi.controller;

import com.chessmasters.chessapi.entity.Game;
import com.chessmasters.chessapi.request.GameRequest;
import com.chessmasters.chessapi.response.GameResponse;
import com.chessmasters.chessapi.service.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    private GameService service;

    public GameController(GameService service) {
        this.service = service;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/games",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResponse registerGame(@RequestBody GameRequest gameRequest) {
        Game game = service.registerGame(gameRequest);
        return new GameResponse(game);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/games/{id}/start",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResponse startGame(@PathVariable("id") Long id, @RequestBody GameRequest gameRequest) {
        Game game = service.startGame(id, gameRequest);
        return new GameResponse(game);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/games/{id}/move",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public GameResponse movePiece(@PathVariable("id") Long id, @RequestBody GameRequest gameRequest) {
        Game game = service.movePiece(id, gameRequest);
        return new GameResponse(game);
    }
}
