package seedu.medibook.ui.patientprofile;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import javafx.util.Pair;
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
    private ListView<Pair<String, Set>> detailsListView;

    /**
     * Creates a {@code MedicalDetailsCard} for the given {@code Patient}.
     */
    public MedicalDetailsCard(Patient patient) {
        super(FXML);
        this.patient = patient;

        cardHeader.setText("Medical Details: ");
        ObservableList<Pair<String, Set>> fields = FXCollections.observableArrayList();
        fields.add(new Pair<String, Set>("Tags", patient.getTags()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalDetailsCard)) {
            return false;
        }

        // state check
        MedicalDetailsCard card = (MedicalDetailsCard) other;
        return patient.equals(card.patient);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a medical detail field using a {@code MedicalDetailsRow}.
     */
    class MedicalDetailsListViewCell extends ListCell<Pair<String, Set>> {
        @Override
        protected void updateItem(Pair<String, Set> categoryTagsPair, boolean empty) {
            super.updateItem(categoryTagsPair, empty);

            if (empty || categoryTagsPair == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new MedicalDetailsRow(categoryTagsPair.getKey(), categoryTagsPair.getValue()).getRoot());
            }
        }
    }
}
