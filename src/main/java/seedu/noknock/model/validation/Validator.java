package seedu.noknock.model.validation;

/**
 * A validator that validates a value of type T and returns a ValidationResult.
 */
public interface Validator<T> {
    ValidationResult validate(T value);
}
