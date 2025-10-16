package seedu.noknock.model.validation.field;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates dates to ensure they meet specific criteria.
 * A valid date must be in the format YYYY-MM-DD or DD-MM-YYYY and must not be in the past.
 */
public final class DateValidator extends AbstractValidator<LocalDate> {
    /**
     * Parses a date string into a LocalDate object.
     * Accepts dates in the format YYYY-MM-DD or DD-MM-YYYY.
     *
     * @param input The date string to parse.
     * @return A LocalDate object if parsing is successful; null otherwise.
     */
    public static LocalDate parse(String input) {
        if (input == null) {
            return null;
        }

        if (input.matches("\\d{4}-\\d{2}-\\d{2}")) {
            try {
                return LocalDate.parse(input);
            } catch (DateTimeParseException ignored) {
                return null;
            }
        } else if (input.matches("\\d{2}-\\d{2}-\\d{4}")) {
            try {
                return LocalDate.parse(input, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            } catch (DateTimeParseException ignored) {
                return null;
            }
        }
        return null;
    }

    /**
     * Validates the given date.
     *
     * @param date The date to validate.
     * @return A ValidationResult indicating whether the date is valid or not.
     */
    @Override
    public ValidationResult validate(LocalDate date) {
        if (date == null) {
            return fail("date", "Date must be in YYYY-MM-DD or DD-MM-YYYY format and not in the past");
        }
        if (date.isBefore(LocalDate.now())) {
            return fail("date", "Date must not be in the past");
        }
        return ok();
    }
}
