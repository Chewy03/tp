package seedu.noknock.model.validation.field;

import seedu.noknock.model.validation.AbstractValidator;
import seedu.noknock.model.validation.ValidationResult;

/**
 * Validates phone numbers to ensure they meet specific criteria.
 * A valid phone number must start with a '+' sign, followed by 8 to 15 digits,
 * and may include spaces and hyphens for readability.
 */
public final class PhoneValidator extends AbstractValidator<String> {
    private static final String PHONE_REGEX = "^\\+[- 0-9]{8,20}$";

    /**
     * Validates the given phone number.
     *
     * @param phone The phone number to validate.
     * @return A ValidationResult indicating whether the phone number is valid or not.
     */
    @Override
    public ValidationResult validate(String phone) {
        if (phone == null || !phone.matches(PHONE_REGEX)) {
            return fail("phone",
                "Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens");
        }
        String digitsOnly = phone.replaceAll("[^0-9]", "");
        if (digitsOnly.length() < 8 || digitsOnly.length() > 15) {
            return fail("phone",
                "Phone number must start with + sign and be 8-15 digits, may include spaces and hyphens");
        }
        return ok();
    }
}
