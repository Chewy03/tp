package seedu.noknock.model.validation.field;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates tags to ensure they meet specific criteria.
 * A valid tag must be between 1 and 20 characters long and can only contain letters, numbers, and hyphens.
 */
public final class TagValidator extends AbstractValidator<String> {
    private static final String TAG_REGEX = "^[A-Za-z0-9\\-]{1,20}$";

    /**
     * Validates the given tag.
     *
     * @param tag The tag to validate.
     * @return A ValidationResult indicating whether the tag is valid or not.
     */
    @Override
    public ValidationResult validate(String tag) {
        if (tag == null || tag.trim().isEmpty() || !tag.matches(TAG_REGEX)) {
            return fail("tag", "Tags must be 1-20 characters with letters, numbers, and hyphens only");
        }
        return ok();
    }
}
