package seedu.medibook.ui.patientprofile;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.ui.UiPart;

/**
 * An UI component representing a patient's profile that displays all the information of a {@code Patient}.
 */
public class PatientProfile extends UiPart<Region> {

    private static final String FXML = "PatientProfile.fxml";

    public final Patient patient;

    @FXML
    private AnchorPane personalDetailsCard;
    @FXML
    private AnchorPane medicalDetailsCard;
    @FXML
    private AnchorPane medicalNotesPanel;
    @FXML
    private AnchorPane patientChartCard;

    /**
     * Creates a {@code PatientProfile} for the given {@code Patient}.
     */
    public PatientProfile(Patient patient) {
        super(FXML);
        this.patient = patient;

        PersonalDetailsCard personalDetailsCard = new PersonalDetailsCard(patient);
        this.personalDetailsCard.getChildren().add(personalDetailsCard.getRoot());

        MedicalDetailsCard medicalDetailsCard = new MedicalDetailsCard(patient);
        this.medicalDetailsCard.getChildren().add(medicalDetailsCard.getRoot());

        MedicalNotesPanel medicalNotesPanel = new MedicalNotesPanel(patient.getObservableMedicalNoteList());
        this.medicalNotesPanel.getChildren().add(medicalNotesPanel.getRoot());

        PatientChartCard patientChartCard = new PatientChartCard(patient);
        this.patientChartCard.getChildren().add(patientChartCard.getRoot());
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientProfile)) {
            return false;
        }

        // state check
        PatientProfile profile = (PatientProfile) other;
        return patient.equals(profile.patient);
    }
}
