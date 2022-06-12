package tpmacc.notes.domain;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class NoteService {
    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public Iterable<Note> viewNoteList() {
        return noteRepository.findAll();
    }

    public Note viewNoteDetails(String title) {
        return noteRepository.findByTitle(title)
                .orElseThrow(() -> new NoteNotFoundException(title));
    }

    public Note addNoteToCatalog(Note note) {
        if (noteRepository.existsByTitle(note.title())) {
            throw new NoteAlreadyExistsException(note.title());
        }
        return noteRepository.save(note);
    }

    public void removeNoteFromCatalog(String isbn) {
        noteRepository.deleteByIsbn(isbn);
    }

    public Note editNoteDetails(String title, Note note) {
        Optional<Note> existingNote = noteRepository.findByTitle(title);
        if (existingNote.isEmpty()) {
            return addNoteToCatalog(note);
        }
        var noteToUpdate = new Note(
                existingNote.get().title(),
                note.content());
        return noteRepository.save(noteToUpdate);
    }
}