package seedu.medibook.model;

import javafx.collections.ObservableList;
import seedu.medibook.model.medicalnote.MedicalNote;

/**
 * Unmodifiable view of a medical notes list
 */
public interface ReadOnlyMedicalNoteList {

    /**
     * Returns an unmodifiable view of the patient's medical notes.
     */
    ObservableList<MedicalNote> getMedicalNoteList();

}
