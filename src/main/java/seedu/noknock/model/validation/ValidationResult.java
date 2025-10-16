package seedu.noknock.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents the result of a validation operation.
 */
public record ValidationResult(boolean valid, List<ValidationError> errors) {
    /**
     * Factory methods for creating ValidationResult instances.
     */
    public static ValidationResult ok() {
        return new ValidationResult(true, Collections.emptyList());
    }

    /**
     * Creates a ValidationResult representing a single validation error.
     */
    public static ValidationResult fail(String field, String message) {
        return new ValidationResult(false, Collections.singletonList(new ValidationError(field, message)));
    }

    /**
     * Combines multiple ValidationResult instances into one.
     */
    public static ValidationResult combine(List<ValidationResult> results) {
        List<ValidationError> allErrors = new ArrayList<>();
        for (ValidationResult r : results) {
            allErrors.addAll(r.errors);
        }
        return allErrors.isEmpty()
            ? ok()
            : new ValidationResult(false, allErrors);
    }

    /**
     * Checks if the validation result is valid.
     */
    @Override
    public List<ValidationError> errors() {
        return Collections.unmodifiableList(errors);
    }
}
