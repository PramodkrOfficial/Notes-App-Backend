//package com.example.notesapp.controller;

//
//import com.example.notesapp.model.Note;
//import com.example.notesapp.repository.NoteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//import java.util.Optional;
//
//@RestController
//@RequestMapping("/api/notes")
//@CrossOrigin(origins = "*")  // Allow all origins for testing
//public class NoteController {
//
//    @Autowired
//    private NoteRepository noteRepository;
//
//    // Create a new note
//    @PostMapping
//    public Note createNote(@RequestBody Note note) {
//        return noteRepository.save(note);
//    }
//
//    // Get all notes
//    @GetMapping
//    public List<Note> getAllNotes() {
//        return noteRepository.findAll();
//    }
//
//    // Get note by id
//    @GetMapping("/{id}")
//    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
//        Optional<Note> note = noteRepository.findById(id);
//        if (note.isPresent()) {
//            return ResponseEntity.ok(note.get());
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Update note by id
//    @PutMapping("/{id}")
//    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody Note noteDetails) {
//        Optional<Note> optionalNote = noteRepository.findById(id);
//        if (optionalNote.isPresent()) {
//            Note note = optionalNote.get();
//            note.setTitle(noteDetails.getTitle());
//            note.setContent(noteDetails.getContent());
//            Note updatedNote = noteRepository.save(note);
//            return ResponseEntity.ok(updatedNote);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Delete note by id
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
//        Optional<Note> optionalNote = noteRepository.findById(id);
//        if (optionalNote.isPresent()) {
//            noteRepository.deleteById(id);
//            return ResponseEntity.noContent().build();
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }
//}



package com.example.notesapp.controller;


import com.example.notesapp.model.Note;
import com.example.notesapp.service.NoteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



//@CrossOrigin(origins = "https://task-app-frontend-sepia.vercel.app/")
@RestController
@RequestMapping("/api/notes")
@CrossOrigin(origins = "*")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping
    public Note createNote(@RequestBody Note note) {
        return noteService.saveNote(note);
    }

    @GetMapping
    public List<Note> getAllNotes() {
        return noteService.getAllNotes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable String id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable String id, @RequestBody Note noteDetails) {
        return noteService.updateNote(id, noteDetails)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable String id) {
        if (noteService.deleteNoteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}