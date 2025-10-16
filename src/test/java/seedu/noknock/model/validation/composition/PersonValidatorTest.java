package seedu.noknock.model.validation.composition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class PersonValidatorTest {
    private PersonValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PersonValidator();
    }

    @Test
    public void validate_allValid_returnsValid() {
        ValidationResult result =
            validator.validate("John Doe", "A1", "S1234567D", Arrays.asList("diabetic", "allergy"));
        assertTrue(result.valid());
    }

    @Test
    public void validate_invalidName_returnsInvalid() {
        ValidationResult result = validator.validate("", "A1", "S1234567D", List.of("diabetic"));
        assertFalse(result.valid());
        assertEquals("name", result.errors().get(0).field());
        assertEquals("Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidWard_returnsInvalid() {
        ValidationResult result = validator.validate("John Doe", "1A", "S1234567D", List.of("diabetic"));
        assertFalse(result.valid());
        assertEquals("ward", result.errors().get(0).field());
        assertEquals("Ward must start with a letter and followed by one or more digits",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidIc_returnsInvalid() {
        ValidationResult result = validator.validate("John Doe", "A1", "123", List.of("diabetic"));
        assertFalse(result.valid());
        assertEquals("ic", result.errors().get(0).field());
        assertEquals("IC number must follow the Singaporean IC format (e.g., S1234567A)",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidTag_returnsInvalid() {
        ValidationResult result = validator.validate("John Doe", "A1", "S1234567D", Arrays.asList("diabetic", ""));
        assertFalse(result.valid());
        assertEquals("tag", result.errors().get(0).field());
        assertEquals("Tags must be 1-20 characters with letters, numbers, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_multipleInvalid_returnsInvalid() {
        ValidationResult result = validator.validate("", "1A", "123", Arrays.asList("", "allergy"));
        assertFalse(result.valid());
        assertEquals("name", result.errors().get(0).field());
        assertEquals("Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes",
            result.errors().get(0).message());

        assertEquals("ward", result.errors().get(1).field());
        assertEquals("Ward must start with a letter and followed by one or more digits",
            result.errors().get(1).message());

        assertEquals("ic", result.errors().get(2).field());
        assertEquals("IC number must follow the Singaporean IC format (e.g., S1234567A)",
            result.errors().get(2).message());

        assertEquals("tag", result.errors().get(3).field());
        assertEquals("Tags must be 1-20 characters with letters, numbers, and hyphens only",
            result.errors().get(3).message());
    }

    @Test
    public void validate_nullTags_returnsValid() {
        ValidationResult result = validator.validate("John Doe", "A1", "S1234567D", null);
        assertTrue(result.valid());
    }

    @Test
    public void validate_emptyTags_returnsValid() {
        ValidationResult result = validator.validate("John Doe", "A1", "S1234567D", Collections.emptyList());
        assertTrue(result.valid());
    }
}
