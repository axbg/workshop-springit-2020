package com.springit.workshop.spring.controller;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.Note;
import com.springit.workshop.spring.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.rest.prefix}/note")
public class NoteController {

    private final NoteService noteService;

    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return ResponseEntity.ok(noteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Note> get(@PathVariable Long id) throws SpringBusinessException {
        return ResponseEntity.ok(noteService.find(id));
    }

    @GetMapping("/public/{id}")
    public ResponseEntity<Note> getPublic(@PathVariable Long id) throws SpringBusinessException {
        return ResponseEntity.ok(noteService.findPublic(id));
    }

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.create(note).getNoteWithShortContent());
    }

    @PutMapping
    public ResponseEntity<Note> update(@RequestBody Note note) throws SpringBusinessException {
        return ResponseEntity.ok(noteService.update(note));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        noteService.remove(id);
        return ResponseEntity.ok().build();
    }
}