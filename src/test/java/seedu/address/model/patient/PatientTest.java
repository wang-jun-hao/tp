package seedu.address.model.patient;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_BLOOD_TYPE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DOB_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_HEIGHT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_IC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_WEIGHT_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PatientBuilder;

public class PatientTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Patient patient = new PatientBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> patient.getTags().remove(0));
    }

    @Test
    public void isSamePatient() {
        // same object -> returns true
        assertTrue(ALICE.isSamePatient(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePatient(null));

        //different ic -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withIc(VALID_IC_BOB).build();
        assertFalse(ALICE.isSamePatient(editedAlice));

        // same ic, same name, same phone, different attributes -> returns true
        editedAlice = new PatientBuilder(ALICE).withDateOfBirth(VALID_DOB_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB)
                .withBloodType(VALID_BLOOD_TYPE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same ic, same name, same email, different attributes -> returns true
        editedAlice = new PatientBuilder(ALICE).withDateOfBirth(VALID_DOB_BOB).withPhone(VALID_PHONE_BOB)
                .withAddress(VALID_ADDRESS_BOB).withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB)
                .withBloodType(VALID_BLOOD_TYPE_BOB).withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same ic, same name, same phone, same email, different attributes -> returns true
        editedAlice = new PatientBuilder(ALICE).withDateOfBirth(VALID_DOB_BOB).withAddress(VALID_ADDRESS_BOB)
                .withHeight(VALID_HEIGHT_BOB).withWeight(VALID_WEIGHT_BOB).withBloodType(VALID_BLOOD_TYPE_BOB)
                .withTags(VALID_TAG_HUSBAND).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same ic, same name, same date of birth, same height, same weight, same address, different phone,
        // different email -> returns true
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePatient(editedAlice));

        // same ic, different name, different phone, different email -> returns true
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                .withEmail(VALID_EMAIL_BOB).build();
        assertTrue(ALICE.isSamePatient(editedAlice));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Patient aliceCopy = new PatientBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different patient -> returns false
        assertFalse(ALICE.equals(BOB));

        // different ic -> returns false
        Patient editedAlice = new PatientBuilder(ALICE).withIc(VALID_IC_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different name -> returns false
        editedAlice = new PatientBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different date of birth -> returns false
        editedAlice = new PatientBuilder(ALICE).withDateOfBirth(VALID_DOB_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PatientBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PatientBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different height -> returns false
        editedAlice = new PatientBuilder(ALICE).withHeight(VALID_HEIGHT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different weight -> returns false
        editedAlice = new PatientBuilder(ALICE).withWeight(VALID_WEIGHT_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different blood type -> returns false
        editedAlice = new PatientBuilder(ALICE).withBloodType(VALID_BLOOD_TYPE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new PatientBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }
}
