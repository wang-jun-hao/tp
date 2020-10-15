package seedu.medibook.ui;

import java.util.logging.Logger;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.medibook.commons.core.LogsCenter;
import seedu.medibook.model.patient.Patient;

/**
 * Panel containing the list of patients.
 */
public class PatientListPanel extends UiPart<Region> {
    private static final String FXML = "PatientListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PatientListPanel.class);

    @FXML
    private TableView patientTableView;

    /**
     * Creates a {@code PatientListPanel} with the given {@code ObservableList}.
     */
    public PatientListPanel(ObservableList<Patient> patientList) {
        super(FXML);
        TableColumn<Patient, Number> columnIndex = new TableColumn<Patient, Number>("#");
        columnIndex.setCellValueFactory(column-> new ReadOnlyObjectWrapper<Number>(patientTableView
                .getItems().indexOf(column.getValue()) + 1));

        TableColumn<String, Patient> columnIc = new TableColumn<>("IC");
        columnIc.setCellValueFactory(new PropertyValueFactory<>("stringIC"));

        TableColumn<String, Patient> columnName = new TableColumn<>("Name");
        columnName.setCellValueFactory(new PropertyValueFactory<>("stringName"));

        TableColumn<String, Patient> columnDOB = new TableColumn<>("DOB");
        columnDOB.setCellValueFactory(new PropertyValueFactory<>("stringDOB"));

        TableColumn<String, Patient> columnPhone = new TableColumn<>("Phone");
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("stringPhone"));

        TableColumn<String, Patient> columnEmail = new TableColumn<>("Email");
        columnEmail.setCellValueFactory(new PropertyValueFactory<>("stringEmail"));

        TableColumn<String, Patient> columnAddress = new TableColumn<>("Address");
        columnAddress.setCellValueFactory(new PropertyValueFactory<>("stringAddress"));

        TableColumn<String, Patient> columnHeight = new TableColumn<>("Height");
        columnHeight.setCellValueFactory(new PropertyValueFactory<>("stringHeight"));

        TableColumn<String, Patient> columnWeight = new TableColumn<>("Weight");
        columnWeight.setCellValueFactory(new PropertyValueFactory<>("stringWeight"));

        TableColumn<String, Patient> columnBmi = new TableColumn<>("BMI");
        columnBmi.setCellValueFactory(new PropertyValueFactory<>("stringBmi"));

        TableColumn<String, Patient> columnBloodType = new TableColumn<>("Blood Type");
        columnBloodType.setCellValueFactory(new PropertyValueFactory<>("stringBloodType"));


        patientTableView.getColumns().add(columnIndex);
        patientTableView.getColumns().add(columnIc);
        patientTableView.getColumns().add(columnName);
        patientTableView.getColumns().add(columnDOB);
        patientTableView.getColumns().add(columnPhone);
        patientTableView.getColumns().add(columnEmail);
        patientTableView.getColumns().add(columnAddress);
        patientTableView.getColumns().add(columnHeight);
        patientTableView.getColumns().add(columnWeight);
        patientTableView.getColumns().add(columnBmi);
        patientTableView.getColumns().add(columnBloodType);

        columnIndex.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.04));
        columnIc.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.075));
        columnName.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.125));
        columnDOB.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.07));
        columnPhone.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.07));
        columnEmail.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.15));
        columnAddress.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.22));
        columnHeight.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.0625));
        columnWeight.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.0625));
        columnBmi.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.04));
        columnBloodType.prefWidthProperty().bind(patientTableView.widthProperty().multiply(0.075));

        columnIndex.setResizable(false);
        columnIc.setResizable(false);
        columnName.setResizable(false);
        columnDOB.setResizable(false);
        columnPhone.setResizable(false);
        columnEmail.setResizable(false);
        columnAddress.setResizable(false);
        columnHeight.setResizable(false);
        columnWeight.setResizable(false);
        columnBmi.setResizable(false);
        columnBloodType.setResizable(false);

        columnIndex.setSortable(false);
        columnIc.setSortable(false);
        columnName.setSortable(false);
        columnDOB.setSortable(false);
        columnPhone.setSortable(false);
        columnEmail.setSortable(false);
        columnAddress.setSortable(false);
        columnHeight.setSortable(false);
        columnWeight.setSortable(false);
        columnBmi.setSortable(false);
        columnBloodType.setSortable(false);

        patientTableView.getItems().addAll(patientList);
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Patient} using a {@code PatientCard}.
     */
    class PatientListViewCell extends ListCell<Patient> {
        @Override
        protected void updateItem(Patient patient, boolean empty) {
            super.updateItem(patient, empty);

            if (empty || patient == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PatientCard(patient, getIndex() + 1).getRoot());
            }
        }
    }

}
