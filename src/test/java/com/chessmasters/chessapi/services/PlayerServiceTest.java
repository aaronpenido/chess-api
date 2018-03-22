package com.chessmasters.chessapi.services;

import com.chessmasters.chessapi.entities.PlayerEntity;
import com.chessmasters.chessapi.exceptions.PlayerNotFoundException;
import com.chessmasters.chessapi.models.Player;
import com.chessmasters.chessapi.repositories.PlayerRepository;
import com.chessmasters.chessapi.request.PlayerRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PlayerServiceTest {

    @InjectMocks
    private PlayerService service;
    @Mock
    private PlayerRepository playerRepository;

    @Test
    public void returnPlayerWithGeneratedId() {
        final Long id = 1L;
        final String name = "Player Name";
        PlayerRequest request = new PlayerRequest(name);
        PlayerEntity expectedPlayer = new PlayerEntity(name);
        ReflectionTestUtils.setField(expectedPlayer, "id", id);
        when(playerRepository.save(any(PlayerEntity.class))).thenReturn(expectedPlayer);

        Player player = service.createPlayer(request);

        assertThat(player.getId()).isEqualTo(id);
        assertThat(player.getName()).isEqualTo(name);
    }

    @Test
    public void savePlayerOnDatabase() {
        final String name = "Player Name";
        PlayerRequest request = new PlayerRequest(name);

        service.createPlayer(request);
        verify(playerRepository).save(any(PlayerEntity.class));
    }

    @Test
    public void getAllPlayersFromDatabase() {
        service.getPlayers();
        verify(playerRepository).findAll();
    }

    @Test
    public void getPlayerById() {
        final Long id = 1L;

        when(playerRepository.findOne(any(Long.class))).thenReturn(new PlayerEntity());

        PlayerEntity player = service.getById(id);

        assertThat(player).isNotNull();
    }

    @Test
    public void getByIdThrowExceptionWhenNotFound() {
        final Long id = 1L;

        when(playerRepository.findOne(id)).thenReturn(null);
        assertThatThrownBy(() -> service.getById(id))
                .isInstanceOf(PlayerNotFoundException.class);
    }
}