package seedu.medibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.medibook.testutil.TypicalPatients.OUT_OF_RANGE_NOTE_INDEX;
import static seedu.medibook.testutil.TypicalPatients.VALID_NOTE_INDEX;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.core.Messages;
import seedu.medibook.commons.core.index.Index;
import seedu.medibook.model.Model;
import seedu.medibook.model.ModelManager;
import seedu.medibook.model.UserPrefs;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model, Logic) and unit tests for
 * {@code DeleteNoteommand}.
 */
public class DeleteNoteCommandTest {

    private Model model = new ModelManager(getTypicalMediBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() {
        Index validIndex = Index.fromOneBased(VALID_NOTE_INDEX);

        Patient targetPatient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(validIndex);
        MedicalNote medicalNoteToDelete =
                targetPatient.getMedicalNoteList().getMedicalNoteAtIndex(validIndex.getZeroBased());

        model.accessPatient(targetPatient);

        String expectedMessage = String.format(DeleteNoteCommand.MESSAGE_DELETE_NOTE_SUCCESS, medicalNoteToDelete);

        ModelManager expectedModel = new ModelManager(model.getMediBook(), new UserPrefs());
        Patient targetPatientWithDeletedNote = new PatientBuilder(targetPatient).build();
        targetPatientWithDeletedNote.getMedicalNoteList().deleteMedicalNoteAtIndex(validIndex.getZeroBased());
        expectedModel.setPatient(targetPatient, targetPatientWithDeletedNote);
        expectedModel.accessPatient(targetPatient);

        assertCommandSuccess(deleteNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_outOfRangeIndex_exceptionThrown() {
        Index outOfRangeIndex = Index.fromOneBased(OUT_OF_RANGE_NOTE_INDEX);

        Patient targetPatient = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        DeleteNoteCommand deleteNoteCommand = new DeleteNoteCommand(outOfRangeIndex);

        model.accessPatient(targetPatient);

        assertCommandFailure(deleteNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteNoteCommand deleteFirstNoteCommand = new DeleteNoteCommand(Index.fromOneBased(1));
        DeleteNoteCommand deleteSecondNoteCommand = new DeleteNoteCommand(Index.fromOneBased(2));

        // same object -> returns true
        assertTrue(deleteFirstNoteCommand.equals(deleteFirstNoteCommand));

        // same values -> returns true
        DeleteNoteCommand deleteFirstNoteCommandCopy = new DeleteNoteCommand(Index.fromOneBased(1));
        assertTrue(deleteFirstNoteCommand.equals(deleteFirstNoteCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstNoteCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstNoteCommand.equals(null));

        // different index -> returns false
        assertFalse(deleteFirstNoteCommand.equals(deleteSecondNoteCommand));
    }
}
