package com.example.notesapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.example.notesapp.repository")

public class NotesappApplication {
	public static void main(String[] args) {
		SpringApplication.run(NotesappApplication.class, args);
	}
}



