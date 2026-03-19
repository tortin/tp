package seedu.address.model.outlet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Outlet's postal code.
 * Guarantees: immutable; is valid as declared in {@link #isValidPostalCode(String)}
 */
public class OutletPostalCode {

    public static final String MESSAGE_CONSTRAINTS =
            "Outlet postal codes should only contain numbers, and it should be exactly 6 digits long";
    public static final String VALIDATION_REGEX = "\\d{6}";

    public final String value;

    /**
     * Constructs an {@code OutletPostalCode}.
     *
     * @param postalCode A valid postal code.
     */
    public OutletPostalCode(String postalCode) {
        requireNonNull(postalCode);
        checkArgument(isValidPostalCode(postalCode), MESSAGE_CONSTRAINTS);
        value = postalCode;
    }

    /**
     * Returns true if a given string is a valid postal code.
     */
    public static boolean isValidPostalCode(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof OutletPostalCode)) {
            return false;
        }

        OutletPostalCode otherPostalCode = (OutletPostalCode) other;
        return value.equals(otherPostalCode.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
