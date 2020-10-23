package seedu.medibook.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.ReadOnlyUserPrefs;
import seedu.medibook.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends MediBookStorage, MedicalNoteListStorage, UserPrefsStorage, UserAccountStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getMediBookFilePath();

    @Override
    Optional<ReadOnlyMediBook> readMediBook() throws DataConversionException, IOException;

    @Override
    void saveMediBook(ReadOnlyMediBook mediBook) throws IOException;

    @Override
    boolean isAccount(String username, String password);

}
