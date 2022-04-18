package com.example.NumberGame.models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
public class GameStateLevel {
    @Id
    @GeneratedValue()
    private Long id ;
    private int gameState ;
    private int personalBest ;
    boolean completed ;
}
