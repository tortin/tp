package seedu.address.model.outlet.exceptions;

/**
 * Signals that the operation will result in duplicate Outlets (Outlets are considered duplicates if they have the
 * same identity).
 */
public class DuplicateOutletException extends RuntimeException {
    public DuplicateOutletException() {
        super("Operation would result in duplicate outlets");
    }

    public DuplicateOutletException(String message) {
        super(message);
    }
}
