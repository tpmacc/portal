package tpmacc.notes.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import tpmacc.notes.domain.Note;

import static org.assertj.core.api.Assertions.assertThat;

@JsonTest
class NoteJsonTests {

    @Autowired
    private JacksonTester<Note> json;

    @Test
    void testSerialize() throws Exception {
        var note = new Note("Title", "This is a note");
        var jsonContent = json.write(note);
        assertThat(jsonContent).extractingJsonPathStringValue("@.title")
                .isEqualTo(note.title());
        assertThat(jsonContent).extractingJsonPathStringValue("@.content")
                .isEqualTo(note.content());
    }

    @Test
    void testDeserialize() throws Exception {
        var content = """
        {
            "title": "Title",
            "content": "This is a note"
        }
        """;
        assertThat(json.parse(content))
                .usingRecursiveComparison()
                .isEqualTo(new Note("Title", "This is a note"));
    }
}