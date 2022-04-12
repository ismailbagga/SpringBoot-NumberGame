package com.example.NumberGame.Controllers;

import com.example.NumberGame.models.PersonalBest;
import com.example.NumberGame.repo.PersonalBestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class AppService {
    @Autowired
    private PersonalBestRepo personalBestRepo ;
    @Transactional
    @Modifying
    public RequestState  savePersonalBest(PersonalBest personalBest) {
        if ( personalBest.getEmail() == null || personalBest.getEmail().isEmpty()
         ) {
           return  new RequestState(HttpStatus.BAD_REQUEST,"some important wast not specfied") ;
        }
        Optional<PersonalBest> list = personalBestRepo.findByEmail(personalBest.getEmail()) ;
        if (list.isPresent()) {
            list.get().setMin(personalBest.getMin());
            list.get().setS(personalBest.getS());
            return  new RequestState(HttpStatus.FOUND,"personal best found update was done") ;
        }
        else {
            personalBestRepo.save(personalBest) ;
            return  new RequestState(HttpStatus.CREATED,"personal best was created") ;

        }

    }

    public List<PersonalBest> retrieveFriendsPersonBest(List<String> emails) {
        return  personalBestRepo.findByEmailInOrderByMinAndSCustom(emails) ;
    }

    public PersonalBest retrieveFriendPersonBest(String email) {
        Optional<PersonalBest> personalBest = personalBestRepo.findByEmail(email) ;
        if (personalBest.isEmpty()) return null ;
        return  personalBest.get() ;
    }
}
