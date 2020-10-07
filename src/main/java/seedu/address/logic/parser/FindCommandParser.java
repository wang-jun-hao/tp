package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.*;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {
    // array of prefixes currently supported by the find command.
    private static final Prefix[] SEARCH_PREFIXES = new Prefix[]{ PREFIX_IC, PREFIX_NAME, PREFIX_DOB, PREFIX_PHONE };

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, SEARCH_PREFIXES);

        if (anyPrefixEmpty(argMultimap, SEARCH_PREFIXES)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        List<Predicate<Patient>> predicates =
                Arrays.stream(SEARCH_PREFIXES)
                .filter(prefix -> prefixNotEmpty(argMultimap, prefix))
                .map(prefix -> mapToPredicate(argMultimap, prefix))
                .collect(Collectors.toList());

        return new FindCommand(predicates);
    }

    /**
     * Returns true if any of the prefixes that were specified by the user contains an empty {@code String}
     * in the given * {@code ArgumentMultimap}.
     */
    private boolean anyPrefixEmpty(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> {
            Optional<String> keyword = argumentMultimap.getValue(prefix);

            boolean isPrefixSpecified = keyword.isPresent();
            if (!isPrefixSpecified) {
                return false;
            }

            boolean isKeywordEmptyString = keyword.get().isEmpty();
            return isKeywordEmptyString;
        });
    }

    private boolean prefixNotEmpty(ArgumentMultimap argMultimap, Prefix prefix) {
        String keyword = argMultimap.getValue(prefix).orElse("");
        return !keyword.isEmpty();
    }

    private FieldContainsKeywordsPredicate mapToPredicate(ArgumentMultimap argMultimap, Prefix prefix) {
        List<String> keywords = Arrays.asList(argMultimap.getValue(prefix).get().split("\\s+"));
        return new FieldContainsKeywordsPredicate(keywords, prefix);
    }

}
