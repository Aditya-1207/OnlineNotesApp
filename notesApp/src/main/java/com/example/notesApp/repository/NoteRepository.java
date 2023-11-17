package com.example.notesApp.repository;

import com.example.notesApp.model.Notes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository//Asks Spring to bootstrap the repository during component scan
public interface NoteRepository extends JpaRepository<Notes, Long> {

}
