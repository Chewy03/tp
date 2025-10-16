package seedu.noknock.model.validation.composition;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import seedu.noknock.model.validation.ValidationResult;
import seedu.noknock.model.validation.field.CareTypeValidator;
import seedu.noknock.model.validation.field.DateValidator;
import seedu.noknock.model.validation.field.NotesValidator;
import seedu.noknock.model.validation.field.TimeValidator;

/**
 * Validates a caring session by aggregating multiple field validators.
 * This class uses DateValidator, TimeValidator, CareTypeValidator, and NotesValidator
 * to validate the respective fields of a caring session.
 */
public final class CaringSessionValidator {
    private final DateValidator dateValidator = new DateValidator();
    private final TimeValidator timeValidator = new TimeValidator();
    private final CareTypeValidator careTypeValidator = new CareTypeValidator();
    private final NotesValidator notesValidator = new NotesValidator();

    /**
     * Validates the given caring session fields.
     *
     * @param date     The date of the caring session.
     * @param time     The time of the caring session.
     * @param careType The type of care provided during the session.
     * @param notes    Additional notes about the caring session.
     * @return A ValidationResult indicating whether all fields are valid or not.
     */
    public ValidationResult validate(LocalDate date, LocalTime time, String careType, String notes) {
        List<ValidationResult> results = new ArrayList<>();
        results.add(dateValidator.validate(date));
        results.add(timeValidator.validate(time));
        results.add(careTypeValidator.validate(careType));
        results.add(notesValidator.validate(notes));
        return ValidationResult.combine(results);
    }
}
