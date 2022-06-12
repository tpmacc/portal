package tpmacc.notes.persistence;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import tpmacc.notes.domain.Note;
import tpmacc.notes.domain.NoteRepository;

@Repository
public class InMemoryNoteRepository implements NoteRepository {
    private static final Map<String, Note> notes = new ConcurrentHashMap<>();

    @Override
    public Iterable<Note> findAll() {
        return notes.values();
    }

    @Override
    public Optional<Note> findByTitle(String isbn) {
        return existsByTitle(isbn) ? Optional.of(notes.get(isbn)) : Optional.empty();
    }

    @Override
    public boolean existsByTitle(String isbn) {
        return notes.get(isbn) != null;
    }

    @Override
    public Note save(Note note) {
        notes.put(note.title(), note);
        return note;
    }

    @Override
    public void deleteByIsbn(String isbn) {
        notes.remove(isbn);
    }
}