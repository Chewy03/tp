package seedu.noknock.model.validation.field;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.noknock.model.validation.ValidationResult;

public class DateValidatorTest {
    private DateValidator validator;

    @BeforeEach
    public void setUp() {
        validator = new DateValidator();
    }

    @Test
    public void parse_null_returnsNull() {
        assertNull(DateValidator.parse(null));
    }

    @Test
    public void parse_invalidFormat_returnsNull() {
        assertNull(DateValidator.parse("2025/10/09"));
        assertNull(DateValidator.parse("10-09-25"));
    }

    @Test
    public void parse_validYearMonthDay_returnsLocalDate() {
        LocalDate expected = LocalDate.of(2025, 10, 9);
        assertEquals(expected, DateValidator.parse("2025-10-09"));
    }

    @Test
    public void parse_validDayMonthYear_returnsLocalDate() {
        LocalDate expected = LocalDate.of(2025, 10, 9);
        assertEquals(expected, DateValidator.parse("09-10-2025"));
    }


    @Test
    public void validate_nullDate_returnsInvalid() {
        ValidationResult result = validator.validate(null);
        assertFalse(result.valid());
        assertEquals("date", result.errors().get(0).field());
    }

    @Test
    public void validate_pastDate_returnsInvalid() {
        LocalDate pastDate = LocalDate.now().minusDays(1);
        ValidationResult result = validator.validate(pastDate);
        assertFalse(result.valid());
        assertEquals("date", result.errors().get(0).field());
        assertEquals("Date must not be in the past", result.errors().get(0).message());
    }

    @Test
    public void validate_todayOrFutureDate_returnsValid() {
        LocalDate today = LocalDate.now();
        LocalDate future = today.plusDays(1);

        ValidationResult todayResult = validator.validate(today);
        ValidationResult futureResult = validator.validate(future);

        assertTrue(todayResult.valid());
        assertTrue(futureResult.valid());
    }
}
