package com.example.notesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController
@CrossOrigin(origins = "https://task-app-frontend-sepia.vercel.app/")
//@CrossOrigin(origins = "http://localhost:5173/")
@EnableMongoRepositories(basePackages = "com.example.notesapp.repository")

public class NotesappApplication {

	@RequestMapping("/")
	public String home() {
		return "Dockerizing Spring Boot Application";
	}

	public static void main(String[] args) {
		SpringApplication.run(NotesappApplication.class, args);
	}
}



