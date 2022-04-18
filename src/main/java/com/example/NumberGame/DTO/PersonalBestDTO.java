package com.example.NumberGame.DTO;

import com.example.NumberGame.models.GameState;
import com.example.NumberGame.models.PersonalBest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonalBestDTO {
    private int  id ;
    private int min ;
    private int s ;
    private LocalDate createdAt ;
    private String name ;
    Set<GameState> state  = new HashSet<>()  ;
    public  PersonalBestDTO(PersonalBest personalBest) {
        this.id = personalBest.getId() ;
        this.min = personalBest.getMin() ;
        this.s = personalBest.getS() ;
        this.createdAt = personalBest.getCreatedAt() ;
        this.name = personalBest.getName() ;
        this.state = personalBest.getState().stream().map((gameState) -> {
            return  new GameState(gameState.getGameId(),gameState.isCompleted(),null) ;
        }).collect(Collectors.toSet()); ;
    }
}
