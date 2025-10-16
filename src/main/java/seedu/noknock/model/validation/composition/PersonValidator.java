package seedu.noknock.model.validation.composition;

import java.util.ArrayList;
import java.util.List;

import seedu.noknock.model.validation.ValidationResult;
import seedu.noknock.model.validation.field.IcValidator;
import seedu.noknock.model.validation.field.NameValidator;
import seedu.noknock.model.validation.field.TagValidator;
import seedu.noknock.model.validation.field.WardValidator;

/**
 * Validates persons by composing multiple field validators.
 * This includes validators for name, ward, IC number, and tags.
 */
public final class PersonValidator {
    private final NameValidator nameValidator = new NameValidator();
    private final WardValidator wardValidator = new WardValidator();
    private final IcValidator icValidator = new IcValidator();
    private final TagValidator tagValidator = new TagValidator();

    /**
     * Validates the given person details.
     *
     * @param name The person's name to validate.
     * @param ward The person's ward to validate.
     * @param ic   The person's IC number to validate.
     * @param tags The person's tags to validate.
     * @return A ValidationResult indicating whether the person details are valid or not.
     */
    public ValidationResult validate(String name, String ward, String ic, List<String> tags) {
        List<ValidationResult> results = new ArrayList<>();
        results.add(nameValidator.validate(name));
        results.add(wardValidator.validate(ward));
        results.add(icValidator.validate(ic));
        if (tags != null) {
            for (String tag : tags) {
                results.add(tagValidator.validate(tag));
            }
        }
        return ValidationResult.combine(results);
    }
}
