package seedu.noknock.model.validation.composition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class NokValidatorTest {
    private NokValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new NokValidator();
    }

    @Test
    public void validate_allValid_returnsValid() {
        ValidationResult result = validator.validate("John Doe", "+6591234567", "Father");
        assertTrue(result.valid());
    }

    @Test
    public void validate_invalidName_returnsInvalid() {
        ValidationResult result = validator.validate("", "91234567", "Father");
        assertFalse(result.valid());
        assertEquals("name", result.errors().get(0).field());
        assertEquals("Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes",
            result.errors().get(0).message());
        assertEquals("phone", result.errors().get(1).field());
        assertEquals("Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens",
            result.errors().get(1).message());
    }

    @Test
    public void validate_invalidPhone_returnsInvalid() {
        ValidationResult result = validator.validate("John Doe", "123", "Father");
        assertFalse(result.valid());
        assertEquals("phone", result.errors().get(0).field());
        assertEquals("Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidRelationship_returnsInvalid() {
        ValidationResult result = validator.validate("John Doe", "+6591234567", "");
        assertFalse(result.valid());
        assertEquals("relationship", result.errors().get(0).field());
        assertEquals("Relationship must be 1-30 characters with letters, spaces, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_multipleInvalid_returnsInvalid() {
        ValidationResult result = validator.validate("", "123", "");
        assertFalse(result.valid());
        assertEquals("name", result.errors().get(0).field());
        assertEquals("Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes",
            result.errors().get(0).message());

        assertEquals("phone", result.errors().get(1).field());
        assertEquals("Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens",
            result.errors().get(1).message());

        assertEquals("relationship", result.errors().get(2).field());
        assertEquals("Relationship must be 1-30 characters with letters, spaces, and hyphens only",
            result.errors().get(2).message());
    }
}
