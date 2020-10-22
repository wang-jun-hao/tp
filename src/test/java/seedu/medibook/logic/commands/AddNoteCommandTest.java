package seedu.medibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.commands.AddNoteCommand.MESSAGE_SUCCESS;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import org.junit.jupiter.api.Test;

import seedu.medibook.model.MediBook;
import seedu.medibook.model.Model;
import seedu.medibook.model.ModelManager;
import seedu.medibook.model.UserPrefs;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.commonfields.Name;
import seedu.medibook.model.doctor.Doctor;
import seedu.medibook.model.doctor.Mcr;
import seedu.medibook.model.medicalnote.Content;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for AddNoteCommand.
 */
public class AddNoteCommandTest {

    private final Model model = new ModelManager(getTypicalMediBook(), new UserPrefs());

    @Test
    public void execute_noteOnListView_failure() {
        // set model to hold no optional patient
        model.resetAccessedPatient();

        MedicalNote medicalNote = new MedicalNote(new Date("20-10-2019", true),
                new Doctor(new Name("John"), new Mcr("M02830P")),
                new Content("Patient is having fever."));
        AddNoteCommand addNoteCommand = new AddNoteCommand(medicalNote);
        assertCommandFailure(addNoteCommand, model, AddNoteCommand.MESSAGE_ADD_NOTE_ON_LIST);
    }

    @Test
    public void execute_duplicateNote_failure() {
        Patient targetPatient = model.getFilteredPatientList().get(0);

        model.accessPatient(targetPatient);

        MedicalNote duplicateMedicalNote = new MedicalNote(new Date("19-02-2020", true),
                new Doctor(new Name("John"), new Mcr("MP2819J")),
                new Content("Patient is good."));

        AddNoteCommand addNoteCommand = new AddNoteCommand(duplicateMedicalNote);
        assertCommandFailure(addNoteCommand, model, AddNoteCommand.MESSAGE_DUPLICATE_NOTE);
    }


    @Test
    public void execute_accessFirstPatientThenNote_successWithCorrectNoteAdded() {
        Patient targetPatient = model.getFilteredPatientList().get(0);

        model.accessPatient(targetPatient);

        MedicalNote medicalNote = new MedicalNote(new Date("21-10-2019", true),
                new Doctor(new Name("John"), new Mcr("M02830P")),
                new Content("Patient is having fever."));
        AddNoteCommand addNoteCommand = new AddNoteCommand(medicalNote);

        Patient resultingPatient = new PatientBuilder(targetPatient).build();
        resultingPatient.addMedicalNote(medicalNote);

        String expectedMessage = String.format(MESSAGE_SUCCESS, medicalNote);

        Model expectedModel = new ModelManager(new MediBook(model.getMediBook()), new UserPrefs());
        expectedModel.setPatient(model.getFilteredPatientList().get(0), resultingPatient);
        expectedModel.accessPatient(targetPatient);

        assertCommandSuccess(addNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        // same medical note -> returns true
        MedicalNote medicalNote = new MedicalNote(new Date("20-10-2019", true),
                new Doctor(new Name("John"), new Mcr("M02830P")),
                new Content("Patient is having fever."));
        AddNoteCommand addNoteCommand1 = new AddNoteCommand(medicalNote);
        AddNoteCommand addNoteCommand2 = new AddNoteCommand(medicalNote);
        assertTrue(addNoteCommand1.equals(addNoteCommand2));

        // different medical note -> returns false
        MedicalNote differentMedicalNote = new MedicalNote(new Date("21-10-2019", true),
                new Doctor(new Name("Gary"), new Mcr("M12838P")),
                new Content("Patient is having chills."));
        AddNoteCommand differentAddNoteCommand = new AddNoteCommand(differentMedicalNote);
        assertFalse(addNoteCommand1.equals(differentAddNoteCommand));

        // null -> returns false
        assertFalse(addNoteCommand1.equals(null));
    }

}
