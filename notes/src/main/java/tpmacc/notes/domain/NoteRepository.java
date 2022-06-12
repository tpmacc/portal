package tpmacc.notes.domain;

import java.util.Optional;

public interface NoteRepository {
    Iterable<Note> findAll();
    Optional<Note> findByTitle(String isbn);
    boolean existsByTitle(String isbn);
    Note save(Note book);
    void deleteByIsbn(String isbn);
}