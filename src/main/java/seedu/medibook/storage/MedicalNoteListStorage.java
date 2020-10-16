package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.medicalnote.ReadOnlyMedicalNoteList;
import seedu.medibook.model.patient.Ic;

/**
 * Represents a storage for {@link MedicalNoteList}.
 */
public interface MedicalNoteListStorage {
    /**
     * Returns MedicalNoteList data as a {@link ReadOnlyMedicalNoteList}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Ic ic) throws DataConversionException, IOException;

    /**
     * @see #readMedicalNoteList(Ic ic)
     */
    Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Path filePath, Ic ic)
            throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyMedicalNoteList} to the storage.
     * @param medicalNoteList cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Ic ic) throws IOException;

    /**
     * @see #saveMedicalNoteList(ReadOnlyMedicalNoteList, Ic ic)
     */
    void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Path filePath, Ic ic) throws IOException;

}
