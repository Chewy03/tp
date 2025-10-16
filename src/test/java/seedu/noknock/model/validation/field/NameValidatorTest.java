package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class NameValidatorTest {
    private NameValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new NameValidator();
    }

    @Test
    public void validate_null_returnsInvalid() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("name", result.errors().get(0).field());
        assertEquals("Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes",
            result.errors().get(0).message());
    }

    @Test
    public void validate_emptyName_returnsInvalid() {
        ValidationResult result = validator.validate("");
        assertFalse(result.valid());
        assertEquals("name", result.errors().get(0).field());
        assertEquals("Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes",
            result.errors().get(0).message());
    }

    @Test
    public void validate_tooLongName_returnsInvalid() {
        String longName = "a".repeat(51);
        ValidationResult result = validator.validate(longName);
        assertFalse(result.valid());
        assertEquals("name", result.errors().get(0).field());
        assertEquals("Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidCharacters_returnsInvalid() {
        ValidationResult result = validator.validate("John@Doe");
        assertFalse(result.valid());
        assertEquals("name", result.errors().get(0).field());
        assertEquals("Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes",
            result.errors().get(0).message());
    }

    @Test
    public void validate_validName_returnsValid() {
        ValidationResult result = validator.validate("John O'Connor-Smith");
        assertTrue(result.valid());
    }
}
