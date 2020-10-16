package seedu.medibook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.medibook.testutil.Assert.assertThrows;
import static seedu.medibook.testutil.TypicalMedicalNotes.VALID_MEDICAL_NOTE1;
import static seedu.medibook.testutil.TypicalMedicalNotes.VALID_MEDICAL_NOTE2;
import static seedu.medibook.testutil.TypicalMedicalNotes.getTypicalMedicalNoteList;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.medicalnote.ReadOnlyMedicalNoteList;
import seedu.medibook.model.patient.Ic;

public class JsonMedicalNoteListStorageTest {
    private static final Path TEST_DATA_FOLDER =
            Paths.get("src", "test", "data", "JsonMedicalNoteListStorageTest");
    private static final Ic IC = new Ic("S7329204G");

    @TempDir
    public Path testFolder;

    @Test
    public void readMedicalNoteList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readMedicalNoteList(null, IC));
    }

    private java.util.Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(String filePath, Ic ic) throws Exception {
        return new JsonMedicalNoteListStorage(Paths.get(filePath))
                .readMedicalNoteList(addToTestDataPathIfNotNull(filePath), ic);
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readMedicalNoteList("NonExistentFile.json", IC).isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataConversionException.class, () ->
                readMedicalNoteList("notJsonFormatMedicalNotes", IC));
    }

    @Test
    public void readMedicalNoteList_invalidPatientMedicalNotes_throwDataConversionException() {
        assertThrows(DataConversionException.class, () -> readMedicalNoteList("invalidPatientMedicalNotes", IC));
    }

    @Test
    public void readMedicalNoteList_invalidAndValidPatientMedicalNotes_throwDataConversionException() {
        assertThrows(DataConversionException.class, () ->
                readMedicalNoteList("invalidAndValidPatientMedicalNotes", IC));
    }

    @Test
    public void readAndSaveMedicalNoteList_allInOrder_success() throws Exception {
        Path filePath = testFolder.resolve("TempMedicalNotes");
        MedicalNoteList original = getTypicalMedicalNoteList();
        JsonMedicalNoteListStorage jsonMedicalNoteListStorage = new JsonMedicalNoteListStorage(filePath);

        // Save in new file and read back
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, IC);
        ReadOnlyMedicalNoteList readBack = jsonMedicalNoteListStorage.readMedicalNoteList(filePath, IC).get();
        assertEquals(original, new MedicalNoteList(readBack));

        // Modify data, overwrite exiting file, and read back
        original.add(VALID_MEDICAL_NOTE1);
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, filePath, IC);
        readBack = jsonMedicalNoteListStorage.readMedicalNoteList(filePath, IC).get();
        assertEquals(original, new MedicalNoteList(readBack));

        // Save and read without specifying file path
        original.add(VALID_MEDICAL_NOTE2);
        jsonMedicalNoteListStorage.saveMedicalNoteList(original, IC); // file path not specified
        readBack = jsonMedicalNoteListStorage.readMedicalNoteList(IC).get(); // file path not specified
        assertEquals(original, new MedicalNoteList(readBack));

    }

    @Test
    public void saveMedicalNoteList_nullMMedicalNoteList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMedicalNoteList(null, "SomeFile.json"));
    }

    /**
     * Saves {@code medicalNoteList} at the specified {@code filePath}.
     */
    private void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, String filePath) {
        try {
            new JsonMedicalNoteListStorage(Paths.get(filePath))
                    .saveMedicalNoteList(medicalNoteList, addToTestDataPathIfNotNull(filePath), IC);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveMedicalNoteList_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveMedicalNoteList(new MedicalNoteList(), null));
    }
}
