package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Person's date of birth (DOB) in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDob(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Date of birth (DOB) should be of the format \"DD-MM-YYYY\""
            + "where D, M and Y represent digits of the day, month and year of the DOB respectively."
            + "\nDOB should also have not been passed yet i.e. not in the future.";
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");
    public final LocalDate dateOfBirth;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dobString A valid date of birth.
     */
    public DateOfBirth(String dobString) {
        requireNonNull(dobString);
        checkArgument(isValidDob(dobString), MESSAGE_CONSTRAINTS);
        dateOfBirth = LocalDate.parse(dobString, INPUT_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDob(String test) {
        try {
            LocalDate testDate = LocalDate.parse(test, INPUT_FORMATTER);
            LocalDate todayDate = LocalDate.now();
            return testDate.isBefore(todayDate) || testDate.isEqual(todayDate);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return dateOfBirth.format(OUTPUT_FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DateOfBirth // instanceof handles nulls
                && dateOfBirth.equals(((DateOfBirth) other).dateOfBirth)); // state check
    }

    @Override
    public int hashCode() {
        return dateOfBirth.hashCode();
    }

}
