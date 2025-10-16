package seedu.noknock.model.validation.field;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates relationships to ensure they meet specific criteria.
 * A valid relationship must be between 1 and 30 characters long and can only contain letters, spaces, and hyphens.
 */
public final class RelationshipValidator extends AbstractValidator<String> {
    private static final String REL_REGEX = "^[A-Za-z\\- ]{1,30}$";

    /**
     * Validates the given relationship.
     *
     * @param rel The relationship to validate.
     * @return A ValidationResult indicating whether the relationship is valid or not.
     */
    @Override
    public ValidationResult validate(String rel) {
        if (rel == null || rel.trim().isEmpty() || !rel.trim().matches(REL_REGEX)) {
            return fail("relationship", "Relationship must be 1-30 characters with letters, spaces, and hyphens only");
        }
        return ok();
    }
}
