package seedu.noknock.model.validation.field;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates care types to ensure they meet specific criteria.
 * A valid care type must be between 1 and 50 characters long.
 */
public final class CareTypeValidator extends AbstractValidator<String> {
    /**
     * Validates the given care type.
     *
     * @param type The care type to validate.
     * @return A ValidationResult indicating whether the care type is valid or not.
     */
    @Override
    public ValidationResult validate(String type) {
        if (type == null || type.trim().isEmpty() || type.trim().length() > 50) {
            return fail("careType", "Care type must be 1-50 characters");
        }
        return ok();
    }
}
