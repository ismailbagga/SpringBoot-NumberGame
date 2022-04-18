package com.example.NumberGame.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalBest {
    @Id
    @GeneratedValue()
    private int  id ;
    private int min ;
    private int s ;
    private LocalDate createdAt ;
    private String name ;
    @OneToMany(mappedBy = "personalBest")
    Set<GameState> state  = new HashSet<>()  ;

    @PrePersist()
    void  onPersist() {
        createdAt = LocalDate.now() ;
        state.addAll(List.of(
                new GameState(1,false,this),
                new GameState(21,false,this),
                new GameState(41,false,this),
                new GameState(61,false,this)
        )) ;
    }

}
