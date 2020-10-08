package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class FieldContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Arrays.asList("first", "second");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second", "third");

        FieldContainsKeywordsPredicate firstPredicate =
                new FieldContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_NAME);
        FieldContainsKeywordsPredicate secondPredicate =
                new FieldContainsKeywordsPredicate(secondPredicateKeywordList, PREFIX_IC);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same keywords, prefix -> returns true
        FieldContainsKeywordsPredicate firstPredicateCopy =
                new FieldContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_NAME);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different keywords, prefix -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));

        // different keywords, same prefix -> returns false
        FieldContainsKeywordsPredicate thirdPredicate =
                new FieldContainsKeywordsPredicate(secondPredicateKeywordList, PREFIX_NAME);
        assertFalse(firstPredicate.equals(thirdPredicate));

        // same keywords, different prefix -> returns false
        firstPredicateCopy = new FieldContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_DOB);
        assertFalse(firstPredicate.equals(firstPredicateCopy));

        // same keywords, different prefix (test with different prefix) -> returns false
        firstPredicateCopy = new FieldContainsKeywordsPredicate(firstPredicateKeywordList, PREFIX_PHONE);
        assertFalse(firstPredicate.equals(firstPredicateCopy));
    }

    @Test
    public void test_containsOneKeyword_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                new FieldContainsKeywordsPredicate(Collections.singletonList("Alice"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("S9123456Z"), PREFIX_IC);
        assertTrue(predicate.test(new PatientBuilder().withIc("S9123456Z").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("08-09-2010"), PREFIX_DOB);
        assertTrue(predicate.test(new PatientBuilder().withDateOfBirth("08-09-2010").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("81234567"), PREFIX_PHONE);
        assertTrue(predicate.test(new PatientBuilder().withPhone("81234567").build()));
    }

    @Test
    public void test_containsMultipleKeywords_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
            predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "Bob"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // no tests for ic, dob and phone fields since the other fields are just a single word (Not separated by spaces)
    }

    @Test
    public void test_matchesOneKeyword_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Bob", "Carol"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Carol").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("S9123456Z", "S9876543C"), PREFIX_IC);
        assertTrue(predicate.test(new PatientBuilder().withIc("S9876543C").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("15-03-2002", "08-09-2010"), PREFIX_DOB);
        assertTrue(predicate.test(new PatientBuilder().withDateOfBirth("15-03-2002").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("81234567", "66669876"), PREFIX_PHONE);
        assertTrue(predicate.test(new PatientBuilder().withPhone("66669876").build()));
    }

    @Test
    public void test_mixedCaseKeywords_returnsTrue() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                predicate = new FieldContainsKeywordsPredicate(Arrays.asList("aLIce", "bOB"), PREFIX_NAME);
        assertTrue(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Collections.singletonList("s9876543c"), PREFIX_IC);
        assertTrue(predicate.test(new PatientBuilder().withIc("S9876543C").build()));

        // no tests for dob and phone fields since these fields are numeric.
    }

    @Test
    public void test_zeroKeywords_returnsFalse() {
        // name field
        FieldContainsKeywordsPredicate predicate =
                new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_NAME);
        assertFalse(predicate.test(new PatientBuilder().withName("Alice").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_IC);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9876543C").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_DOB);
        assertFalse(predicate.test(new PatientBuilder().withDateOfBirth("15-03-2002").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Collections.emptyList(), PREFIX_PHONE);
        assertFalse(predicate.test(new PatientBuilder().withPhone("66669876").build()));
    }

    @Test
    public void test_nonMatchingKeywords_returnsFalse() {
        // Non-matching keyword
        FieldContainsKeywordsPredicate predicate =
            predicate = new FieldContainsKeywordsPredicate(Arrays.asList("Carol"), PREFIX_NAME);
        assertFalse(predicate.test(new PatientBuilder().withName("Alice Bob").build()));

        // ic field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("S9123456Z", "S9876543C"), PREFIX_IC);
        assertFalse(predicate.test(new PatientBuilder().withIc("S8642024G").build()));

        // date of birth field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("15-03-2002", "08-09-2010"), PREFIX_DOB);
        assertFalse(predicate.test(new PatientBuilder().withDateOfBirth("13-04-1985").build()));

        // phone field
        predicate = new FieldContainsKeywordsPredicate(Arrays.asList("81234567", "66669876"), PREFIX_PHONE);
        assertFalse(predicate.test(new PatientBuilder().withPhone("94210412").build()));
    }

    @Test
    public void test_keywordMatchesOtherFields_returnsFalse() {
        // keywords match all fields except name
        FieldContainsKeywordsPredicate predicate =
            new FieldContainsKeywordsPredicate(Arrays.asList("S9999999R", "12345", "alice@email.com", "Main",
                    "Street", "171", "61.2", "A+", "12-08-1999"), PREFIX_NAME);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except ic
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "12345", "alice@email.com", "Main",
                        "Street", "171", "61.2", "A+", "12-08-1999"), PREFIX_IC);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except date of birth
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "S9999999R", "12345", "alice@email.com",
                        "Main", "Street", "171", "61.2", "A+"), PREFIX_DOB);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));

        // keywords match all fields except phone
        predicate =
                new FieldContainsKeywordsPredicate(Arrays.asList("Alice", "S9999999R", "alice@email.com", "Main",
                        "Street", "171", "61.2", "A+", "12-08-1999"), PREFIX_PHONE);
        assertFalse(predicate.test(new PatientBuilder().withIc("S9999999R").withName("Alice").withPhone("12345")
                .withEmail("alice@email.com").withAddress("Main Street").withHeight("171").withWeight("61.2")
                .withBloodType("A+").withDateOfBirth("12-08-1999").build()));
    }
}
