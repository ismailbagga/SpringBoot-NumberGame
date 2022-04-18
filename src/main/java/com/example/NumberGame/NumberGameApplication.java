package com.example.NumberGame;

import com.example.NumberGame.models.GameState;
import com.example.NumberGame.repo.GameStateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class NumberGameApplication {

	public static void main(String[] args) {
		SpringApplication.run(NumberGameApplication.class, args);
	}


}
