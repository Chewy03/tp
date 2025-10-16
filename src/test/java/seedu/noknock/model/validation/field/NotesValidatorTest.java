package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class NotesValidatorTest {
    private NotesValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new NotesValidator();
    }

    @Test
    public void validate_null_returnsValid() {
        ValidationResult result = validator.validate(null);
        assertTrue(result.valid());
    }

    @Test
    public void validate_emptyNotes_returnsValid() {
        ValidationResult result = validator.validate("");
        assertTrue(result.valid());
    }

    @Test
    public void validate_tooLongNotes_returnsInvalid() {
        String longNotes = "a".repeat(201);
        ValidationResult result = validator.validate(longNotes);
        assertFalse(result.valid());
        assertEquals("notes", result.errors().get(0).field());
        assertEquals("Notes cannot exceed 200 characters", result.errors().get(0).message());
    }

    @Test
    public void validate_validNotes_returnsValid() {
        String validNotes = "This is a valid note.";
        ValidationResult result = validator.validate(validNotes);
        assertTrue(result.valid());
    }
}
