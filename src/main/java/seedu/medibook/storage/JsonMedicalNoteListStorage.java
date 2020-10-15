package seedu.medibook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.FileUtil;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.ReadOnlyMedicalNoteList;

/**
 * A class to access MediBook data stored as a json file on the hard disk.
 */
public class JsonMedicalNoteListStorage implements MedicalNoteListStorage {

    public static final String DIR_NAME = "medicalnotes";
    private static final Logger logger = LogsCenter.getLogger(JsonMedicalNoteListStorage.class);

    private final Path filePath;

    public JsonMedicalNoteListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Path getMedicalNoteListFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyMedicalNoteList> readMedicalNoteList() throws DataConversionException, IOException {
        return readMedicalNoteList(filePath);
    }

    /**
     * Similar to {@link #readMedicalNoteList()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Path filePath)
            throws DataConversionException, IOException {
        requireNonNull(filePath);

        Optional<JsonSerializableMedicalNoteList> jsonMedicalNoteList = JsonUtil.readJsonFile(
                filePath, JsonSerializableMedicalNoteList.class);
        if (!jsonMedicalNoteList.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonMedicalNoteList.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataConversionException(ive);
        }
    }

    @Override
    public void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList) throws IOException {
        saveMedicalNoteList(medicalNoteList, filePath);
    }

    /**
     * Similar to {@link #saveMedicalNoteList(ReadOnlyMedicalNoteList)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Path filePath) throws IOException {
        requireNonNull(medicalNoteList);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableMedicalNoteList(medicalNoteList), filePath);
    }

}
