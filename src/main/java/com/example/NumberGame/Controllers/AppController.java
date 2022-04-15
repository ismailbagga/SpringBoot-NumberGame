package com.example.NumberGame.Controllers;


import com.example.NumberGame.models.PersonalBest;
import com.example.NumberGame.repo.PersonalBestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping(
        value = "/api/v1/personalBest"
)
@RestController
public class AppController {
    @GetMapping("/friends")
    public ResponseEntity<?> getFriendsPersonalBest(@RequestBody getPersonalBestDto emails) {

        return new ResponseEntity(appService.retrieveFriendsPersonBest(emails.getEmails()), HttpStatus.ACCEPTED) ;
    }

    @Autowired
    private AppService appService  ;
    @GetMapping("/friend")
    public ResponseEntity<?> getFriendPersonalBest(@RequestParam(value = "email") String  email) {
        System.out.println("email is : "+email);
        return new ResponseEntity(appService.retrieveFriendPersonBest(email), HttpStatus.ACCEPTED) ;
    }
    @PostMapping("/save")
    public ResponseEntity<?> savePersonalBest(@RequestBody PersonalBest personalBest) {

        return new ResponseEntity<>(appService.savePersonalBest(personalBest), HttpStatus.ACCEPTED) ;
    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllPersonalBest() {

        return new ResponseEntity(appService.getAll(), HttpStatus.ACCEPTED) ;
    }
    @DeleteMapping("/all")
    public ResponseEntity<?> deleteAll() {
        return  ResponseEntity.ok(appService.deleteAll()) ;
    }
}
