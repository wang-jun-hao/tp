package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.ADDRESS_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.BLOOD_TYPE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.BLOOD_TYPE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.DOB_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DOB_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.HEIGHT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.HEIGHT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.IC_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.IC_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_ADDRESS_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_BLOOD_TYPE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_DOB_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_EMAIL_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_HEIGHT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_IC_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_NAME_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_PHONE_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.address.logic.commands.CommandTestUtil.INVALID_WEIGHT_DESC;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.TAG_DESC_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_FRIEND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.WEIGHT_DESC_BOB;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

public class AddCommandParserTest {
    private AddCommandParser parser = new AddCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        Person expectedPerson = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple ics - last ic accepted
        assertParseSuccess(parser, IC_DESC_AMY + IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple names - last name accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_AMY + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple dates of birth - last date of birth accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_AMY + DOB_DESC_BOB
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple phones - last phone accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_AMY
                        + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple emails - last email accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_AMY + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple addresses - last address accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_AMY + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple heights - last height accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_AMY + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple weights - last weight accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_AMY + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple blood type - last blood type accepted
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_AMY + BLOOD_TYPE_DESC_BOB + TAG_DESC_FRIEND,
                new AddCommand(expectedPerson));

        // multiple tags - all accepted
        Person expectedPersonMultipleTags = new PersonBuilder(BOB).withTags(VALID_TAG_FRIEND, VALID_TAG_HUSBAND)
                .build();
        assertParseSuccess(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                        + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                        + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
            new AddCommand(expectedPersonMultipleTags));
    }

    @Test
    public void parse_optionalFieldsMissing_success() {
        // zero tags
        Person expectedPerson = new PersonBuilder(AMY).withTags().build();
        assertParseSuccess(parser, IC_DESC_AMY + NAME_DESC_AMY + DOB_DESC_AMY + PHONE_DESC_AMY + EMAIL_DESC_AMY
                + ADDRESS_DESC_AMY + HEIGHT_DESC_AMY + WEIGHT_DESC_AMY + BLOOD_TYPE_DESC_AMY,
                new AddCommand(expectedPerson));
    }

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE);

        // missing ic prefix
        assertParseFailure(parser, VALID_IC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing name prefix
        assertParseFailure(parser, IC_DESC_BOB + VALID_NAME_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing date of birth prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + VALID_DOB_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing phone prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + VALID_PHONE_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing email prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + VALID_EMAIL_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing address prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + VALID_ADDRESS_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing height prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + VALID_HEIGHT_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing weight prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + VALID_WEIGHT_BOB
                + BLOOD_TYPE_DESC_BOB, expectedMessage);

        // missing blood type prefix
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + VALID_BLOOD_TYPE_BOB, expectedMessage);

        // all prefixes missing
        assertParseFailure(parser, VALID_IC_BOB + VALID_NAME_BOB + VALID_DOB_BOB + VALID_PHONE_BOB
                + VALID_EMAIL_BOB + VALID_ADDRESS_BOB + VALID_HEIGHT_BOB + VALID_WEIGHT_BOB
                + VALID_BLOOD_TYPE_BOB, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid ic
        assertParseFailure(parser, INVALID_IC_DESC + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Ic.MESSAGE_CONSTRAINTS);

        // invalid name
        assertParseFailure(parser, IC_DESC_BOB + INVALID_NAME_DESC + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Name.MESSAGE_CONSTRAINTS);

        // invalid date of birth
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + INVALID_DOB_DESC + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                DateOfBirth.MESSAGE_CONSTRAINTS);

        // invalid phone
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + INVALID_PHONE_DESC
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Phone.MESSAGE_CONSTRAINTS);

        // invalid email
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + INVALID_EMAIL_DESC + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Email.MESSAGE_CONSTRAINTS);

        // invalid address
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Address.MESSAGE_CONSTRAINTS);

        // invalid height
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + INVALID_HEIGHT_DESC + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Height.MESSAGE_CONSTRAINTS);

        // invalid weight
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + INVALID_WEIGHT_DESC
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                Weight.MESSAGE_CONSTRAINTS);

        // invalid blood type
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + INVALID_BLOOD_TYPE_DESC + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                BloodType.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + INVALID_TAG_DESC + VALID_TAG_FRIEND,
                Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first invalid value reported
        assertParseFailure(parser, IC_DESC_BOB + INVALID_NAME_DESC + DOB_DESC_BOB + PHONE_DESC_BOB
                + EMAIL_DESC_BOB + INVALID_ADDRESS_DESC + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB,
                Name.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + IC_DESC_BOB + NAME_DESC_BOB + DOB_DESC_BOB
                + PHONE_DESC_BOB + EMAIL_DESC_BOB + ADDRESS_DESC_BOB + HEIGHT_DESC_BOB + WEIGHT_DESC_BOB
                + BLOOD_TYPE_DESC_BOB + TAG_DESC_HUSBAND + TAG_DESC_FRIEND,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
    }
}
