package seedu.medibook.model;

import java.util.Optional;

import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Patient;

public class Context {
    private Optional<Patient> accessedPatient = Optional.empty();
    private Optional<Patient> deletedPatient = Optional.empty();
    private Optional<Patient> editedPatient = Optional.empty();
    private Ic editedPatientPrevIc;

    /**
     * Accesses the given patient.
     * The patient must exist in the medi book.
     */
    public void accessPatient(Patient patient) {
        this.accessedPatient = Optional.of(patient);
    }

    public Optional<Patient> getPatientToAccess() {
        return this.accessedPatient;
    }

    /**
     * Resets the accessed patient.
     */
    public void resetAccessedPatient() {
        this.accessedPatient = Optional.empty();
    }

    /**
     * Sets the {@code patient} that has just been deleted.
     */
    public void setDeletedPatient(Patient patient) {
        this.deletedPatient = Optional.of(patient);
    }

    /**
     * Returns the {@code patient} that has just been deleted.
     */
    public Optional<Patient> getDeletedPatient() {
        return this.deletedPatient;
    }

    /**
     * Resets the {@code patient} that has just been deleted.
     */
    public void resetDeletedPatient() {
        this.deletedPatient = Optional.empty();
    }

    /**
     * Sets the {@code patient} that has just been edited.
     */
    public void setEditedPatient(Patient patient, Ic prevIc) {
        this.editedPatient = Optional.of(patient);
        this.editedPatientPrevIc = prevIc;
    }

    /**
     * Returns the {@code patient} that has just been edited.
     */
    public Optional<Patient> getEditedPatient() {
        return this.editedPatient;
    }

    /**
     * Returns the previous Ic of an edited patient assuming the Ic was edited.
     */
    public Ic getEditedPatientPrevIc() {
        return this.editedPatientPrevIc;
    }

    /**
     * Resets the {@code patient} that has just been edited.
     */
    public void resetEditedPatient() {
        this.editedPatient = Optional.empty();
    }
}
