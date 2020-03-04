package com.springit.workshop.spring.repository;

import com.springit.workshop.spring.model.Note;
import com.springit.workshop.spring.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    Optional<Note> findById(Long id);

    Optional<Note> findByIdAndOwner(Long id, User owner);

    List<Note> findAllByOwner(User owner);

    void removeByIdAndOwner(Long id, User owner);
}