package seedu.address.model.patient;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;

/**
 * Tests that a {@code Patient}'s field as specified by the prefix matches with the keyword given.
 */
public class FieldContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;
    private final Prefix prefix;

    /**
     * Creates a predicate for a patient field based on the given prefix and keywords.
     */
    public FieldContainsKeywordsPredicate(List<String> keywords, Prefix prefix) {
        this.keywords = keywords;
        this.prefix = prefix;
    }

    @Override
    public boolean test(Patient patient) {
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(getField(patient), keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FieldContainsKeywordsPredicate // instanceof handles nulls
                && keywords.equals(((FieldContainsKeywordsPredicate) other).keywords)) // keywords check
                && prefix.equals(((FieldContainsKeywordsPredicate) other).prefix); // prefix check
    }

    private String getField(Patient patient) {
        // switch statement cannot be used since Prefix is not an enum
        if (PREFIX_IC.equals(prefix)) {
            return patient.getIc().ic;
        } else if (PREFIX_NAME.equals(prefix)) {
            return patient.getName().fullName;
        } else if (PREFIX_DOB.equals(prefix)) {
            return patient.getDateOfBirth().value;
        } else if (PREFIX_PHONE.equals(prefix)) {
            return patient.getPhone().value;
        } else if (PREFIX_EMAIL.equals(prefix)) {
            return patient.getEmail().value;
        } else if (PREFIX_HEIGHT.equals(prefix)) {
            return patient.getHeight().value;
        } else if (PREFIX_WEIGHT.equals(prefix)) {
            return patient.getWeight().value;
        } else if (PREFIX_ADDRESS.equals(prefix)) {
            return patient.getAddress().value;
        } else if (PREFIX_BLOOD_TYPE.equals(prefix)) {
            return patient.getBloodType().bloodType.label;
        }
        return null;
    }

}
