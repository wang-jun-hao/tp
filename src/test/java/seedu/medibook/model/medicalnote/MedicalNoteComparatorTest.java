package seedu.medibook.model.medicalnote;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.Date;

class MedicalNoteComparatorTest {
    private final MedicalNoteComparator comparator = new MedicalNoteComparator();

    private final MedicalNote medicalNote1 =
            new MedicalNote(new Date("19-02-2020", true), "Dr John",
                    "Patient is sick.");
    private final MedicalNote medicalNote2 =
            new MedicalNote(new Date("19-02-2020", true), "Dr John",
                    "Patient is sick.");
    private final MedicalNote medicalNote3 =
            new MedicalNote(new Date("19-02-2020", true), "Dr Mary",
                    "Patient is healthy.");
    private final MedicalNote medicalNote4 =
            new MedicalNote(new Date("30-12-2019", true), "Dr John",
                    "Patient is sick.");
    private final MedicalNote medicalNote5 =
            new MedicalNote(new Date("30-12-2019", true), "Dr Mary",
                    "Patient is healthy.");

    @Test
    void compare_sameDateSameFields_zero() {

        assertTrue(comparator.compare(medicalNote1, medicalNote2) == 0);
    }

    @Test
    void compare_sameDateDiffFields_zero() {
        assertTrue(comparator.compare(medicalNote1, medicalNote3) == 0);
    }

    @Test
    void compare_earlierDateSameFields_negative() {
        assertTrue(comparator.compare(medicalNote4, medicalNote1) < 0);
    }

    @Test
    void compare_earlierDateDiffFields_negative() {
        assertTrue(comparator.compare(medicalNote5, medicalNote1) < 0);
    }

    @Test
    void compare_laterDateSameFields_negative() {
        assertTrue(comparator.compare(medicalNote1, medicalNote4) > 0);
    }

    @Test
    void compare_laterDateDiffFields_negative() {
        assertTrue(comparator.compare(medicalNote1, medicalNote5) > 0);
    }
}
