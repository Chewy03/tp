package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class TagValidatorTest {
    private TagValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new TagValidator();
    }

    @Test
    public void validate_null_returnsInvalid() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("tag", result.errors().get(0).field());
        assertEquals("Tags must be 1-20 characters with letters, numbers, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_emptyTag_returnsInvalid() {
        ValidationResult result = validator.validate("");
        assertFalse(result.valid());
        assertEquals("tag", result.errors().get(0).field());
        assertEquals("Tags must be 1-20 characters with letters, numbers, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_tooLongTag_returnsInvalid() {
        String longTag = "a".repeat(21);
        ValidationResult result = validator.validate(longTag);
        assertFalse(result.valid());
        assertEquals("tag", result.errors().get(0).field());
        assertEquals("Tags must be 1-20 characters with letters, numbers, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidCharacters_returnsInvalid() {
        ValidationResult result = validator.validate("Tag@123");
        assertFalse(result.valid());
        assertEquals("tag", result.errors().get(0).field());
        assertEquals("Tags must be 1-20 characters with letters, numbers, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_validTag_returnsValid() {
        ValidationResult result = validator.validate("Tag-123");
        assertTrue(result.valid());
    }
}
