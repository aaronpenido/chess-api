package com.chessmasters.chessapi.repository;

import com.chessmasters.chessapi.Player;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends CrudRepository<Player, Long> {
}

