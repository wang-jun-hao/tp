package seedu.medibook.ui.patientprofile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.ui.UiPart;

/**
 * An UI component that displays the personal details of a {@code Patient}.
 */
public class PersonalDetailsCard extends UiPart<Region> {

    private static final String FXML = "ProfileDetailsCard.fxml";

    public final Patient patient;

    @FXML
    private Label cardHeader;
    @FXML
    private HBox cardPane;

    /**
     * Creates a {@code PersonalDetailsCard} for the given {@code Patient}.
     */
    public PersonalDetailsCard(Patient patient) {
        super(FXML);
        this.patient = patient;

        cardHeader.setText("Personal Details: ");
    }
}
