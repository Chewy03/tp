package seedu.noknock.model.validation.field;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates notes to ensure they meet specific criteria.
 * A valid note can be null or up to 200 characters long.
 */
public final class NotesValidator extends AbstractValidator<String> {
    /**
     * Validates the given notes.
     *
     * @param notes The notes to validate.
     * @return A ValidationResult indicating whether the notes are valid or not.
     */
    @Override
    public ValidationResult validate(String notes) {
        if (notes != null && notes.length() > 200) {
            return fail("notes", "Notes cannot exceed 200 characters");
        }
        return ok();
    }
}
