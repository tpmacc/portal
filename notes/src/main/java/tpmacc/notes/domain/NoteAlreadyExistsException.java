package tpmacc.notes.domain;

public class NoteAlreadyExistsException extends RuntimeException {
    public NoteAlreadyExistsException(String title) {
        super("A note with title " + title + " already exists.");
    }
}