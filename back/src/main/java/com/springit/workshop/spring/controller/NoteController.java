package com.springit.workshop.spring.controller;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.Note;
import com.springit.workshop.spring.service.NoteService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("${app.rest.prefix}/note")
public class NoteController {

    private final NoteService noteService;

    @GetMapping("/{id}")
    public ResponseEntity<Note> get(@PathVariable Long id) throws SpringBusinessException {
        return ResponseEntity.ok(noteService.find(id));
    }

    @GetMapping
    public ResponseEntity<List<Note>> getAll() {
        return ResponseEntity.ok(noteService.findAll());
    }

    @PostMapping
    public ResponseEntity<Note> create(@RequestBody Note note) {
        return ResponseEntity.ok(noteService.create(note));
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