package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class PhoneValidatorTest {
    private PhoneValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new PhoneValidator();
    }

    @Test
    public void validate_null_returnsInvalid() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("phone", result.errors().get(0).field());
        assertEquals("Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidFormat_returnsInvalid() {
        ValidationResult result = validator.validate("12345678");
        assertFalse(result.valid());
        assertEquals("phone", result.errors().get(0).field());
        assertEquals("Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens",
            result.errors().get(0).message());
    }

    @Test
    public void validate_tooShort_returnsInvalid() {
        ValidationResult result = validator.validate("+1234567");
        assertFalse(result.valid());
        assertEquals("phone", result.errors().get(0).field());
        assertEquals("Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens",
            result.errors().get(0).message());
    }

    @Test
    public void validate_tooLong_returnsInvalid() {
        ValidationResult result = validator.validate("+12345678901234567890");
        assertFalse(result.valid());
        assertEquals("phone", result.errors().get(0).field());
        assertEquals("Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens",
            result.errors().get(0).message());
    }

    @Test
    public void validate_validPhone_returnsValid() {
        ValidationResult result = validator.validate("+1234-5678 9012");
        assertTrue(result.valid());
    }
}
