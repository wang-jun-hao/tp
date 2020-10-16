package seedu.medibook.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.commons.util.FileUtil;
import seedu.medibook.commons.util.JsonUtil;
import seedu.medibook.model.ReadOnlyMedicalNoteList;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Ic;

/**
 * A class to access MediBook data stored as a json file on the hard disk.
 */
public class JsonMedicalNoteListStorage implements MedicalNoteListStorage {

    public static final String NAME_DIR = "medicalnotes";
    private static final String NAME_EXTENSION = ".json";
    private static final Logger logger = LogsCenter.getLogger(JsonMedicalNoteListStorage.class);

    private final Path filePath;

    public JsonMedicalNoteListStorage(Path filePath) {
        this.filePath = filePath;
    }

    @Override
    public Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Ic ic) throws DataConversionException {
        return readMedicalNoteList(filePath, ic);
    }

    /**
     * Similar to {@link #readMedicalNoteList(Ic ic)}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataConversionException if the file is not in the correct format.
     */
    public Optional<ReadOnlyMedicalNoteList> readMedicalNoteList(Path filePath, Ic ic) throws DataConversionException {
        requireNonNull(filePath);

        Path medicalNotesPath = filePath.resolve(NAME_DIR).resolve(ic.toString() + NAME_EXTENSION);
        if (!FileUtil.isFileExists(medicalNotesPath)) {
            return Optional.empty();
        }

        Optional<JsonSerializableMedicalNoteList> jsonMedicalNoteList = JsonUtil.readJsonFile(
                medicalNotesPath, JsonSerializableMedicalNoteList.class);
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
    public void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Ic ic) throws IOException {
        saveMedicalNoteList(medicalNoteList, filePath, ic);
    }

    /**
     * Similar to {@link #saveMedicalNoteList(ReadOnlyMedicalNoteList, Ic ic)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveMedicalNoteList(ReadOnlyMedicalNoteList medicalNoteList, Path filePath, Ic ic) throws IOException {
        requireNonNull(medicalNoteList);
        requireNonNull(filePath);

        Path medicalNotesPath = filePath.resolve(NAME_DIR).resolve(ic.toString() + NAME_EXTENSION);
        FileUtil.createIfMissing(medicalNotesPath);
        JsonUtil.saveJsonFile(new JsonSerializableMedicalNoteList(medicalNoteList), medicalNotesPath);
    }

}
