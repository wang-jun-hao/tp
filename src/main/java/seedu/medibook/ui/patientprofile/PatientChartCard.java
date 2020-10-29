package seedu.medibook.ui.patientprofile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.medibook.model.commonfields.Date;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Weight;
import seedu.medibook.ui.UiPart;

public class PatientChartCard extends UiPart<Region> {

    private static final String FXML = "PatientChartCard.fxml";

    public final Patient patient;


    @FXML
    private Label cardHeader;

    @FXML
    private LineChart<String, Number> weightChart;

    @FXML
    private LineChart<String, Number> heightChart;

    @FXML
    private LineChart<String, Number> bmiChart;

    /**
     * Creates a {@code PatientChartCard} for the given {@code Patient}.
     */
    public PatientChartCard(Patient patient) {
        super(FXML);
        this.patient = patient;
        cardHeader.setText("Chart: ");
        weightChart.setTitle("Weight Chart");
        heightChart.setTitle("Height Chart");
        bmiChart.setTitle("BMI Chart");

        XYChart.Series<String, Number> weightSeries = new XYChart.Series<>();
        weightSeries.setName("weight records");

        XYChart.Series<String, Number> heightSeries = new XYChart.Series<>();
        heightSeries.setName("height records");

        XYChart.Series<String, Number> bmiSeries = new XYChart.Series<>();
        bmiSeries.setName("BMI records");
        Map<String, Integer> stringHeightMap = new HashMap<>();
        Map<String, Double> stringWeightMap = new HashMap<>();

        //weight chart
        CategoryAxis xAxisWeight = (CategoryAxis) weightChart.getXAxis();
        NumberAxis yAxisWeight = (NumberAxis) weightChart.getYAxis();
        xAxisWeight.setLabel("Date");
        yAxisWeight.setForceZeroInRange(false);
        yAxisWeight.setLabel("Weight(kg)");

        HashMap<Date, Weight> weightRecord = patient.getRecord().getWeightRecord();
        List<Map.Entry<Date, Weight>> weightRecords = new ArrayList<>(weightRecord.entrySet());
        Weight[] weights = weightRecord.values().toArray(new Weight[0]);
        List<Date> dateListWeight = new ArrayList<Date>();
        for (int k = 0; k < weights.length; k++) {
            dateListWeight.add(weightRecords.get(k).getKey());
        }
        Collections.sort(dateListWeight);

        LocalDate startWeightDate = dateListWeight.get(0).getLocalDate();
        LocalDate endWeightDate = dateListWeight.get(dateListWeight.size() - 1).getLocalDate();
        ObservableList<String> allDatesWeight = FXCollections.observableArrayList();
        while (!startWeightDate.isAfter(endWeightDate)) {
            allDatesWeight.add(startWeightDate.toString());
            startWeightDate = startWeightDate.plusDays(1);
        }

        xAxisWeight.setCategories(allDatesWeight);

        for (int i = 0; i < weights.length; i++) {
            String date = dateListWeight.get(i).getLocalDate().toString();
            Double plot = weightRecord.get(dateListWeight.get(i)).getNumericValue();
            stringWeightMap.put(date, plot);
            weightSeries.getData().add(new XYChart.Data(date, plot));
        }



        //height chart
        CategoryAxis xAxisHeight = (CategoryAxis) heightChart.getXAxis();
        NumberAxis yAxisHeight = (NumberAxis) heightChart.getYAxis();
        xAxisHeight.setLabel("Date");
        yAxisHeight.setForceZeroInRange(false);
        yAxisHeight.setLabel("Height(cm)");


        HashMap<Date, Height> heightRecord = patient.getRecord().getHeightRecord();
        List<Map.Entry<Date, Height>> heightRecords = new ArrayList<>(heightRecord.entrySet());
        Height[] heights = heightRecord.values().toArray(new Height[0]);
        List<Date> dateListHeight = new ArrayList<Date>();
        for (int k = 0; k < heights.length; k++) {
            dateListHeight.add(weightRecords.get(k).getKey());
        }
        Collections.sort(dateListHeight);

        LocalDate startHeightDate = dateListHeight.get(0).getLocalDate();
        LocalDate endHeightDate = dateListHeight.get(dateListHeight.size() - 1).getLocalDate();
        ObservableList<String> allDatesHeight = FXCollections.observableArrayList();
        while (!startHeightDate.isAfter(endHeightDate)) {
            allDatesHeight.add(startHeightDate.toString());
            startHeightDate = startHeightDate.plusDays(1);
        }

        xAxisHeight.setCategories(allDatesHeight);

        for (int i = 0; i < heights.length; i++) {
            String date = dateListHeight.get(i).getLocalDate().toString();
            int plot = heightRecord.get(dateListHeight.get(i)).getNumericValue();
            stringHeightMap.put(date, plot);
            heightSeries.getData().add(new XYChart.Data(date, plot));
        }

        //bmi chart
        CategoryAxis xAxisBmi = (CategoryAxis) bmiChart.getXAxis();
        NumberAxis yAxisBmi = (NumberAxis) bmiChart.getYAxis();
        xAxisBmi.setLabel("Date");
        yAxisBmi.setForceZeroInRange(false);
        yAxisBmi.setLabel("BMI");


        LocalDate startBmiDate;
        LocalDate endBmiDate;
        if (dateListHeight.get(0).getLocalDate().isBefore(dateListWeight.get(0).getLocalDate())) {
            startBmiDate = dateListHeight.get(0).getLocalDate();
        } else {
            startBmiDate = dateListWeight.get(0).getLocalDate();
        }

        if (dateListHeight.get(dateListHeight.size() - 1).getLocalDate()
                .isBefore(dateListWeight.get(dateListWeight.size() - 1).getLocalDate())) {
            endBmiDate = dateListWeight.get(dateListWeight.size() - 1).getLocalDate();
        } else {
            endBmiDate = dateListHeight.get(dateListHeight.size() - 1).getLocalDate();
        }

        ObservableList<String> allDatesBmi = FXCollections.observableArrayList();
        while (!startBmiDate.isAfter(endBmiDate)) {
            allDatesBmi.add(startBmiDate.toString());
            startBmiDate = startBmiDate.plusDays(1);
        }

        xAxisBmi.setCategories(allDatesHeight);

        for (int i = 0; i < allDatesBmi.size(); i++) {
            String date = allDatesBmi.get(i);
            if (stringHeightMap.get(date) != null && stringWeightMap.get(date) != null) {
                double height = (double) stringHeightMap.get(date) / 100;
                double weight = stringWeightMap.get(date);
                double numericBmi = weight / Math.pow(height, 2);
                double bmi = ((double) Math.round(numericBmi * 10)) / 10;
                bmiSeries.getData().add(new XYChart.Data(date, bmi));
            }
        }

        weightChart.getData().add(weightSeries);
        heightChart.getData().add(heightSeries);
        bmiChart.getData().add(bmiSeries);

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
        PatientChartCard card = (PatientChartCard) other;
        return patient.equals(card.patient);
    }

}
