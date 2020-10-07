package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.patient.DateOfBirth;
import seedu.address.model.patient.Height;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Weight;

/**
 * An UI component that displays information of a {@code Patient}.
 */
public class PatientCard extends UiPart<Region> {

    private static final String FXML = "PatientListCard.fxml";
    private static final String OPTIONAL_FIELD_EMPTY_MESSAGE = "N/A";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Patient patient;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label ic;
    @FXML
    private Label id;
    @FXML
    private Label dateOfBirth;
    @FXML
    private Label phone;
    @FXML
    private Label address;
    @FXML
    private Label email;
    @FXML
    private Label height;
    @FXML
    private Label weight;
    @FXML
    private Label bmi;
    @FXML
    private Label bloodType;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PatientCode} with the given {@code Patient} and index to display.
     */
    public PatientCard(Patient patient, int displayedIndex) {
        super(FXML);
        this.patient = patient;
        id.setText(displayedIndex + ". ");
        ic.setText(patient.getIc().ic);
        name.setText(patient.getName().fullName);
        dateOfBirth.setText(patient.getDateOfBirth().date.format(DateOfBirth.OUTPUT_FORMATTER));
        phone.setText(patient.getPhone().value);

        // email
        if (patient.getEmail().isPresent()) {
            email.setText(patient.getEmail().get().value);
        } else {
            email.setText(OPTIONAL_FIELD_EMPTY_MESSAGE);
        }

        // address
        if (patient.getAddress().isPresent()) {
            address.setText(patient.getAddress().get().value);
        } else {
            address.setText(OPTIONAL_FIELD_EMPTY_MESSAGE);
        }

        // height
        if (patient.getHeight().isPresent()) {
            height.setText(patient.getHeight().get().value + Height.HEIGHT_UNIT);
        } else {
            height.setText(OPTIONAL_FIELD_EMPTY_MESSAGE);
        }

        // weight
        if (patient.getWeight().isPresent()) {
            weight.setText(patient.getWeight().get().value + Weight.WEIGHT_UNIT);
        } else {
            weight.setText(OPTIONAL_FIELD_EMPTY_MESSAGE);
        }

        // bmi
        if (patient.getBmi().isPresent()) {
            bmi.setText(patient.getBmi().get().value);
        } else {
            bmi.setText(OPTIONAL_FIELD_EMPTY_MESSAGE);
        }

        // bloodType
        if (patient.getBloodType().isPresent()) {
            bloodType.setText(patient.getBloodType().get().bloodType.label);
        } else {
            bloodType.setText(OPTIONAL_FIELD_EMPTY_MESSAGE);
        }

        patient.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof PatientCard)) {
            return false;
        }

        // state check
        PatientCard card = (PatientCard) other;
        return id.getText().equals(card.id.getText())
                && patient.equals(card.patient);
    }
}
