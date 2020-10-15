package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.ReadOnlyMedicalNoteList;

/**
 * Represents a storage for {@link MedicalNoteList}.
 */
public interface MedicalNoteListStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getMedicalNoteListFilePath();

    /**
     * Returns MedicalNoteList data as a {@link ReadOnlyMedicalNoteList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMedicalNoteList> readMedicalNoteList() throws DataConversionException, IOException;

    /**
     * @see #readMedicalNoteList()
     */
    Optional<ReadOnlyMediBook> readMedicalNoteList(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMedicalNoteList} to the storage.
     * @param medicalNoteList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList) throws IOException;

    /**
     * @see #saveMedicalNoteList(ReadOnlyMedicalNoteList)
     */
    void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Path filePath) throws IOException;

}
