package seedu.medibook.model.medicalnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.Date;

class MedicalNoteListTest {
    private final MedicalNote medicalNote1 = new MedicalNote(new Date("20-10-2019", true),
            "John", "Patient is awesome.");
    private final MedicalNote medicalNote2 = new MedicalNote(new Date("25-10-2019", true),
            "Gary", "Patient is bad.");

    @Test
    void add_emptyMedicalNoteList_success() {
        MedicalNoteList emptyMedicalNoteList = new MedicalNoteList();
        emptyMedicalNoteList.add(medicalNote1);

        MedicalNoteList expectedMedicalNoteList = new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote1)));
        assertEquals(expectedMedicalNoteList, emptyMedicalNoteList);
    }

    @Test
    void add_nonEmptyMedicalNoteList_successWithCorrectOrder() {
        MedicalNoteList partiallyFilledMedicalNoteList =
                new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote2)));
        partiallyFilledMedicalNoteList.add(medicalNote1);

        MedicalNoteList expectedMedicalNoteList = new MedicalNoteList(
                new LinkedList<>(Arrays.asList(medicalNote1, medicalNote2)));
        assertEquals(expectedMedicalNoteList, partiallyFilledMedicalNoteList);
        assertEquals(Arrays.asList(medicalNote1, medicalNote2), partiallyFilledMedicalNoteList.getMedicalNoteList());
    }

    @Test
    void makeCopy() {
        MedicalNoteList testList = new MedicalNoteList(new LinkedList<>(Arrays.asList(medicalNote1)));
        MedicalNoteList copiedList = testList.makeCopy();
        assertEquals(testList, copiedList);
        assertTrue(copiedList != testList);
    }
}
