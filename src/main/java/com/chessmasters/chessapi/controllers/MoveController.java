package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.response.MoveResponse;
import com.chessmasters.chessapi.services.MoveService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class MoveController {

    private MoveService moveService;

    public MoveController(MoveService moveService) {
        this.moveService = moveService;
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
