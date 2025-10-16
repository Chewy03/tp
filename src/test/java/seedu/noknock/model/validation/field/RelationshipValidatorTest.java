package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class RelationshipValidatorTest {
    private RelationshipValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new RelationshipValidator();
    }

    @Test
    public void validate_null_returnsInvalid() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("relationship", result.errors().get(0).field());
        assertEquals("Relationship must be 1-30 characters with letters, spaces, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_emptyRelationship_returnsInvalid() {
        ValidationResult result = validator.validate("");
        assertFalse(result.valid());
        assertEquals("relationship", result.errors().get(0).field());
        assertEquals("Relationship must be 1-30 characters with letters, spaces, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_tooLongRelationship_returnsInvalid() {
        String longRel = "a".repeat(31);
        ValidationResult result = validator.validate(longRel);
        assertFalse(result.valid());
        assertEquals("relationship", result.errors().get(0).field());
        assertEquals("Relationship must be 1-30 characters with letters, spaces, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidCharacters_returnsInvalid() {
        ValidationResult result = validator.validate("Friend@Home");
        assertFalse(result.valid());
        assertEquals("relationship", result.errors().get(0).field());
        assertEquals("Relationship must be 1-30 characters with letters, spaces, and hyphens only",
            result.errors().get(0).message());
    }

    @Test
    public void validate_validRelationship_returnsValid() {
        ValidationResult result = validator.validate("Mother-in-law");
        assertTrue(result.valid());
    }
}
