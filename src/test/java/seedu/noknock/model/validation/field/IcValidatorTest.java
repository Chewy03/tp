package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class IcValidatorTest {
    private final IcValidator validator = new IcValidator();

    @Test
    void validIc_shouldPass() {
        ValidationResult result = validator.validate("S1234567A");
        assertTrue(result.valid());
    }

    @Test
    void validIc_lowercase_shouldPass() {
        ValidationResult result = validator.validate("t7654321b");
        assertTrue(result.valid());
    }

    @Test
    void invalidIc_wrongPrefix_shouldFail() {
        ValidationResult result = validator.validate("A1234567B");
        assertFalse(result.valid());
        assertEquals("ic", result.errors().get(0).field());
        assertEquals("IC number must follow the Singaporean IC format (e.g., S1234567A)",
            result.errors().get(0).message());
    }

    @Test
    void invalidIc_wrongLength_shouldFail() {
        ValidationResult result = validator.validate("S123456A");
        assertFalse(result.valid());
        assertEquals("ic", result.errors().get(0).field());
        assertEquals("IC number must follow the Singaporean IC format (e.g., S1234567A)",
            result.errors().get(0).message());
    }

    @Test
    void invalidIc_nonDigit_shouldFail() {
        ValidationResult result = validator.validate("S12A4567B");
        assertFalse(result.valid());
        assertEquals("ic", result.errors().get(0).field());
        assertEquals("IC number must follow the Singaporean IC format (e.g., S1234567A)",
            result.errors().get(0).message());
    }

    @Test
    void invalidIc_wrongSuffix_shouldFail() {
        ValidationResult result = validator.validate("S1234567!");
        assertFalse(result.valid());
        assertEquals("ic", result.errors().get(0).field());
        assertEquals("IC number must follow the Singaporean IC format (e.g., S1234567A)",
            result.errors().get(0).message());
    }

    @Test
    void nullIc_shouldFail() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("ic", result.errors().get(0).field());
        assertEquals("IC number must follow the Singaporean IC format (e.g., S1234567A)",
            result.errors().get(0).message());
    }

    @Test
    void ic_withSpaces_shouldPass() {
        ValidationResult result = validator.validate("  S1234567A  ");
        assertTrue(result.valid());
    }
}
