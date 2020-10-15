package seedu.medibook.model.medicalnote;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import seedu.medibook.model.ReadOnlyMedicalNoteList;

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
     * Initializes the data of this {@code MedicalNoteList} with {@code newData}.
     */
    public MedicalNoteList(ReadOnlyMedicalNoteList toBeCopied) {
        this();
        toBeCopied.getMedicalNoteList().forEach(this::add);
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

    public MedicalNoteList makeCopy() {
        return new MedicalNoteList(innerList);
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
}
