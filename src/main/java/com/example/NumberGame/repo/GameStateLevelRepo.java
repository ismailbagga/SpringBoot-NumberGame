package com.example.NumberGame.repo;

import com.example.NumberGame.models.GameState;
import com.example.NumberGame.models.GameStateLevel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GameStateLevelRepo extends JpaRepository<GameStateLevel,Long> {



}
