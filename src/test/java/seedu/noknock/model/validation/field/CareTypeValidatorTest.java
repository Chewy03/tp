package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class CareTypeValidatorTest {
    private CareTypeValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new CareTypeValidator();
    }

    @Test
    public void validate_null_returnsInvalid() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("careType", result.errors().get(0).field());
        assertEquals("Care type must be 1-50 characters", result.errors().get(0).message());
    }

    @Test
    public void validate_emptyCareType_returnsInvalid() {
        ValidationResult result = validator.validate("");
        assertFalse(result.valid());
        assertEquals("careType", result.errors().get(0).field());
        assertEquals("Care type must be 1-50 characters", result.errors().get(0).message());
    }

    @Test
    public void validate_tooLongCareType_returnsInvalid() {
        String longCareType = "a".repeat(51);
        ValidationResult result = validator.validate(longCareType);
        assertFalse(result.valid());
        assertEquals("careType", result.errors().get(0).field());
        assertEquals("Care type must be 1-50 characters", result.errors().get(0).message());
    }

    @Test
    public void validate_validCareType_returnsValid() {
        ValidationResult result = validator.validate("Basic Care");
        assertTrue(result.valid());
    }
}
