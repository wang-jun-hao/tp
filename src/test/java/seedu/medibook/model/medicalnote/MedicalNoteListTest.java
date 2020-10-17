package seedu.medibook.model.medicalnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.Date;

class MedicalNoteListTest {
    private final MedicalNote medicalNote = new MedicalNote(new Date("20-10-2019", true),
            "John", "Patient is awesome.");
    private final MedicalNote medicalNote2 = new MedicalNote(new Date("25-10-2019", true),
            "Gary", "Patient is bad.");

    @Test
    void add_emptyMedicalNoteList_success() {
        MedicalNoteList emptyMedicalNoteList = new MedicalNoteList();
        emptyMedicalNoteList.add(medicalNote);

        MedicalNoteList expectedMedicalNoteList = new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote)));
        assertEquals(expectedMedicalNoteList, emptyMedicalNoteList);
    }

    @Test
    void add_nonEmptyMedicalNoteList_success() {
        MedicalNoteList partiallyFilledMedicalNoteList =
                new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote)));
        partiallyFilledMedicalNoteList.add(medicalNote2);

        MedicalNoteList expectedMedicalNoteList = new MedicalNoteList(
                new LinkedList<>(Arrays.asList(medicalNote, medicalNote2)));
        assertEquals(expectedMedicalNoteList, partiallyFilledMedicalNoteList);
    }

    @Test
    void makeCopy() {
        MedicalNoteList testList = new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote)));
        MedicalNoteList copiedList = testList.makeCopy();
        assertEquals(testList, copiedList);
        assertTrue(copiedList != testList);
    }
}
