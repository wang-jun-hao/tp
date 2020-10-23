package seedu.medibook.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Optional;

import seedu.medibook.commons.core.Messages;
import seedu.medibook.commons.core.index.Index;
import seedu.medibook.logic.commands.exceptions.CommandException;
import seedu.medibook.model.Model;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Patient;

/**
 * Deletes a medical note identified using it's displayed index in the patient's profile from MediBook.
 */
public class DeleteNoteCommand extends Command {

    public static final String COMMAND_WORD = "deletenote";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the medical note identified by the index number used in the displayed index in the"
            + " patient's profile page.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_NOTE_SUCCESS = "Deleted Medical Note: %1$s";
    public static final String MESSAGE_DELETE_NOTE_ON_LIST = "You can only delete a medical note from a patient "
            + "when you are viewing his/her patient profile. Access the patient profile before "
            + "deleting a medical note.";

    private final Index targetIndex;

    public DeleteNoteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Optional<Patient> patientOptional = model.getPatientToAccess();

        if (patientOptional.isEmpty()) {
            throw new CommandException(MESSAGE_DELETE_NOTE_ON_LIST);
        }

        Patient displayedPatient = patientOptional.get();

        assert model.hasPatient(displayedPatient) : "Patient in context does not exist in model";

        MedicalNoteList medicalNoteList = displayedPatient.getMedicalNoteList();

        int indexZeroBased = targetIndex.getZeroBased();

        if (indexZeroBased >= medicalNoteList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
        }

        MedicalNote noteToDelete = medicalNoteList.getMedicalNoteAtIndex(indexZeroBased);

        medicalNoteList.deleteMedicalNoteAtIndex(indexZeroBased);

        return new CommandResult(String.format(MESSAGE_DELETE_NOTE_SUCCESS, noteToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteNoteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteNoteCommand) other).targetIndex)); // state check
    }
}
