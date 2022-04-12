package com.example.NumberGame.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PersonalBest {
    @Id
    @GeneratedValue()
    private Long  id ;
    private int min ;
    private int s ;
    private LocalDate createdAt ;
    @Column(unique = true)
    private String email ;
    private String name ;

    @PrePersist()
    void  setCreateAtDate() {
        createdAt = LocalDate.now() ;
    }
}
