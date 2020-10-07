package seedu.address.model.patient;

import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.Prefix;

import java.util.List;
import java.util.function.Predicate;

import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

/**
 * Tests that a {@code Patient}'s field as specified by the prefix matches the keyword given.
 */
public class FieldContainsKeywordsPredicate implements Predicate<Patient> {
    private final List<String> keywords;
    private final Prefix prefix;

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
        if (PREFIX_IC.equals(prefix)) {
            return patient.getIc().ic;
        } else if (PREFIX_NAME.equals(prefix)) {
            return patient.getName().fullName;
        } else if (PREFIX_DOB.equals(prefix)) {
            return patient.getDateOfBirth().value;
        } else if (PREFIX_PHONE.equals(prefix)) {
            return patient.getPhone().value;
        }
        return null;
    }

}
