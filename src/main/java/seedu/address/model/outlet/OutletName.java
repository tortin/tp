package seedu.address.model.outlet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Outlet's name.
 * Guarantees: immutable; is valid as declared in {@link #isValidOutletName(String)}
 */
public class OutletName {

    public static final String MESSAGE_CONSTRAINTS = "Outlet names should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code OutletName}.
     *
     * @param outletName A valid outlet name.
     */
    public OutletName(String outletName) {
        requireNonNull(outletName);
        checkArgument(isValidOutletName(outletName), MESSAGE_CONSTRAINTS);
        value = outletName;
    }

    /**
     * Returns true if a given string is a valid outlet name.
     */
    public static boolean isValidOutletName(String test) {
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

        if (!(other instanceof OutletName)) {
            return false;
        }

        OutletName otherOutletName = (OutletName) other;
        return value.equals(otherOutletName.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
