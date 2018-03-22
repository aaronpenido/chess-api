package com.chessmasters.chessapi.repositories;

import com.chessmasters.chessapi.entities.PlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlayerRepository extends JpaRepository<PlayerEntity, Long> {
}
