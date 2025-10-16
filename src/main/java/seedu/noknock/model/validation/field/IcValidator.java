package seedu.noknock.model.validation.field;

import java.util.Locale;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates Singaporean IC numbers to ensure they meet specific criteria.
 * A valid IC number must start with 'S' or 'T', followed by 7 digits, and end with an uppercase letter.
 */
public final class IcValidator extends AbstractValidator<String> {
    private static final String IC_REGEX = "^[ST]\\d{7}[A-Z]$";

    /**
     * Validates the given IC number.
     *
     * @param ic The IC number to validate.
     * @return A ValidationResult indicating whether the IC number is valid or not.
     */
    @Override
    public ValidationResult validate(String ic) {
        if (ic == null) {
            return fail("ic", "IC number must follow the Singaporean IC format (e.g., S1234567A)");
        }
        String upper = ic.trim().toUpperCase(Locale.ROOT);
        if (!upper.matches(IC_REGEX)) {
            return fail("ic", "IC number must follow the Singaporean IC format (e.g., S1234567A)");
        }
        return ok();
    }
}
