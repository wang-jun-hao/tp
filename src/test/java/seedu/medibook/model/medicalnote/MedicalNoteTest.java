package seedu.medibook.model.medicalnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

class MedicalNoteTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        // when date is null
        assertThrows(NullPointerException.class, () -> {
            new MedicalNote(null, "Lydia Yu", "Patient is asymptomatic.");
        });
        // when doctorName is null
        assertThrows(NullPointerException.class, () -> {
            new MedicalNote("15-04-2020", null, "Patient is asymptomatic.");
        });
        // when content is null
        assertThrows(NullPointerException.class, () -> {
            new MedicalNote("15-04-2020", "Lydia Yu", null);
        });
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "15/04/20";
        assertThrows(IllegalArgumentException.class, () -> {
            new MedicalNote(invalidDate, "Lydia Yu", "Patient is asymptomatic.");
        });
    }

    @Test
    public void constructor_invalidDoctorName_throwsIllegalArgumentException() {
        String invalidDoctorName = "";
        assertThrows(IllegalArgumentException.class, () -> {
            new MedicalNote("15-04-2020", invalidDoctorName, "Patient is asymptomatic.");
        });
    }

    @Test
    public void constructor_invalidContent_throwsIllegalArgumentException() {
        String invalidContent = "";
        assertThrows(IllegalArgumentException.class, () -> {
            new MedicalNote("15-04-2020", "Lydia Yu", invalidContent);
        });
    }

    @Test
    void isValidDate() {
        // invalid date
        assertFalse(MedicalNote.isValidDate(null)); // null input
        assertFalse(MedicalNote.isValidDate("")); // empty string
        assertFalse(MedicalNote.isValidDate("21/03/89")); // wrong format with /
        assertFalse(MedicalNote.isValidDate("23-04-00")); // wrong year format
        assertFalse(MedicalNote.isValidDate("2 Feb 1996")); // wrong format with letters

        // valid date
        assertTrue(MedicalNote.isValidDate("23-03-2000"));
        assertTrue(MedicalNote.isValidDate("01-07-1995"));
        assertTrue(MedicalNote.isValidDate("31-12-1995"));
    }

    @Test
    void isValidDoctorName() {
        // null doctor name
        assertThrows(NullPointerException.class, () -> MedicalNote.isValidDoctorName(null));

        // invalid doctor name
        assertFalse(MedicalNote.isValidDoctorName("")); // empty string
        assertFalse(MedicalNote.isValidDoctorName(" ")); // spaces only
        assertFalse(MedicalNote.isValidDoctorName("^")); // only non-alphanumeric characters
        assertFalse(MedicalNote.isValidDoctorName("peter*")); // contains non-alphanumeric characters

        // valid doctor name
        assertTrue(MedicalNote.isValidDoctorName("peter jack")); // alphabets only
        assertTrue(MedicalNote.isValidDoctorName("12345")); // numbers only
        assertTrue(MedicalNote.isValidDoctorName("peter the 2nd")); // alphanumeric characters
        assertTrue(MedicalNote.isValidDoctorName("Capital Tan")); // with capital letters
        assertTrue(MedicalNote.isValidDoctorName("David Roger Jackson Ray Jr 2nd")); // long names
    }

    @Test
    void isValidContent() {
        // null content
        assertThrows(NullPointerException.class, () -> MedicalNote.isValidContent(null));

        // invalid content
        assertFalse(MedicalNote.isValidContent("")); // empty string

        // valid content
        assertTrue(MedicalNote.isValidContent("Patient is asymptomatic.")); // any characters
        assertTrue(MedicalNote.isValidContent("#%@")); // any characters
    }
}
