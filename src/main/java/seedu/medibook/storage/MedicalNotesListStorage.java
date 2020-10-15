package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.ReadOnlyMedicalNotesList;

/**
 * Represents a storage for {@link MedicalNotesList}.
 */
public interface MedicalNotesListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMedicalNotesListFilePath();

    /**
     * Returns MedicalNotesList data as a {@link ReadOnlyMedicalNotesList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMedicalNotesList> readMedicalNotesList() throws DataConversionException, IOException;

    /**
     * @see #readMedicalNotesList()
     */
    Optional<ReadOnlyMediBook> readMediBook(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMedicalNotesList} to the storage.
     * @param medicalNotesList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMedicalNotesList(ReadOnlyMedicalNotesList medicalNotesList) throws IOException;

    /**
     * @see #saveMedicalNotesList(ReadOnlyMedicalNotesList)
     */
    void saveMedicalNotesList(ReadOnlyMedicalNotesList medicalNotesList, Path filePath) throws IOException;

}
