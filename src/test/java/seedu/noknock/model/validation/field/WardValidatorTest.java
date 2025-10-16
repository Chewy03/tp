package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class WardValidatorTest {
    private WardValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new WardValidator();
    }

    @Test
    public void validate_null_returnsInvalid() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("ward", result.errors().get(0).field());
        assertEquals("Ward must start with a letter and followed by one or more digits",
            result.errors().get(0).message());
    }

    @Test
    public void validate_emptyWard_returnsInvalid() {
        ValidationResult result = validator.validate("");
        assertFalse(result.valid());
        assertEquals("ward", result.errors().get(0).field());
        assertEquals("Ward must start with a letter and followed by one or more digits",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidFormat_returnsInvalid() {
        ValidationResult result = validator.validate("1A");
        assertFalse(result.valid());
        assertEquals("ward", result.errors().get(0).field());
        assertEquals("Ward must start with a letter and followed by one or more digits",
            result.errors().get(0).message());

        result = validator.validate("A-1");
        assertFalse(result.valid());
        assertEquals("ward", result.errors().get(0).field());
        assertEquals("Ward must start with a letter and followed by one or more digits",
            result.errors().get(0).message());

        result = validator.validate("A_1");
        assertFalse(result.valid());
        assertEquals("ward", result.errors().get(0).field());
        assertEquals("Ward must start with a letter and followed by one or more digits",
            result.errors().get(0).message());

        result = validator.validate("Ward7");
        assertFalse(result.valid());
        assertEquals("ward", result.errors().get(0).field());
        assertEquals("Ward must start with a letter and followed by one or more digits",
            result.errors().get(0).message());
    }

    @Test
    public void validate_validWard_returnsValid() {
        ValidationResult result = validator.validate("A1");
        assertTrue(result.valid());

        result = validator.validate("B12");
        assertTrue(result.valid());
    }
}
