package com.springit.workshop.spring.service;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.Note;

import java.util.List;

public interface NoteService {
    Note find(Long id) throws SpringBusinessException;

    Note findPublic(Long id) throws SpringBusinessException;

    List<Note> findAll();

    Note create(Note note);

    Note update(Note note) throws SpringBusinessException;

    void remove(Long id);
}