package com.chessmasters.chessapi.controller;

import com.chessmasters.chessapi.Player;
import com.chessmasters.chessapi.request.PlayerRequest;
import com.chessmasters.chessapi.response.PlayerResponse;
import com.chessmasters.chessapi.service.PlayerService;
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
        PlayerRequest playerRequest = createPlayerRequest();

        PlayerResponse playerResponse = controller.registerPlayer(playerRequest);

        assertThat(playerResponse).isNotNull();
        assertThat(playerResponse.getName()).isEqualTo(playerRequest.getName());
    }

    private PlayerRequest createPlayerRequest() {
        final String name = "Player Name";
        PlayerRequest playerRequest = new PlayerRequest(name);
        Player player = new Player(playerRequest);
        when(service.registerPlayer(playerRequest)).thenReturn(player);

        return playerRequest;
    }
}