package seedu.noknock.model.validation;

/**
 * Represents a validation error with a specific field and an associated message.
 *
 * @param field   The field that has the validation error.
 * @param message The message describing the validation error.
 */
public record ValidationError(String field, String message) {
}
