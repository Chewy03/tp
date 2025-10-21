package seedu.noknock.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.noknock.commons.util.AppUtil.checkArgument;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Represents the relationship between a patient and their next-of-kin.
 * Guarantees: immutable; is valid as declared in {@link #isValidRelationship(String)}
 */
public enum Relationship {
    DAUGHTER("Daughter"),
    FATHER("Father"),
    MOTHER("Mother"),
    GRANDMOTHER("Grandmother"),
    GRANDFATHER("Grandfather"),
    GRANDDAUGHTER("Granddaughter"),
    GRANDSON("GrandSon"),
    SON("Son");

    public static final String MESSAGE_CONSTRAINTS =
        "Relationship must be one of: "
            + Arrays.stream(values())
            .map(Relationship::toString)
            .collect(Collectors.joining(", "))
            + " (case-insensitive)";

    private final String displayValue;

    Relationship(String displayValue) {
        this.displayValue = displayValue;
    }

    /**
     * Returns true if a given string is a valid relationship.
     */
    public static boolean isValidRelationship(String test) {
        if (test == null) {
            return false;
        }
        try {
            fromString(test);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * Converts a string to a Relationship enum value.
     * Case-insensitive matching.
     *
     * @param relationship The relationship string to convert.
     * @return The corresponding Relationship enum value.
     * @throws IllegalArgumentException if the string is not a valid relationship.
     */
    public static Relationship fromString(String relationship) {
        requireNonNull(relationship);
        String normalized = relationship.toUpperCase().trim();
        try {
            return Relationship.valueOf(normalized);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Creates a Relationship with validation (for consistency with other classes).
     *
     * @param relationship A valid relationship string.
     * @return The corresponding Relationship enum value.
     */
    public static Relationship of(String relationship) {
        requireNonNull(relationship);
        checkArgument(isValidRelationship(relationship), MESSAGE_CONSTRAINTS);
        return fromString(relationship);
    }

    @Override
    public String toString() {
        return displayValue;
    }
}
