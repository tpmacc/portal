package tpmacc.notes.domain;

public class NoteNotFoundException extends RuntimeException {
    public NoteNotFoundException(String title) {
        super("The note with title " + title + " was not found.");
    }
}