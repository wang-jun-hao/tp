package seedu.medibook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.medicalnote.MedicalNote;

/**
 * Jackson-friendly version of {@link MedicalNote}.
 */
class JsonAdaptedMedicalNote {

    public static final String MESSAGE_FORMAT_MISSING_FIELD = "MedicalNote's %s field is missing!";
    public static final String MESSAGE_INVALID_DATE_FIELD = "MedicalNote's Date field is invalid!";
    public static final String FIELD_CONTENT = "Content";
    public static final String FIELD_DATE = "Date";
    public static final String FIELD_DOCTOR_NAME = "Docter Name";

    public final String date;
    @JsonProperty("doctor name")
    public final String doctorName;
    public final String content;

    /**
     * Constructs a {@code JsonAdaptedMedicalNote} with the given patient details.
     */
    @JsonCreator
    public JsonAdaptedMedicalNote(@JsonProperty("date") String date, @JsonProperty("doctor name") String doctorName,
                                  @JsonProperty("content") String content) {
        this.date = date;
        this.doctorName = doctorName;
        this.content = content;
    }

    /**
     * Converts a given {@code MedicalNote} into this class for Jackson use.
     */
    public JsonAdaptedMedicalNote(MedicalNote medicalNote) {
        this.date = medicalNote.getDateString();
        this.doctorName = medicalNote.getDoctorName();
        this.content = medicalNote.getContent();
    }

    /**
     * Converts this Jackson-friendly adapted patient object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted patient.
     */
    public MedicalNote toModelType() throws IllegalValueException {
        if (date == null) {
            throw new IllegalValueException(String.format(MESSAGE_FORMAT_MISSING_FIELD, FIELD_DATE));
        }

        if (!MedicalNote.isValidDate(date)) {
            throw new IllegalValueException(MESSAGE_INVALID_DATE_FIELD);
        }

        final String modelDate = date;

        if (doctorName == null) {
            throw new IllegalValueException(String.format(MESSAGE_FORMAT_MISSING_FIELD, FIELD_DOCTOR_NAME));
        }

        final String modelDoctorName = doctorName;

        if (content == null) {
            throw new IllegalValueException(String.format(MESSAGE_FORMAT_MISSING_FIELD, FIELD_CONTENT));
        }

        final String modelContent = content;

        return new MedicalNote(modelDate, modelDoctorName, modelContent);
    }

}
