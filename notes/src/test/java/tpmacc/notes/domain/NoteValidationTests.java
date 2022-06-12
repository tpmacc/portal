package tpmacc.notes.domain;

        import org.junit.jupiter.api.BeforeAll;
        import org.junit.jupiter.api.Test;
        import tpmacc.notes.domain.Note;

        import javax.validation.ConstraintViolation;
        import javax.validation.Validation;
        import javax.validation.Validator;
        import javax.validation.ValidatorFactory;
        import java.util.Set;
        import static org.assertj.core.api.Assertions.assertThat;

class NoteValidationTests {

    private static Validator validator;

    @BeforeAll
    static void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void whenAllFieldsCorrectThenValidationSucceeds() {
        var note = new Note("Title", "This is a note.");
        Set<ConstraintViolation<Note>> violations = validator.validate(note);
        assertThat(violations).isEmpty();
    }

    @Test
    void whenIsbnDefinedButIncorrectThenValidationFails() {
        var book = new Note("", "Author");
        Set<ConstraintViolation<Note>> violations = validator.validate(book);
        assertThat(violations).hasSize(1);
        assertThat(violations.iterator().next().getMessage())
                .isEqualTo("Note title must not be blank");
    }
}