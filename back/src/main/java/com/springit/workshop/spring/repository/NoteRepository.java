package com.springit.workshop.spring.repository;

import com.springit.workshop.spring.model.Note;
import com.springit.workshop.spring.model.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepository extends CrudRepository<Note, Long> {
    Optional<Note> findByIdAndOwner(Long id, User owner);

    List<Note> findAllByOwner(User owner);

    void removeByIdAndOwner(Long id, User owner);
}