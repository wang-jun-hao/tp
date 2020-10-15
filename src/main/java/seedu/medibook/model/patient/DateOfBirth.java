package seedu.medibook.model.patient;

import static seedu.medibook.commons.util.AppUtil.checkArgument;

import seedu.medibook.model.Date;

/**
 * Represents a Patient's date of birth (DOB) in the medi book.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateOfBirth(String)}
 */
public class DateOfBirth {

    public static final String MESSAGE_CONSTRAINTS = "Date of birth (DOB) should be of the format \"DD-MM-YYYY\""
            + "where D, M and Y represent digits of the day, month and year of the DOB respectively."
            + "\nDOB should not be in the future.";
    public final String inputValue;
    public final String outputValue;
    private final Date date;


    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dobString A valid date of birth.
     */
    public DateOfBirth(String dobString) {
        checkArgument(isValidDateOfBirth(dobString), MESSAGE_CONSTRAINTS);
        date = new Date(dobString);
        inputValue = date.inputValue;
        outputValue = date.outputValue;
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String dobString) {
        if (!Date.isValidDate(dobString)) {
            return false;
        }
        Date date = new Date(dobString);
        return date.isOnOrBeforeToday();
    }

    @Override
    public String toString() {
        return inputValue;
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
