package com.springit.workshop.spring.service.impl;

import com.springit.workshop.spring.exception.SpringBusinessException;
import com.springit.workshop.spring.model.Note;
import com.springit.workshop.spring.repository.NoteRepository;
import com.springit.workshop.spring.service.NoteService;
import com.springit.workshop.spring.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final UserService userService;
    private final NoteRepository noteRepository;

    @Override
    public Note find(final Long id) throws SpringBusinessException {
        return noteRepository.findByIdAndOwner(id, userService.getCurrentUser())
                .orElseThrow(() -> new SpringBusinessException("Note not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Note findPublic(Long id) throws SpringBusinessException {
        Optional<Note> note = noteRepository.findById(id);

        if (note.isEmpty() || !note.get().getIsPublic()) {
            throw new SpringBusinessException("Note not found", HttpStatus.NOT_FOUND);
        }

        return note.get();
    }

    @Override
    public List<Note> findAll() {
        return noteRepository.findAllByOwner(userService.getCurrentUser())
                .stream()
                .map(Note::getNoteWithShortContent)
                .collect(Collectors.toList());
    }

    @Override
    public Note create(final Note note) {
        note.setId(null);
        note.setOwner(userService.getCurrentUser());
        return createOrUpdateNote(note);
    }

    @Override
    public Note update(final Note note) throws SpringBusinessException {
        Note foundNote = noteRepository.findByIdAndOwner(note.getId(), userService.getCurrentUser())
                .orElseThrow(() -> new SpringBusinessException("Note not found", HttpStatus.NOT_FOUND));

        if (note.getContent() != null) {
            foundNote.setContent(note.getContent());
        }

        if (note.getIsPublic() != null) {
            foundNote.setIsPublic(note.getIsPublic());
        }

        return createOrUpdateNote(foundNote);
    }

    @Override
    public void remove(final Long id) {
        noteRepository.removeByIdAndOwner(id, userService.getCurrentUser());
    }

    private Note createOrUpdateNote(Note note) {
        return noteRepository.save(note);
    }
}