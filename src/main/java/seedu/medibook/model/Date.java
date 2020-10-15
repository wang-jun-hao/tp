package seedu.medibook.model;

import static java.util.Objects.requireNonNull;
import static seedu.medibook.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a date in MediBook.
 * Guarantees: immutable; is valid as declared in {@link #isValidDate(String)}
 */
public class Date {
    public static final String MESSAGE_CONSTRAINTS = "Date should be of the format \"DD-MM-YYYY\""
            + "where D, M and Y represent digits of the day, month and year of the DOB respectively.";
    public static final DateTimeFormatter OUTPUT_FORMATTER = DateTimeFormatter.ofPattern("d MMM yyyy");
    private static final DateTimeFormatter INPUT_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    public final String inputValue;
    public final String outputValue;
    public final LocalDate date;

    /**
     * Constructs a {@code Date}.
     *
     * @param dateString A valid date.
     */
    public Date(String dateString) {
        requireNonNull(dateString);
        checkArgument(isValidDate(dateString), MESSAGE_CONSTRAINTS);
        date = LocalDate.parse(dateString, INPUT_FORMATTER);
        inputValue = dateString;
        outputValue = date.format(OUTPUT_FORMATTER);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(String test) {
        try {
            LocalDate.parse(test, INPUT_FORMATTER);
            return true;
        } catch (DateTimeParseException | NullPointerException e) {
            return false;
        }
    }

    /**
     * Returns true if this date is in the past or today.
     */
    public boolean isOnOrBeforeToday() {
        LocalDate todayDate = LocalDate.now();
        return date.isBefore(todayDate) || date.isEqual(todayDate);
    }

    @Override
    public String toString() {
        return inputValue;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Date // instanceof handles nulls
                && date.equals(((Date) other).date)); // state check
    }

    @Override
    public int hashCode() {
        return date.hashCode();
    }

}
