package seedu.medibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medibook.logic.commands.NoteCommand.MESSAGE_SUCCESS;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.Date;
import seedu.medibook.model.MediBook;
import seedu.medibook.model.Model;
import seedu.medibook.model.ModelManager;
import seedu.medibook.model.UserPrefs;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for NoteCommand.
 */
public class NoteCommandTest {

    private final Model model = new ModelManager(getTypicalMediBook(), new UserPrefs());

    @Test
    public void execute_noteOnListView_failure() {
        // set model to hold no optional patient
        model.resetAccessedPatient();

        MedicalNote medicalNote = new MedicalNote(new Date("20-10-2019", true), "John",
                "Patient is having fever.");
        NoteCommand noteCommand = new NoteCommand(medicalNote);
        assertCommandFailure(noteCommand, model, NoteCommand.MESSAGE_NOTE_ON_LIST);
    }

    @Test
    public void execute_accessFirstPatientThenNote_successWithCorrectNoteAdded() {
        Patient targetPatient = model.getFilteredPatientList().get(0);

        //TODO: make sure medical note list is loaded first
        model.accessPatient(targetPatient);

        MedicalNote medicalNote = new MedicalNote(new Date("20-10-2019", true), "John",
                "Patient is having fever.");
        NoteCommand noteCommand = new NoteCommand(medicalNote);

        Patient resultingPatient = new PatientBuilder(targetPatient).build();
        resultingPatient.addMedicalNote(medicalNote);

        String expectedMessage = String.format(MESSAGE_SUCCESS, medicalNote);

        Model expectedModel = new ModelManager(new MediBook(model.getMediBook()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), resultingPatient);
        expectedModel.accessPatient(targetPatient);
        expectedModel.setShouldLoadMedicalNotes(false);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same medical note -> returns true
        MedicalNote medicalNote = new MedicalNote(new Date("20-10-2019", true), "John",
                "Patient is having fever.");
        NoteCommand noteCommand1 = new NoteCommand(medicalNote);
        NoteCommand noteCommand2 = new NoteCommand(medicalNote);
        assertTrue(noteCommand1.equals(noteCommand2));

        // different medical note -> returns false
        MedicalNote differentMedicalNote = new MedicalNote(new Date("21-10-2019", true), "Gary",
                "Patient is having chills.");
        NoteCommand differentNoteCommand = new NoteCommand(differentMedicalNote);
        assertFalse(noteCommand1.equals(differentNoteCommand));

        // null -> returns false
        assertFalse(noteCommand1.equals(null));
    }

}
