package com.example.notesapp.service;


import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    private static final Logger logger = LoggerFactory.getLogger(NoteService.class);

    @Autowired
    private NoteRepository noteRepository;

    public Note saveNote(Note note) {
        try {
            return noteRepository.save(note);
        } catch (Exception e) {
            logger.error("Error saving note", e);
            throw e;
        }
    }

    public List<Note> getAllNotes() {
        try {
            return noteRepository.findAll();
        } catch (Exception e) {
            logger.error("Error fetching all notes", e);
            throw e;
        }
    }

    public Optional<Note> getNoteById(String id) {
        try {
            return noteRepository.findById(id);
        } catch (Exception e) {
            logger.error("Error fetching note by id: " + id, e);
            throw e;
        }
    }

    public Optional<Note> updateNote(String id, Note noteDetails) {
        try {
            return noteRepository.findById(id).map(note -> {
                note.setTitle(noteDetails.getTitle());
                note.setContent(noteDetails.getContent());
                return noteRepository.save(note);
            });
        } catch (Exception e) {
            logger.error("Error updating note with id: " + id, e);
            throw e;
        }
    }

    public boolean deleteNoteById(String id) {
        try {
            if (noteRepository.existsById(id)) {
                noteRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            logger.error("Error deleting note with id: " + id, e);
            throw e;
        }
    }
}









//old code

//import com.example.notesapp.model.Note;
//import com.example.notesapp.repository.NoteRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class NoteService {
//
//    @Autowired
//    private NoteRepository noteRepository;
//
//    // Create or update a note
//    public Note saveNote(Note note) {
//        return noteRepository.save(note);
//    }
//
//    // Get all notes
//    public List<Note> getAllNotes() {
//        return noteRepository.findAll();
//    }
//
//    // Get note by id
//    public Optional<Note> getNoteById(String id) {
//        return noteRepository.findById(id);
//    }
//
//    // Update note by id
//    public Optional<Note> updateNote(String id, Note noteDetails) {
//        return noteRepository.findById(id).map(note -> {
//            note.setTitle(noteDetails.getTitle());
//            note.setContent(noteDetails.getContent());
//            return noteRepository.save(note);
//        });
//    }
//
//    // Delete note by id
//    public boolean deleteNoteById(String id) {
//        if (noteRepository.existsById(id)) {
//            noteRepository.deleteById(id);
//            return true;
//        }
//        return false;
//    }
//
//}