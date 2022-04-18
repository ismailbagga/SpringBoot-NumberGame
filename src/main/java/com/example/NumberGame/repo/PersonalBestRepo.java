package com.example.NumberGame.repo;

import com.example.NumberGame.models.PersonalBest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PersonalBestRepo extends JpaRepository<PersonalBest,Long> {
    @Query("SELECT  pb FROM PersonalBest as pb" +
            " WHERE  pb.name in ?1 Order By pb.min DESC ,  pb.s DESC ")
    List<PersonalBest> findByNamesInOrderByMinAndSCustom(List<String> name) ;
    Optional<PersonalBest> findByName(String name ) ;
}
