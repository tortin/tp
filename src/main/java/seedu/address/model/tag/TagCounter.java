package seedu.address.model.tag;

import java.util.HashMap;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * A wrapper to count the frequency of occurence of {@code Tag}s in the current candidate book.
 */
public class TagCounter {
    private HashMap<Tag, Integer> tagCounter;

    public TagCounter() {
        tagCounter = new HashMap<Tag, Integer>();
    }

    /**
     * Package-private constructor for testing purposes.
     */
    TagCounter(HashMap<Tag, Integer> tagCounter) {
        this.tagCounter = tagCounter;
    }

    /**
     * Adds the {@code Tag} to the {@code TagCounter} if not already present, then increments the count of the
     * {@code Tag} by 1.
     */
    private void incrementTag(Tag tag) {
        if (!tagCounter.containsKey(tag)) {
            tagCounter.put(tag, 0);
        }
        tagCounter.put(tag, tagCounter.get(tag) + 1);
    }

    /**
     * Adds all {@code Tag}s in a {@code Person} to the {@code TagCounter}.
     */
    public void incrementTags(Person person, UniquePersonList uniquePersonList) {
        for (Tag tag : person.getTags()) {
            this.incrementTag(tag);
        }
        if (!isValid()) {
            resetTagCounter(uniquePersonList);
        }
    }

    /**
     * Decrements the count of the given {@code Tag} in the tag counter by 1,
     * then removes the {@code Tag} from the {@code TagCounter} if the count is equal to 0.
     */
    private void decrementTag(Tag tag) {
        if (!tagCounter.containsKey(tag)) {
            tagCounter.put(tag, 0);
        }
        tagCounter.put(tag, tagCounter.get(tag) - 1);
        if (tagCounter.get(tag) == 0) {
            tagCounter.remove(tag);
        }
    }

    /**
     * Removes all {@code Tag}s in a {@code Person} from the {@code TagCounter}.
     */
    public void decrementTags(Person person, UniquePersonList uniquePersonList) {
        for (Tag tag : person.getTags()) {
            this.decrementTag(tag);
        }
        if (!isValid()) {
            resetTagCounter(uniquePersonList);
        }
    }

    /**
     * Resets the {@code TagCounter} using an {@code UniquePersonList} by counting all tags present. This method is to
     * be called as a last resort to reset the TagCounter to the correct state should any errors occur.
     */
    public void resetTagCounter(UniquePersonList uniquePersonList) {
        tagCounter = new HashMap<Tag, Integer>();
        for (Person person : uniquePersonList.asUnmodifiableObservableList()) {
            this.incrementTags(person, uniquePersonList);
        }
    }

    /**
     * Checks if the given {@Code TagCounter} is valid (contains any values less than or equal to 0).
     */
    private boolean isValid() {
        for (Integer value : tagCounter.values()) {
            if (value <= 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof TagCounter)) {
            return false;
        }

        TagCounter otherTagCounter = (TagCounter) other;
        return tagCounter.equals(otherTagCounter.tagCounter);
    }

    @Override
    public int hashCode() {
        return tagCounter.hashCode();
    }

    @Override
    public String toString() {
        return tagCounter.toString();
    }
}
