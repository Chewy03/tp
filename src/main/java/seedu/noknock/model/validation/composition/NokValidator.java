package seedu.noknock.model.validation.composition;

import java.util.ArrayList;
import java.util.List;

import seedu.noknock.model.validation.ValidationResult;
import seedu.noknock.model.validation.field.NameValidator;
import seedu.noknock.model.validation.field.PhoneValidator;
import seedu.noknock.model.validation.field.RelationshipValidator;

/**
 * Validates a next-of-kin (NOK) entry, which includes a name, phone number, and relationship.
 * This class uses individual validators for each field and combines their results.
 */
public final class NokValidator {
    private final NameValidator nameValidator = new NameValidator();
    private final PhoneValidator phoneValidator = new PhoneValidator();
    private final RelationshipValidator relationshipValidator = new RelationshipValidator();

    /**
     * Validates the given NOK details.
     *
     * @param name         The NOK's name to validate.
     * @param phone        The NOK's phone number to validate.
     * @param relationship The NOK's relationship to validate.
     * @return A ValidationResult indicating whether all NOK details are valid or not.
     */
    public ValidationResult validate(String name, String phone, String relationship) {
        List<ValidationResult> results = new ArrayList<>();
        results.add(nameValidator.validate(name));
        results.add(phoneValidator.validate(phone));
        results.add(relationshipValidator.validate(relationship));
        return ValidationResult.combine(results);
    }
}
