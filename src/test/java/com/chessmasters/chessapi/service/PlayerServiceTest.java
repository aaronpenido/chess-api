package com.chessmasters.chessapi.service;

import com.chessmasters.chessapi.entity.Player;
import com.chessmasters.chessapi.repository.PlayerRepository;
import com.chessmasters.chessapi.request.PlayerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static com.chessmasters.chessapi.enums.Color.WHITE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService service;
    @Mock
    private PlayerRepository repository;

    @Test
    public void returnPlayerWithGeneratedId() {
        final Long id = 1L;
        final String name = "Player Name";
        PlayerRequest request = new PlayerRequest(name);
        when(repository.save(any(Player.class))).thenReturn(new Player(WHITE, id, name));

        Player player = service.registerPlayer(request);

        assertThat(player.getId()).isEqualTo(id);
        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    public void savePlayerOnDatabase() {
        final String name = "Player Name";
        PlayerRequest request = new PlayerRequest(name);

        service.registerPlayer(request);
        verify(repository).save(any(Player.class));
    }
}