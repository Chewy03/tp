package seedu.noknock.model.validation;

/**
 * An abstract base class for validators, providing common utility methods.
 *
 * @param <T> the type of object to be validated
 */
public abstract class AbstractValidator<T> implements Validator<T> {
    /**
     * Creates a ValidationResult representing a successful validation.
     *
     * @return a ValidationResult indicating success
     */
    protected ValidationResult ok() {
        return ValidationResult.ok();
    }

    /**
     * Creates a ValidationResult representing a validation failure.
     *
     * @param field   the field that failed validation
     * @param message the error message
     * @return a ValidationResult indicating failure
     */
    protected ValidationResult fail(String field, String message) {
        return ValidationResult.fail(field, message);
    }
}
