package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.CARL;
import static seedu.address.testutil.TypicalPersons.getTypicalPersons;

import java.util.HashMap;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;
import seedu.address.testutil.PersonBuilder;

public class TagCounterTest {


    @Test
    public void toStringMethod() {
        TagCounter tagCounter = new TagCounter();
        Person p = new PersonBuilder().withTags("friend", "colleague").build();
        tagCounter.incrementTags(p, new UniquePersonList());
        String result = tagCounter.toString();
        assertEquals("{[friend]=1, [colleague]=1}", result);
    }

    @Test
    public void incrementTags_success() {
        HashMap<Tag, Integer> tagMap = new HashMap<Tag, Integer>();
        TagCounter tagCounter = new TagCounter();
        UniquePersonList uniquePersonList = new UniquePersonList();

        // Equality with empty counters
        assertEquals(new TagCounter(tagMap), tagCounter);

        // Addition of person with 1 tag
        tagCounter.incrementTags(ALICE, uniquePersonList);
        tagMap.put(new Tag("friends"), 1);
        assertEquals(new TagCounter(tagMap), tagCounter);

        // Addition of person with multiple tags
        tagCounter.incrementTags(BENSON, uniquePersonList);
        tagMap.put(new Tag("friends"), 2);
        tagMap.put(new Tag("owesMoney"), 1);
        assertEquals(new TagCounter(tagMap), tagCounter);

        // Addition of person with no tags
        tagCounter.incrementTags(CARL, uniquePersonList);
        assertEquals(new TagCounter(tagMap), tagCounter);
    }

    @Test
    public void decrementTags_success() {
        HashMap<Tag, Integer> tagMap = new HashMap<Tag, Integer>();
        tagMap.put(new Tag("friends"), 2);
        tagMap.put(new Tag("owesMoney"), 1);
        TagCounter tagCounter = new TagCounter();
        UniquePersonList uniquePersonList = new UniquePersonList();
        tagCounter.incrementTags(ALICE, uniquePersonList);
        tagCounter.incrementTags(BENSON, uniquePersonList);
        tagCounter.incrementTags(CARL, uniquePersonList);

        // Check TagCounter is correct initially
        assertEquals(new TagCounter(tagMap), tagCounter);

        // Decrement of person with no tags
        tagCounter.incrementTags(CARL, uniquePersonList);
        assertEquals(new TagCounter(tagMap), tagCounter);

        // Decrement of person with 1 tag
        tagCounter.decrementTags(ALICE, uniquePersonList);
        tagMap.put(new Tag("friends"), 1);
        assertEquals(new TagCounter(tagMap), tagCounter);

        // Decrement of person with multiple tags
        tagCounter.decrementTags(BENSON, uniquePersonList);
        tagMap.clear();
        assertEquals(new TagCounter(tagMap), tagCounter);

    }

    @Test
    public void resetTagCounter() {
        UniquePersonList uniquePersonList = new UniquePersonList();
        for (Person person : getTypicalPersons()) {
            uniquePersonList.add(person);
        }

        HashMap<Tag, Integer> tagMap = new HashMap<Tag, Integer>();
        tagMap.put(new Tag("friends"), 3);
        tagMap.put(new Tag("owesMoney"), 1);

        TagCounter tagCounter = new TagCounter();

        tagCounter.resetTagCounter(uniquePersonList);
        assertEquals(new TagCounter(tagMap), tagCounter);
    }

    @Test
    public void decrementTags_reset() {
        UniquePersonList uniquePersonList = new UniquePersonList();
        for (Person person : getTypicalPersons()) {
            uniquePersonList.add(person);
        }

        HashMap<Tag, Integer> tagMap = new HashMap<Tag, Integer>();
        tagMap.put(new Tag("friends"), 3);
        tagMap.put(new Tag("owesMoney"), 1);

        TagCounter tagCounter = new TagCounter();
        tagCounter.decrementTags(BENSON, uniquePersonList);

        assertEquals(new TagCounter(tagMap), tagCounter);
    }

    @Test
    public void incrementTags_reset() {
        UniquePersonList uniquePersonList = new UniquePersonList();
        for (Person person : getTypicalPersons()) {
            uniquePersonList.add(person);
        }

        HashMap<Tag, Integer> tagMap = new HashMap<Tag, Integer>();
        tagMap.put(new Tag("friends"), 3);
        tagMap.put(new Tag("owesMoney"), 1);

        HashMap<Tag, Integer> secondTagMap = new HashMap<Tag, Integer>();
        secondTagMap.put(new Tag("friends"), -1);

        TagCounter tagCounter = new TagCounter(secondTagMap);
        tagCounter.incrementTags(BENSON, uniquePersonList);

        assertEquals(new TagCounter(tagMap), tagCounter);
    }
}
