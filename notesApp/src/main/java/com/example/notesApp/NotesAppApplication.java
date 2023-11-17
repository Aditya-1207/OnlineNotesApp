package com.example.notesApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NotesAppApplication {

    public static void main(String[] args) {

        SpringApplication.run(NotesAppApplication.class, args);
    }

}
