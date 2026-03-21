package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class TagComboTest {

    Set<Tag> firstTagSet = Set.of(new Tag("python"), new Tag("java"));
    Set<Tag> secondTagSet = Set.of(new Tag("python"), new Tag("java"));
    Set<Tag> thirdTagSet = Set.of(new Tag("python"), new Tag("java"), new Tag("C"));

    TagCombo firstTagCombo = new TagCombo(firstTagSet);
    TagCombo secondTagCombo = new TagCombo(secondTagSet);
    TagCombo thirdTagCombo = new TagCombo(thirdTagSet);

    @Test
    public void equals() {
        assertTrue(firstTagCombo.equals(secondTagCombo));
        assertFalse(firstTagCombo.equals(thirdTagCombo));
        assertTrue(firstTagCombo.equals(firstTagCombo));
        assertFalse(firstTagCombo.equals(null));
    }

    @Test
    public void hashcode() {
        assertEquals(firstTagCombo.hashCode(), secondTagCombo.hashCode());
        assertNotEquals(firstTagCombo.hashCode(), thirdTagCombo.hashCode());
    }
}
