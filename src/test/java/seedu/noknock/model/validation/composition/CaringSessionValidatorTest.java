package seedu.noknock.model.validation.composition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class CaringSessionValidatorTest {
    private CaringSessionValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new CaringSessionValidator();
    }

    @Test
    public void validate_allValid_returnsValid() {
        LocalDate date = LocalDate.now().plusDays(3);
        LocalTime time = LocalTime.now().plusHours(2);
        String careType = "Physical";
        String notes = "Routine check";
        ValidationResult result = validator.validate(date, time, careType, notes);
        assertTrue(result.valid());
    }

    @Test
    public void validate_invalidDate_returnsInvalid() {
        LocalTime time = LocalTime.of(14, 30);
        String careType = "Physical";
        String notes = "Routine check";
        ValidationResult result = validator.validate(null, time, careType, notes);
        assertFalse(result.valid());
        assertEquals("date", result.errors().get(0).field());
        assertEquals("Date must be in YYYY-MM-DD or DD-MM-YYYY format and not in the past",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidTime_returnsInvalid() {
        LocalDate date = LocalDate.now().plusDays(3);
        String careType = "Physical";
        String notes = "Routine check";
        ValidationResult result = validator.validate(date, null, careType, notes);
        assertFalse(result.valid());
        assertEquals("time", result.errors().get(0).field());
        assertEquals("Time must be in HH:MM format or 12-hour format with am/pm",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidCareType_returnsInvalid() {
        LocalDate date = LocalDate.now().plusDays(3);
        LocalTime time = LocalTime.now().plusHours(2);
        String notes = "Routine check";
        ValidationResult result = validator.validate(date, time, "", notes);
        assertFalse(result.valid());
        assertEquals("careType", result.errors().get(0).field());
        assertEquals("Care type must be 1-50 characters",
            result.errors().get(0).message());
    }

    @Test
    public void validate_invalidNotes_returnsInvalid() {
        LocalDate date = LocalDate.now().plusDays(3);
        LocalTime time = LocalTime.now().plusHours(2);
        String careType = "Physical";
        String notes = "a".repeat(201);
        ValidationResult result = validator.validate(date, time, careType, notes);
        assertFalse(result.valid());
        assertEquals("notes", result.errors().get(0).field());
        assertEquals("Notes cannot exceed 200 characters",
            result.errors().get(0).message());
    }

    @Test
    public void validate_multipleInvalid_returnsInvalid() {
        LocalDate date = LocalDate.of(2024, 6, 1);
        LocalTime time = LocalTime.of(14, 30);
        ValidationResult result = validator.validate(date, time, "", null);
        assertFalse(result.valid());

        assertEquals("date", result.errors().get(0).field());
        assertEquals("Date must not be in the past",
            result.errors().get(0).message());

        assertEquals("careType", result.errors().get(1).field());
        assertEquals("Care type must be 1-50 characters",
            result.errors().get(1).message());
    }
}
