package seedu.medibook.model;

import java.util.List;

import seedu.medibook.model.medicalnote.MedicalNote;

/**
 * Unmodifiable view of a medical notes list
 */
public interface ReadOnlyMedicalNoteList {

    /**
     * Returns an unmodifiable view of the patient's medical notes.
     */
    List<MedicalNote> getMedicalNoteList();

}
