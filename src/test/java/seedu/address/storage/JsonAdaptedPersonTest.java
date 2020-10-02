package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.BloodType;
import seedu.address.model.person.DateOfBirth;
import seedu.address.model.person.Email;
import seedu.address.model.person.Height;
import seedu.address.model.person.Ic;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Weight;

public class JsonAdaptedPersonTest {
    private static final String INVALID_IC = "A12345R7H";
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_DOB = "31/12/95";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_HEIGHT = "17S";
    private static final String INVALID_WEIGHT = "10.12";
    private static final String INVALID_BLOOD_TYPE = "D+";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_IC = BENSON.getIc().toString();
    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_DOB = BENSON.getDateOfBirth().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_HEIGHT = BENSON.getHeight().toString();
    private static final String VALID_WEIGHT = BENSON.getWeight().toString();
    private static final String VALID_BLOOD_TYPE = BENSON.getBloodType().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidIc_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = Ic.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullIc_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Ic.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, INVALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, null, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, INVALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = DateOfBirth.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullDateOfBirth_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, null, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, DateOfBirth.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, INVALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, null, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, INVALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, null,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                INVALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                null, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidHeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, INVALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = Height.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullHeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, null, VALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Height.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, INVALID_WEIGHT, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, null, VALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidBloodType_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, INVALID_BLOOD_TYPE, VALID_TAGS);
        String expectedMessage = BloodType.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullBloodType_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, BloodType.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_IC, VALID_NAME, VALID_DOB, VALID_PHONE, VALID_EMAIL,
                VALID_ADDRESS, VALID_HEIGHT, VALID_WEIGHT, VALID_BLOOD_TYPE, invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

}
