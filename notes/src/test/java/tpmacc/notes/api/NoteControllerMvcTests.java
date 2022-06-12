package tpmacc.notes.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tpmacc.notes.domain.NoteNotFoundException;
import tpmacc.notes.domain.NoteService;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(NoteController.class)
class NoteControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteService noteService;

    @Test
    void whenGetNoteNotExistingThenShouldReturn404() throws Exception {
        String title = "Does not exist";
        given(noteService.viewNoteDetails(title)).willThrow(NoteNotFoundException.class);
        mockMvc
                .perform(get("/v1/notes/" + title))
                .andExpect(status().isNotFound());
    }
}