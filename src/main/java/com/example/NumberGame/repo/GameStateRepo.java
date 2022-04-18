package com.example.NumberGame.repo;

import com.example.NumberGame.models.GameState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GameStateRepo extends JpaRepository<GameState,Integer> {
   @Query(value = "SELECT gs FROM GameState gs WHERE gs.gameId = ?1 AND gs.personalBest.name = ?2 ")
    Optional<GameState> findByGameIdCustom(int gameId,String name);
}
