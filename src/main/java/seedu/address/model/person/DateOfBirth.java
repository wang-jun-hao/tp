package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's date of birth (DOB) in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateOfBirth(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Date of birth (DOB) should be of the format \"DD-MM-YYYY\""
            + "where D, M and Y represent digits of the day, month and year of the DOB respectively."
            + "\nDOB should not be in the future.";
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final String value;
    public final LocalDate date;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dobString A valid date of birth.
     */
    public DateOfBirth(String dobString) {
        requireNonNull(dobString);
        checkArgument(isValidDateOfBirth(dobString), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(dobString, INPUT_FORMATTER);
        value = dobString;
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String test) {
        try {
            LocalDate testDate = LocalDate.parse(test, INPUT_FORMATTER);
            LocalDate todayDate = LocalDate.now();
            return testDate.isBefore(todayDate) || testDate.isEqual(todayDate);
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && date.equals(((DateOfBirth) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
