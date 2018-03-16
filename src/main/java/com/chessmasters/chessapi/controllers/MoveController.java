package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.response.MoveResponse;
import com.chessmasters.chessapi.services.MoveService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MoveController {

    private MoveService moveService;

    public MoveController(MoveService moveService) {
        this.moveService = moveService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/games/{gameId}/moves",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public MoveResponse createMove(@PathVariable Long gameId) {
        return new MoveResponse(moveService.createMove(gameId));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/games/{gameId}/moves")
    public List<MoveResponse> listMoves(@PathVariable Long gameId) {

        return moveService.getMoves(gameId)
                .stream()
                .map(MoveResponse::new)
                .collect(Collectors.toList());
    }
}
