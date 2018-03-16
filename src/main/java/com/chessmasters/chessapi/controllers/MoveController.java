package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.response.MoveResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MoveController {

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/games/{gameId}/moves")
    public List<MoveResponse> listGames(@PathVariable Long gameId) {
        return new ArrayList<>();
    }
}
