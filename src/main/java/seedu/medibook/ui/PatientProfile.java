package seedu.medibook.ui;

import javafx.scene.layout.Region;
import seedu.medibook.model.patient.Patient;

public class PatientProfile extends UiPart<Region> {

    private static final String FXML = "PatientProfile.fxml";

    public final Patient patient;

    /**
     * Creates a {@code PatientProfile} with the given {@code Patient}.
     */
    public PatientProfile(Patient patient) {
        super(FXML);
        this.patient = patient;
    }
}
