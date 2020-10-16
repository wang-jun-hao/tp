package seedu.medibook.testutil;

import seedu.medibook.model.Date;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.medicalnote.MedicalNoteList;

/**
 * A utility class containing a list of {@code MedicalNote} objects to be used in tests.
 */
public class TypicalMedicalNotes {
    // Medical Notes
    public static final MedicalNote VALID_MEDICAL_NOTE1 = new MedicalNote(
            new Date("01-01-2020", true),
            "Dr Strange",
            "Patient is in critical condition."
    );

    public static final MedicalNote VALID_MEDICAL_NOTE2 = new MedicalNote(
            new Date("17-01-2020", true),
            "Dr Who",
            "Patient is recovering well."
    );

    public static final MedicalNote VALID_MEDICAL_NOTE3 = new MedicalNote(
            new Date("23-01-2020", true),
            "Dr Nick",
            "Patient has fully recovered."
    );

    /**
     * Returns a {@code MedicalNoteList} with all the typical medical notes.
     */
    public static MedicalNoteList getTypicalMedicalNoteList() {
        MedicalNoteList medicalNotes = new MedicalNoteList();
        medicalNotes.add(VALID_MEDICAL_NOTE1);
        medicalNotes.add(VALID_MEDICAL_NOTE2);
        medicalNotes.add(VALID_MEDICAL_NOTE3);
        return medicalNotes;
    }
}
