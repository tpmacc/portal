package tpmacc.notes;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import tpmacc.notes.domain.Note;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NotesApplicationTests {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    void whenPostRequestThenNoteCreated() {
        var expectedNote = new Note("Title", "This is a note");

        webTestClient
                .post()
                .uri("/v1/notes")
                .bodyValue(expectedNote)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(Note.class).value(actualNote -> {
                    assert(actualNote !=null);
                    assert(actualNote.title()).equals(expectedNote.title());
                });
    }
}