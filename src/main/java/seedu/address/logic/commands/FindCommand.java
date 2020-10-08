package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.patient.Patient;

/**
 * Finds and lists all patients in address book whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all patients whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice bob charlie";

    private final List<Predicate<Patient>> predicates;

    public FindCommand(Predicate<Patient> predicate) {
        this.predicates = Collections.singletonList(predicate);
    }

    public FindCommand(List<Predicate<Patient>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Patient> combinedPredicates = predicates.stream().reduce(x -> true, Predicate::and);
        model.updateFilteredPatientList(combinedPredicates);

        return new CommandResult(
                String.format(Messages.MESSAGE_PATIENT_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && arePredicatesEqual(predicates, ((FindCommand) other).predicates)); // state check
    }

    /**
     * Checks if the two lists contains the same predicates regardless of order.
     */
    private boolean arePredicatesEqual(List<Predicate<Patient>> predicates1, List<Predicate<Patient>> predicates2) {
        if (predicates1.size() != predicates2.size()) {
            return false;
        }
        HashSet<Predicate<Patient>> predicatesSet = new HashSet<>(predicates1);
        predicatesSet.removeAll(predicates2);
        return predicatesSet.isEmpty();
    }
}
