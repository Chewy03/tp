package seedu.noknock.model.validation.field;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates person names to ensure they meet specific criteria.
 * A valid name must be between 1 and 50 characters long and can only contain letters, spaces, hyphens, and apostrophes.
 */
public final class NameValidator extends AbstractValidator<String> {
    private static final String NAME_REGEX = "^[A-Za-z'\\- ]{1,50}$";

    /**
     * Validates the given person name.
     *
     * @param name The person name to validate.
     * @return A ValidationResult indicating whether the name is valid or not.
     */
    @Override
    public ValidationResult validate(String name) {
        if (name == null || name.trim().isEmpty()) {
            return fail("name",
                "Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes");
        }
        String normalized = name.trim().replaceAll("\\s+", " ");
        if (!normalized.matches(NAME_REGEX)) {
            return fail("name",
                "Name must be 1-50 characters and contain only letters, spaces, hyphens, and apostrophes");
        }
        return ok();
    }
}
