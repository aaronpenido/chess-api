package com.chessmasters.chessapi.repositories;

import com.chessmasters.chessapi.entities.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<Player, Long> {
}
