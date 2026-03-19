package seedu.address.model.outlet;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents an Outlet's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidOutletAddress(String)}
 */
public class OutletAddress {

    public static final String MESSAGE_CONSTRAINTS = "Outlet addresses should not be blank";
    public static final String VALIDATION_REGEX = "[^\\s].*";

    public final String value;

    /**
     * Constructs an {@code OutletAddress}.
     *
     * @param outletAddress A valid outlet address.
     */
    public OutletAddress(String outletAddress) {
        requireNonNull(outletAddress);
        checkArgument(isValidOutletAddress(outletAddress), MESSAGE_CONSTRAINTS);
        value = outletAddress;
    }

    /**
     * Returns true if a given string is a valid outlet address.
     */
    public static boolean isValidOutletAddress(String test) {
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

        if (!(other instanceof OutletAddress)) {
            return false;
        }

        OutletAddress otherOutletAddress = (OutletAddress) other;
        return value.equals(otherOutletAddress.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
