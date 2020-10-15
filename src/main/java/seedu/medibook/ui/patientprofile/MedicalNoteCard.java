package seedu.medibook.ui.patientprofile;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.ui.UiPart;

/**
 * An UI component that displays a {@code MedicalNote}.
 */
public class MedicalNoteCard extends UiPart<Region> {

    private static final String FXML = "MedicalNoteCard.fxml";

    public final MedicalNote medicalNote;

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label doctorName;
    @FXML
    private TextArea content;

    /**
     * Creates a {@code MedicalNoteCard} with the given {@code MedicalNote} to be displayed.
     */
    public MedicalNoteCard(MedicalNote medicalNote) {
        super(FXML);
        this.medicalNote = medicalNote;
        // TODO: change accordingly based on visibility
        date.setText(medicalNote.date.toString()); // TODO: Make the formatter public or make a get date method
        doctorName.setText(medicalNote.doctorName);
        content.setText(medicalNote.content);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof MedicalNoteCard)) {
            return false;
        }

        // state check
        MedicalNoteCard card = (MedicalNoteCard) other;
        return medicalNote.equals(card.medicalNote);
    }
}
