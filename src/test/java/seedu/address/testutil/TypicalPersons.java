package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    public static final Person ALICE = new PersonBuilder().withIc("S9777777R").withName("Alice Pauline")
            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
            .withPhone("94351253").withHeight("174")
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withIc("S9234567A").withName("Benson Meier")
            .withAddress("311, Clementi Ave 2, #02-25")
            .withEmail("johnd@example.com").withPhone("98765432").withHeight("170")
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withIc("S9876543W").withName("Carl Kurz")
        .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street").withHeight("162").build();
    public static final Person DANIEL = new PersonBuilder().withIc("T0054321P").withName("Daniel Meier")
        .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street").withHeight("183")
        .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withIc("F7654321Q").withName("Elle Meyer")
        .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave").withHeight("177").build();
    public static final Person FIONA = new PersonBuilder().withIc("S7543210A").withName("Fiona Kunz")
        .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo").withHeight("168").build();
    public static final Person GEORGE = new PersonBuilder().withIc("T0232323I").withName("George Best")
        .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street").withHeight("169").build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withIc("S8756432F").withName("Hoon Meier")
        .withPhone("8482424").withEmail("stefan@example.com").withAddress("little india").withHeight("171").build();
    public static final Person IDA = new PersonBuilder().withIc("T0066441J").withName("Ida Mueller")
        .withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave").withHeight("160").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withIc(VALID_IC_AMY).withName(VALID_NAME_AMY)
        .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
        .withHeight(VALID_HEIGHT_AMY).withTags(VALID_TAG_FRIEND).build();
    public static final Person BOB = new PersonBuilder().withIc(VALID_IC_BOB).withName(VALID_NAME_BOB)
        .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
        .withHeight(VALID_HEIGHT_BOB).withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
