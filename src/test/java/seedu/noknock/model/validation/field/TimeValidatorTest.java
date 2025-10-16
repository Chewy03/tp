package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class TimeValidatorTest {
    private TimeValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new TimeValidator();
    }

    @Test
    public void validate_null_returnsInvalid() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("time", result.errors().get(0).field());
        assertEquals("Time must be in HH:MM format or 12-hour format with am/pm",
            result.errors().get(0).message());
    }

    @Test
    public void validate_valid24Hour_returnsValid() {
        LocalTime time = TimeValidator.parse("14:30");
        ValidationResult result = validator.validate(time);
        assertTrue(result.valid());
    }

    @Test
    public void validate_valid12Hour_returnsValid() {
        LocalTime time = TimeValidator.parse("2:30pm");
        ValidationResult result = validator.validate(time);
        assertTrue(result.valid());
    }

    @Test
    public void validate_valid12HourWithSpace_returnsValid() {
        LocalTime time = TimeValidator.parse("4:30 am");
        ValidationResult result = validator.validate(time);
        assertTrue(result.valid());
    }


    @Test
    public void validate_invalidFormat_returnsInvalid() {
        LocalTime time = TimeValidator.parse("25:61");
        ValidationResult result = validator.validate(time);
        assertNull(time, "Parsing an invalid time string should return null");
        assertFalse(result.valid());
        assertEquals("time", result.errors().get(0).field());
        assertEquals("Time must be in HH:MM format or 12-hour format with am/pm",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidString_returnsInvalid() {
        LocalTime time = TimeValidator.parse("notatime");
        ValidationResult result = validator.validate(time);
        assertNull(time, "Parsing an invalid time string should return null");
        assertFalse(result.valid());
        assertEquals("time", result.errors().get(0).field());
        assertEquals("Time must be in HH:MM format or 12-hour format with am/pm",
            result.errors().get(0).message());
    }
}
