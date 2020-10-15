package seedu.medibook.ui.patientprofile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.ui.UiPart;

/**
 * An UI component that displays the medical details of a {@code Patient}.
 */
public class MedicalDetailsCard extends UiPart<Region> {

    private static final String FXML = "ProfileDetailsCard.fxml";

    public final Patient patient;

    @FXML
    private Label cardHeader;
    @FXML
    private HBox cardPane; // TODO: table view for details

    /**
     * Creates a {@code MedicalDetailsCard} for the given {@code Patient}.
     */
    public MedicalDetailsCard(Patient patient) {
        super(FXML);
        this.patient = patient;

        cardHeader.setText("Medical Details: ");
    }
}
