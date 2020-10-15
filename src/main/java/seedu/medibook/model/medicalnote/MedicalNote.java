package seedu.medibook.model.medicalnote;

import static seedu.medibook.commons.util.AppUtil.checkArgument;
import static seedu.medibook.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Represents a medical note entry for a Patient in MediBook, on a particular date by a particular doctor.
 * Guarantees: details are present and not null, date and doctor's names are validated, fields are immutable.
 */
public class MedicalNote {
    public static final String DATE_MESSAGE_CONSTRAINTS = "Date should be of the format \"DD-MM-YYYY\""
            + "where D, M and Y represent digits of the day, month and year of the date respectively.";
    public static final String NAME_MESSAGE_CONSTRAINTS =
            "Names should only contain alphanumeric characters and spaces, and it should not be blank";
    public static final String CONTENT_MESSAGE_CONSTRAINTS = "Content should not be blank";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final String NAME_REGEX = "[\\p{Alnum}][\\p{Alnum} ]*";
    /** Date of medical note entry. */
    public final LocalDate date;
    /** Name of doctor entering the note. */
    public final String doctorName;
    /** Content of the note i.e. relevant information from patient's consultation. */
    public final String content;

    /**
     * Constructs a {@code MedicalNote}.
     *
     * @param dateString String representation of a valid date of entry.
     * @param doctorName A valid doctor's name.
     * @param content Contents of the note.
     */
    public MedicalNote(String dateString, String doctorName, String content) {
        requireAllNonNull(dateString, doctorName, content);
        checkArgument(isValidDate(dateString), DATE_MESSAGE_CONSTRAINTS); // input validation for date
        checkArgument(isValidDoctorName(doctorName), NAME_MESSAGE_CONSTRAINTS); // input validation for doctor's name
        checkArgument(isValidContent(content), CONTENT_MESSAGE_CONSTRAINTS); // input validation for content
        this.date = LocalDate.parse(dateString, DATE_FORMATTER);
        this.doctorName = doctorName;
        this.content = content;
    }

    public String getDateString() {
        return date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public String getDoctorName() {
        return doctorName;
    }

    public String getContent() {
        return content;
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate testDate = LocalDate.parse(test, DATE_FORMATTER);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
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
        return "Date: " + date.format(DATE_FORMATTER)
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

