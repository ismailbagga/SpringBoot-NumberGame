package com.example.NumberGame.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;


@Entity
@Table
@Data
@NoArgsConstructor
public class GameState  implements  Serializable{
    @Id
    @GeneratedValue
    private int id ;
    private int gameId;
    private boolean completed  ;
    @ManyToOne()
    PersonalBest personalBest  ;
    public  GameState(int gameId, boolean completed,PersonalBest personalBest
    ) {
        this.gameId = gameId ;
        this.completed = completed ;
        this.personalBest = personalBest ;
    }
    @Override
    public boolean equals(Object obj) {
        if ( obj == null) return  false ;
        if (! ( obj instanceof GameState  )) return  false ;
        return (gameId == ((GameState) obj).getGameId()  )  ;
    }
}