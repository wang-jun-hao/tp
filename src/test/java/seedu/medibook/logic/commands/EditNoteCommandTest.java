package seedu.medibook.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.medibook.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_FIRST;
import static seedu.medibook.testutil.TypicalIndexes.INDEX_SECOND;
import static seedu.medibook.testutil.TypicalPatients.ALICE_MEDICAL_NOTE_1;
import static seedu.medibook.testutil.TypicalPatients.ALICE_MEDICAL_NOTE_2;
import static seedu.medibook.testutil.TypicalPatients.ALICE_NUM_OF_MEDICAL_NOTES;
import static seedu.medibook.testutil.TypicalPatients.getTypicalMediBook;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import seedu.medibook.commons.core.Messages;
import seedu.medibook.commons.core.index.Index;
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
import seedu.medibook.model.medicalnote.MedicalNoteList;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.testutil.PatientBuilder;

/**
 * Contains integration tests and unit tests for EditNoteCommand.
 */
public class EditNoteCommandTest {

    private Model model = new ModelManager(getTypicalMediBook(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecified_success() {
        Patient targetPatient = model.getFilteredPatientList().get(0);
        model.accessPatient(targetPatient);

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setDate(new Date("24-12-2018", true));
        descriptor.setName(new Name("Ian Bob"));
        descriptor.setMcr(new Mcr("M00000X"));
        descriptor.setContent(new Content("Patient has high fever."));

        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST, descriptor);

        MedicalNote editedNote = new MedicalNote(new Date("24-12-2018", true),
                new Doctor(new Name("Ian Bob"), new Mcr("M00000X")),
                new Content("Patient has high fever."));

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(new MediBook(model.getMediBook()), new UserPrefs());
        Patient expectedTargetPatient = expectedModel.getFilteredPatientList().get(0);
        expectedTargetPatient = new PatientBuilder(expectedTargetPatient).build();
        LinkedList<MedicalNote> expectedMedicalNoteLinkedList =
                new LinkedList<>(Arrays.asList(editedNote, ALICE_MEDICAL_NOTE_2));
        expectedTargetPatient.setMedicalNoteList(new MedicalNoteList(expectedMedicalNoteLinkedList));

        expectedModel.accessPatient(expectedTargetPatient);
        expectedModel.setShouldLoadMedicalNotes(false);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Patient targetPatient = model.getFilteredPatientList().get(0);
        model.accessPatient(targetPatient);

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setContent(new Content("Patient has high fever."));

        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST, descriptor);

        MedicalNote editedNote = new MedicalNote(new Date("19-02-2020", true),
                new Doctor(new Name("John"), new Mcr("MP2819J")),
                new Content("Patient has high fever."));

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(new MediBook(model.getMediBook()), new UserPrefs());
        Patient expectedTargetPatient = expectedModel.getFilteredPatientList().get(0);
        expectedTargetPatient = new PatientBuilder(expectedTargetPatient).build();
        LinkedList<MedicalNote> expectedMedicalNoteLinkedList =
                new LinkedList<>(Arrays.asList(editedNote, ALICE_MEDICAL_NOTE_2));
        expectedTargetPatient.setMedicalNoteList(new MedicalNoteList(expectedMedicalNoteLinkedList));

        expectedModel.accessPatient(expectedTargetPatient);
        expectedModel.setShouldLoadMedicalNotes(false);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        Patient targetPatient = model.getFilteredPatientList().get(0);
        model.accessPatient(targetPatient);

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();

        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST, descriptor);

        MedicalNote editedNote = ALICE_MEDICAL_NOTE_1;

        String expectedMessage = String.format(EditNoteCommand.MESSAGE_EDIT_NOTE_SUCCESS, editedNote);

        Model expectedModel = new ModelManager(new MediBook(model.getMediBook()), new UserPrefs());
        Patient expectedTargetPatient = expectedModel.getFilteredPatientList().get(0);
        expectedTargetPatient = new PatientBuilder(expectedTargetPatient).build();
        LinkedList<MedicalNote> expectedMedicalNoteLinkedList =
                new LinkedList<>(Arrays.asList(editedNote, ALICE_MEDICAL_NOTE_2));
        expectedTargetPatient.setMedicalNoteList(new MedicalNoteList(expectedMedicalNoteLinkedList));

        expectedModel.accessPatient(expectedTargetPatient);
        expectedModel.setShouldLoadMedicalNotes(false);

        assertCommandSuccess(editNoteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateMedicalNote_failure() {
        Patient targetPatient = model.getFilteredPatientList().get(0);
        model.accessPatient(targetPatient);

        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setDate(new Date("25-08-2020", true));
        descriptor.setName(new Name("John"));
        descriptor.setMcr(new Mcr("MP2819J"));
        descriptor.setContent(new Content("Patient is bad."));

        EditNoteCommand editNoteCommand = new EditNoteCommand(INDEX_FIRST, descriptor);

        assertCommandFailure(editNoteCommand, model, EditNoteCommand.MESSAGE_DUPLICATE_NOTE);
    }

    @Test
    public void execute_invalidMedicalNoteIndex_failure() {
        Patient targetPatient = model.getFilteredPatientList().get(0);
        model.accessPatient(targetPatient);

        Index outOfBoundIndex = Index.fromOneBased(ALICE_NUM_OF_MEDICAL_NOTES + 1);
        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setName(new Name("Berry"));

        EditNoteCommand editNoteCommand = new EditNoteCommand(outOfBoundIndex, descriptor);

        assertCommandFailure(editNoteCommand, model, Messages.MESSAGE_INVALID_NOTE_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        EditNoteCommand.EditNoteDescriptor descriptor = new EditNoteCommand.EditNoteDescriptor();
        descriptor.setName(new Name("Bob"));

        final EditNoteCommand standardCommand = new EditNoteCommand(INDEX_FIRST, descriptor);

        // same values -> returns true
        EditNoteCommand.EditNoteDescriptor descriptor2 = new EditNoteCommand.EditNoteDescriptor();
        descriptor2.setName(new Name("Bob"));
        EditNoteCommand commandWithSameValues = new EditNoteCommand(INDEX_FIRST, descriptor2);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditNoteCommand(INDEX_SECOND, descriptor)));

        // different descriptor -> returns false
        EditNoteCommand.EditNoteDescriptor descriptor3 = new EditNoteCommand.EditNoteDescriptor();
        descriptor3.setMcr(new Mcr("M92638X"));
        assertFalse(standardCommand.equals(new EditNoteCommand(INDEX_FIRST, descriptor3)));
    }

}
