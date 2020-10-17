package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.medibook.storage.JsonAdaptedMedicalNote.FIELD_CONTENT;
import static seedu.medibook.storage.JsonAdaptedMedicalNote.FIELD_DATE;
import static seedu.medibook.storage.JsonAdaptedMedicalNote.FIELD_DOCTOR_NAME;
import static seedu.medibook.storage.JsonAdaptedMedicalNote.MESSAGE_FORMAT_MISSING_FIELD;
import static seedu.medibook.storage.JsonAdaptedMedicalNote.MESSAGE_INVALID_DATE_FIELD;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalMedicalNotes.VALID_MEDICAL_NOTE1;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.medicalnote.MedicalNote;

public class JsonAdaptedMedicalNoteTest {
    @Test
    public void toModelType_validMedicalNotesDetails_returnsMedicalNote() throws Exception {
        MedicalNote medicalNote = VALID_MEDICAL_NOTE1;
        JsonAdaptedMedicalNote patient = new JsonAdaptedMedicalNote(medicalNote);
        assertEquals(medicalNote, patient.toModelType());
    }

    @Test
    public void toModelType_invalidDate_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("30-30-2020", "Dr Watson", "Patient is feeling well");
        String expectedMessage = MESSAGE_INVALID_DATE_FIELD;
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDate_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote(null, "Dr Watson", "Patient is feeling well");
        String expectedMessage = String.format(MESSAGE_FORMAT_MISSING_FIELD, FIELD_DATE);
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullDoctorName_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("20-05-2020", null, "Patient is feeling well");
        String expectedMessage = String.format(MESSAGE_FORMAT_MISSING_FIELD, FIELD_DOCTOR_NAME);
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

    @Test
    public void toModelType_nullContent_throwsIllegalValueException() {
        JsonAdaptedMedicalNote patient =
                new JsonAdaptedMedicalNote("20-05-2020", "Dr Watson", null);
        String expectedMessage = String.format(MESSAGE_FORMAT_MISSING_FIELD, FIELD_CONTENT);
        assertThrows(IllegalValueException.class, expectedMessage, patient::toModelType);
    }

}
