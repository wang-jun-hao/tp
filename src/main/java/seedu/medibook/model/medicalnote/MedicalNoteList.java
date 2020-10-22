package seedu.medibook.model.medicalnote;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Represents a list of medical notes for a Patient in MediBook.
 */
public class MedicalNoteList implements ReadOnlyMedicalNoteList {
    private final List<MedicalNote> innerList;

    /**
     * Constructs an empty medical note list object.
     */
    public MedicalNoteList() {
        innerList = new LinkedList<>();
    }

    /**
     * Constructs medical note list object containing medical notes in the given list.
     * A new copy of the list is made to be stored as the inner list, so that operations on the argument
     * list does not affect this medical note list object.
     * @param listOfMedicalNotes list of medical notes
     */
    public MedicalNoteList(List<MedicalNote> listOfMedicalNotes) {
        List<MedicalNote> copyOfList = new LinkedList<>(listOfMedicalNotes);
        this.innerList = copyOfList;
    }

    /**
     * Adds a new medical note to the list of medical notes.
     * @param newMedicalNote medical note to be added to the list.
     */
    public void add(MedicalNote newMedicalNote) {
        innerList.add(newMedicalNote);
    }

    public boolean isEmpty() {
        return innerList.isEmpty();
    }

    public MedicalNoteList makeCopy() {
        return new MedicalNoteList(innerList);
    }

    /**
     * Returns number of medical notes contained within this list.
     */
    public int size() {
        return innerList.size();
    }

    /**
     * Deletes the medical note at the specified index from the list.
     * @param index Zero-based index of medical note.
     */
    public void deleteMedicalNoteAtIndex(int index) {
        assert index < size() : "Attempting to delete with out-of-range index";

        innerList.remove(index);
    }

    /**
     * Retrieves the medical note at the specified index in the list.
     * @param index Zero-based index of medical note.
     * @return
     */
    public MedicalNote getMedicalNoteAtIndex(int index) {
        assert index < size() : "Attempting to get medical note with out-of-range index";

        return innerList.get(index);
    }

    @Override
    public List<MedicalNote> getMedicalNoteList() {
        return Collections.unmodifiableList(innerList);
    }

    @Override
    public String toString() {
        String result = "";
        for (MedicalNote medicalNote : innerList) {
            result += medicalNote.toString() + "\n";
        }
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MedicalNoteList)) {
            return false;
        }


        MedicalNoteList otherList = (MedicalNoteList) other;
        return otherList.innerList.equals(innerList);
    }


}
