package tpmacc.notes.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import tpmacc.notes.domain.Note;
import tpmacc.notes.domain.NoteService;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/notes")
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @GetMapping
    public Iterable<Note> get() {
        return noteService.viewNoteList();
    }

    @GetMapping("{title}")
    public Note getByTitle(@PathVariable String title) {
        return noteService.viewNoteDetails(title);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Note post(@Valid @RequestBody Note note) {
        return noteService.addNoteToCatalog(note);
    }

    @DeleteMapping("{title}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable String isbn) {
        noteService.removeNoteFromCatalog(isbn);
    }

    @PutMapping("{title}")
    public Note put(@PathVariable String title, @Valid @RequestBody Note note) {
        return noteService.editNoteDetails(title, note);
    }
}