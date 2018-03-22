package com.chessmasters.chessapi.controllers;

import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.models.Player;
import com.chessmasters.chessapi.request.PlayerRequest;
import com.chessmasters.chessapi.response.PlayerResponse;
import com.chessmasters.chessapi.services.PlayerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerControllerTest {

    @Mock
    private PlayerService service;

    @InjectMocks
    private PlayerController controller;

    @Test
    public void createPlayer() {
        final String name = "Player Name";
        PlayerRequest playerRequest = new PlayerRequest(name);

        PlayerEntity player = new PlayerEntity(name);
        when(service.createPlayer(playerRequest)).thenReturn(new Player(player));

        PlayerResponse playerResponse = controller.createPlayer(playerRequest);

        assertThat(playerResponse).isNotNull();
        assertThat(playerResponse.getName()).isEqualTo(playerRequest.getName());
    }
}