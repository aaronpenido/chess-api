package com.chessmasters.chessapi.repository;

import com.chessmasters.chessapi.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends CrudRepository<Game, Long> {
}