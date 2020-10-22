package seedu.medibook.logic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.medibook.commons.core.GuiSettings;
import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.commons.exceptions.DataConversionException;
import seedu.medibook.logic.commands.Command;
import seedu.medibook.logic.commands.CommandResult;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.logic.parser.MediBookParser;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.Model;
import seedu.medibook.model.ReadOnlyMediBook;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_SAVE_ERROR_MESSAGE = "Could not save data to file: ";
    public static final String FILE_LOAD_ERROR_MESSAGE = "Could not read data from file: ";
    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final MediBookParser mediBookParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        mediBookParser = new MediBookParser();
    }

    @Override
    public Optional<Patient> getPatientToAccess() {
        return model.getPatientToAccess();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {

        // Logging, safe to ignore
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        // Parse user input from String to a Command
        Command command = mediBookParser.parseCommand(commandText);
        // Executes the Command and stores the result
        commandResult = command.execute(model);

        try {
            // We can deduce that the previous line of code modifies model in some way
            // since it's being stored here.
            storage.saveMediBook(model.getMediBook());
            Optional<Patient> accessPatient = model.getPatientToAccess();
            if (accessPatient.isPresent()) {
                handleMedicalNoteListIo(accessPatient.get());
            }
        } catch (IOException ioe) {
            throw new CommandException(FILE_SAVE_ERROR_MESSAGE + ioe, ioe);
        } catch (DataConversionException dce) {
            throw new CommandException(FILE_LOAD_ERROR_MESSAGE + dce, dce);
        }

        return commandResult;
    }

    private void handleMedicalNoteListIo(Patient patient) throws IOException, DataConversionException {
        if (patient.getMedicalNoteList().isEmpty()) {
            storage.readMedicalNoteList(patient.getIc())
                    .ifPresent(medicalNotes ->
                            patient.setMedicalNoteList(new MedicalNoteList(medicalNotes.getMedicalNoteList())));
        } else {
            storage.saveMedicalNoteList(patient.getMedicalNoteList(), patient.getIc());
        }
    }

    @Override
    public ReadOnlyMediBook getMediBook() {
        return model.getMediBook();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public Path getMediBookFilePath() {
        return model.getMediBookFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }

    @Override
    public void processLoginInfo(String username, String password) {
        boolean isAccount = storage.isAccount(username, password);
        if (isAccount) {
            System.out.println("yes");
        } else {
            System.out.println("bye");
        }
    }
}
