package seedu.noknock.model.validation.field;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates times to ensure they meet specific criteria.
 * A valid time must be in the format HH:MM (24-hour) or HH:MMam/pm (12-hour).
 */
public final class TimeValidator extends AbstractValidator<LocalTime> {
    /**
     * Parses a time string into a LocalTime object.
     * Supports both 24-hour format (HH:MM) and 12-hour format with am/pm (HH:MMam/pm).
     *
     * @param input The time string to parse.
     * @return A LocalTime object if parsing is successful; null otherwise.
     */
    public static LocalTime parse(String input) {
        if (input == null) {
            return null;
        }

        if (input.matches("\\d{1,2}:\\d{2}")) {
            try {
                return LocalTime.parse(input);
            } catch (DateTimeParseException e) {
                return null;
            }
        }

        String formattedInput = input.replaceAll("\\s", "").toLowerCase();
        if (formattedInput.matches("\\d{1,2}:\\d{2}(am|pm)")) {
            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("h:mma", Locale.ENGLISH);
            try {
                return LocalTime.parse(formattedInput.toUpperCase(Locale.ROOT), fmt);
            } catch (DateTimeParseException e) {
                return null;
            }
        }

        return null;
    }

    /**
     * Validates the given time.
     *
     * @param time The time to validate.
     * @return A ValidationResult indicating whether the time is valid or not.
     */
    @Override
    public ValidationResult validate(LocalTime time) {
        if (time == null) {
            return fail("time", "Time must be in HH:MM format or 12-hour format with am/pm");
        }
        return ok();
    }
}
