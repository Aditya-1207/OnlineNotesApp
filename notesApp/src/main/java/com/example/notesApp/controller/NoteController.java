package com.example.notesApp.controller;

import com.example.notesApp.exception.ResourceNotFoundException;
import com.example.notesApp.model.Notes;
import com.example.notesApp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

//Controller to create, retrieve, Update, delete a note
@RestController //Combination of @Controller and @ResponseBody
//@Controller defines this class is Controller to be bootstrapped while component scan
//@ResponseBody indicates the return value of method will be the response body
@RequestMapping(value = "/api")
//@RequestMapping defines the pattern of request URL
public class NoteController {


    @Autowired
    NoteRepository noteRepository;

    //Get all notes
    @GetMapping(value = "/notes")//@GetMapping handles the HTTP Get request and maps GET /notes/ URL to getAllNotes method
    public List<Notes> getAllNotes(){
        return noteRepository.findAll();
    }

    //Get a single note
    @GetMapping(value = "notes/{id}") //@GetMapping handles the HTTP Get request and maps GET /notes/{id} URL to getNotesById method
    public Notes getNotesById(@PathVariable(value = "id") long id){
        return noteRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Note","Id",id));

    }

    //Create a note
    @PostMapping (value = "/notes")//@PostMapping handles the HTTP POST request and maps POST /notes/ URL to createNote method
    public Notes createNote (@Valid @RequestBody Notes notes){
        //@Valid makes sure Request Body is valid as Title and Content of request is assigned to @NotNull annotation
        //@RequestBody binds request with method parameters
        return noteRepository.save(notes);
    }

    //Update a note
    @PutMapping(value = "notes/{id}")//@PutMapping handles the PUT request and maps PUT /notes/{Id} URl to updateNote method
    public Notes updateNote(@PathVariable(value = "id")long noteId, Notes UpdatedNote){
        Notes note = noteRepository.findById(noteId).orElseThrow(()->new ResourceNotFoundException("Note","Id",noteId));
        //Only Title and content are eligible for update
        note.setTitle(UpdatedNote.getTitle());
        note.setContent(UpdatedNote.getContent());

        return noteRepository.save(note);
    }

    //Delete a note
    @DeleteMapping(value = "notes/{id}")//@DeleteMapping handles DELETE request and maps DELETE notes/{id} to deleteNote method
    public void deleteNote(@PathVariable(value = "id")long id){
        Notes note = noteRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Note","Id",id));
        noteRepository.delete(note);

    }

}
