package seedu.medibook.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.medibook.commons.exceptions.IllegalValueException;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.DateOfBirth;

/**
 * Jackson-friendly version of {@link MedicalNote}.
 */
class JsonAdaptedMedicalNote {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "MedicalNote's %s field is missing!";

    public final String date;
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
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Date"));
        }

        final String modelDate = date;

        if (doctorName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "Doctor Name"));
        }

        final String modelDoctorName = doctorName;

        if (content == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DateOfBirth.class.getSimpleName()));
        }

        final String modelContent = content;

        return new MedicalNote(modelDate, modelDoctorName, modelContent);
    }

}
