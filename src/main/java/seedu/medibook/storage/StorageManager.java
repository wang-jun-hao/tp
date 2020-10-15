package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.ReadOnlyUserPrefs;
import seedu.medibook.model.UserPrefs;

/**
 * Manages storage of MediBook data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private MediBookStorage mediBookStorage;
    private UserPrefsStorage userPrefsStorage;
    private MedicalNoteListStorage medicalNoteListStorage;

    /**
     * Creates a {@code StorageManager} with the given {@code MediBookStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(MediBookStorage mediBookStorage, UserPrefsStorage userPrefsStorage,
                          MedicalNoteListStorage medicalNoteListStorage) {
        super();
        this.mediBookStorage = mediBookStorage;
        this.userPrefsStorage = userPrefsStorage;
        this.medicalNoteListStorage = medicalNoteListStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ MediBook methods ==============================

    @Override
    public Path getMediBookFilePath() {
        return mediBookStorage.getMediBookFilePath();
    }

    @Override
    public Optional<ReadOnlyMediBook> readMediBook() throws DataConversionException, IOException {
        return readMediBook(mediBookStorage.getMediBookFilePath());
    }

    @Override
    public Optional<ReadOnlyMediBook> readMediBook(Path filePath) throws DataConversionException, IOException {
        logger.fine("Attempting to read data from file: " + filePath);
        return mediBookStorage.readMediBook(filePath);
    }

    @Override
    public void saveMediBook(ReadOnlyMediBook mediBook) throws IOException {
        saveMediBook(mediBook, mediBookStorage.getMediBookFilePath());
    }

    @Override
    public void saveMediBook(ReadOnlyMediBook mediBook, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        mediBookStorage.saveMediBook(mediBook, filePath);
    }

}
