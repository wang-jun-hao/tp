package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;

import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Model;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Patient;

/**
 * Adds a patient to the medi book.
 */
public class NoteCommand extends Command {

    public static final String COMMAND_WORD = "note";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a medical note to the current patient "
            + "in MediBook. "
            + "Parameters: "
            + PREFIX_DATE + "DATE OF BIRTH "
            + PREFIX_NAME + "NAME "
            + PREFIX_CONTENT + "MEDICAL NOTE CONTENT "
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_DATE + "04-11-1991 "
            + PREFIX_NAME + "John Doe "
            + PREFIX_CONTENT + "Patient has high fever and cough.";

    public static final String MESSAGE_SUCCESS = "New medical note entry added:\n%1$s";
    public static final String MESSAGE_DUPLICATE_NOTE = "This medical note entry already exists in the system";
    public static final String MESSAGE_NOTE_ON_LIST = "You can only add medical note to a patient when you are viewing "
            + "his/her patient profile. Access the patient profile before adding medical note.";

    private final MedicalNote newMedicalNote;

    /**
     * Creates an AddCommand to add the specified {@code Patient}
     */
    public NoteCommand(MedicalNote newMedicalNote) {
        requireNonNull(newMedicalNote);
        this.newMedicalNote = newMedicalNote;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Patient> patientOptional = model.getPatientToAccess();

        if (!patientOptional.isPresent()) {
            throw new CommandException(MESSAGE_NOTE_ON_LIST);
        }

        Patient displayedPatient = patientOptional.get();

        assert model.hasPatient(displayedPatient) : "Patient in context does not exist in model";

        displayedPatient.addMedicalNote(newMedicalNote);
        
        return new CommandResult(String.format(MESSAGE_SUCCESS, newMedicalNote));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof NoteCommand // instanceof handles nulls
                && newMedicalNote.equals(((NoteCommand) other).newMedicalNote));
    }
}
