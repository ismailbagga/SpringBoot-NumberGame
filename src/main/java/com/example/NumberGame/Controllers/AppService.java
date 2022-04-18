package com.example.NumberGame.Controllers;

import com.example.NumberGame.DTO.PersonalBestDTO;
import com.example.NumberGame.models.GameState;
import com.example.NumberGame.models.PersonalBest;
import com.example.NumberGame.repo.GameStateRepo;
import com.example.NumberGame.repo.PersonalBestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppService {
    @Autowired
    private PersonalBestRepo personalBestRepo ;
    @Autowired
    private GameStateRepo gameStateRepo ;
    @Transactional
    @Modifying
    public RequestState  savePersonalBest(PersonalBest personalBest) {
        if ( personalBest.getName() == null || personalBest.getName().isEmpty()
         ) {
           return  new RequestState(HttpStatus.BAD_REQUEST,"some important info wast not specified") ;
        }
        Optional<PersonalBest> list = personalBestRepo.findByName(personalBest.getName()) ;
        if (list.isPresent()) {
            list.get().setMin(personalBest.getMin());
            list.get().setS(personalBest.getS());
            return  new RequestState(HttpStatus.FOUND,"personal best found update was done") ;
        }
        else {
            var pb = personalBestRepo.save(personalBest) ;
            gameStateRepo.saveAll(List.of(
                    new GameState(1,false,pb),
                    new GameState(21,false,pb),
                    new GameState(41,false,pb),
                    new GameState(61,false,pb))) ;
            return  new RequestState(HttpStatus.CREATED,"personal best was created") ;

        }

    }

    public List<PersonalBestDTO> retrieveFriendsPersonBest(List<String> names) {
        List<PersonalBest> personalBests =
                personalBestRepo.findByNamesInOrderByMinAndSCustom(names) ;
        return   personalBests.stream().
                map(PersonalBestDTO::new)
                .collect(Collectors.toList()) ;
    }

    public PersonalBestDTO retrieveFriendPersonBest(String name) {
        Optional<PersonalBest> personalBest = personalBestRepo.findByName(name) ;
        if (personalBest.isEmpty()) return null ;
        return  new PersonalBestDTO(personalBest.get()) ;
    }

    public List<PersonalBest> getAll() {
        return personalBestRepo.findAll() ;
    }

    public Boolean deleteAll() {
        personalBestRepo.deleteAll();
    return true ;
    }

    @Modifying
    @Transactional
    public RequestState completeLevel(String name, int gameId) {
            Optional<PersonalBest> personalBest = personalBestRepo.findByName(name) ;
            if ( personalBest.isEmpty()) {
                return  new RequestState(HttpStatus.BAD_REQUEST,"there no user") ;
            }
            Optional<GameState> game = gameStateRepo.findByGameIdCustom(gameId,name) ;
            if ( game.isEmpty()) {
                gameStateRepo.save(new GameState(gameId,true,personalBest.get())) ;
                return  new RequestState(HttpStatus.CREATED,"new level completed") ;
            }
            game.get().setCompleted(true);

            return  new RequestState(HttpStatus.FOUND,"update level state!") ;
    }
}
