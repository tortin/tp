package seedu.address.model.tag;

import java.util.Set;

/**
 * A class representing a set of {@Code Tag}s, which can be used for filtering.
 */
public class TagCombo {
    private final Set<Tag> tagSet;

    public TagCombo(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    @Override
    public int hashCode() {
        return tagSet.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCombo)) {
            return false;
        }

        TagCombo otherTag = (TagCombo) other;
        return tagSet.equals(otherTag.tagSet);
    }

    @Override
    public String toString() {
        return tagSet.toString();
    }
}
