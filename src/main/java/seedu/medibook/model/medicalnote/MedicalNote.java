package seedu.medibook.model.medicalnote;

import static seedu.medibook.commons.util.AppUtil.checkArgument;
import static seedu.medibook.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;

import seedu.medibook.model.Date;

/**
 * Represents a medical note entry for a Patient in MediBook, on a particular date by a particular doctor.
 * Guarantees: details are present and not null, date and doctor's names are validated, fields are immutable.
 */
public class MedicalNote {
    public static final String NAME_MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String CONTENT_MESSAGE_CONSTRAINTS = "Content should not be blank";
    private static final String NAME_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    /** Date of medical note entry. */
    public final Date date;
    /** Name of doctor entering the note. */
    public final String doctorName;
    /** Content of the note i.e. relevant information from patient's consultation. */
    public final String content;

    /**
     * Constructs a {@code MedicalNote}.
     *
     * @param date String representation of a valid date of entry.
     * @param doctorName A valid doctor's name.
     * @param content Contents of the note.
     */
    public MedicalNote(Date date, String doctorName, String content) {
        requireAllNonNull(date, doctorName, content);
        checkArgument(isValidDoctorName(doctorName), NAME_MESSAGE_CONSTRAINTS); // input validation for doctor's name
        checkArgument(isValidContent(content), CONTENT_MESSAGE_CONSTRAINTS); // input validation for content
        this.date = date;
        this.doctorName = doctorName;
        this.content = content;
    }

    public String getInputDateString() {
        return date.inputValue;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getContent() {
        return content;
    }

    /**
     * Returns true if a given string is a valid doctor's name.
     */
    public static boolean isValidDoctorName(String test) {
        return test.matches(NAME_REGEX);
    }

    /**
     * Returns true if a given string is a valid content for the note.
     */
    public static boolean isValidContent(String test) {
        return test.length() > 0;
    }

    @Override
    public String toString() {
        return "Date: " + date.outputValue
                + "\nDoctor: " + doctorName
                + "\n\nNotes: " + content;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof MedicalNote)) {
            return false;
        }

        MedicalNote otherNote = (MedicalNote) other;
        return otherNote.date.equals(date)
                && otherNote.doctorName.equals(doctorName)
                && otherNote.content.equals(content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, doctorName, content);
    }
}

