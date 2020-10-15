package seedu.medibook.model.medicalnote;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a list of medical notes for a Patient in MediBook.
 */
public class MedicalNoteList {
    private List<MedicalNote> innerList;

    public MedicalNoteList() {
        innerList = new LinkedList<>();
    }

    public void add(MedicalNote newMedicalNote) {
        innerList.add(newMedicalNote);
    }
}
