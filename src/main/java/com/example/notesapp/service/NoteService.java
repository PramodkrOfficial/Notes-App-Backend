package com.example.notesapp.service;


import com.example.notesapp.model.Note;
import com.example.notesapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoteService {

    @Autowired
    private NoteRepository noteRepository;

    // Create or update a note
    public Note saveNote(Note note) {
        return noteRepository.save(note);
    }

    // Get all notes
    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    // Get note by id
    public Optional<Note> getNoteById(String id) {
        return noteRepository.findById(id);
    }

    // Update note by id
    public Optional<Note> updateNote(String id, Note noteDetails) {
        return noteRepository.findById(id).map(note -> {
            note.setTitle(noteDetails.getTitle());
            note.setContent(noteDetails.getContent());
            return noteRepository.save(note);
        });
    }

    // Delete note by id
    public boolean deleteNoteById(String id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
            return true;
        }
        return false;
    }
}