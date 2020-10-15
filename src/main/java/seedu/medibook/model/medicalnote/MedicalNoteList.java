package seedu.medibook.model.medicalnote;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents a list of medical notes for a Patient in MediBook.
 */
public class MedicalNoteList {
    private final List<MedicalNote> innerList;

    public MedicalNoteList() {
        innerList = new LinkedList<>();
    }

    public MedicalNoteList(List<MedicalNote> innerList) {
        this.innerList = innerList;
    }

    /**
     * Adds a new medical note to the list of medical notes.
     * @param newMedicalNote medical note to be added to the list.
     */
    public void add(MedicalNote newMedicalNote) {
        innerList.add(newMedicalNote);
    }

    @Override
    public String toString() {
        String result = "";
        for (MedicalNote medicalNote : innerList) {
            result += medicalNote.toString() + "\n";
        }
        return result;
    }
}
