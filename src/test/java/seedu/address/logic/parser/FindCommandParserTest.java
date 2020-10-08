package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.CommandTestUtil.NAME_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.address.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.patient.FieldContainsKeywordsPredicate;
import seedu.address.model.patient.Patient;

public class FindCommandParserTest {
    private static final String SINGLE_WHITESPACE = " ";

    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_allFieldsSpecified_success() {
        String[] nameKeywords = new String[]{ "Alice", "Bob" };
        String[] icKeywords = new String[]{ "T0232323I", "S9234567A" };
        String[] dobKeywords = new String[]{ "14-07-2002", "01-03-1992" };
        String[] phoneKeywords = new String[]{ "94351253", "95352563" };

        String nameArg = generateArgument(PREFIX_NAME, nameKeywords);
        String icArg = generateArgument(PREFIX_IC, icKeywords);
        String dobArg = generateArgument(PREFIX_DOB, dobKeywords);
        String phoneArg = generateArgument(PREFIX_PHONE, phoneKeywords);

        String[] nameKeywordsAlt = new String[]{ "Alice", "Bob" };
        String[] icKeywordsAlt = new String[]{ "T0232323I", "S9234567A" };
        String[] dobKeywordsAlt = new String[]{ "14-07-2002", "01-03-1992" };
        String[] phoneKeywordsAlt = new String[]{ "94351253", "95352563" };

        String nameArgAlt = generateArgument(PREFIX_NAME, nameKeywordsAlt);
        String icArgAlt = generateArgument(PREFIX_IC, icKeywordsAlt);
        String dobArgAlt = generateArgument(PREFIX_DOB, dobKeywordsAlt);
        String phoneArgAlt = generateArgument(PREFIX_PHONE, phoneKeywordsAlt);

        Predicate<Patient> namePred = new FieldContainsKeywordsPredicate(Arrays.asList(nameKeywords), PREFIX_NAME);
        Predicate<Patient> icPred = new FieldContainsKeywordsPredicate(Arrays.asList(icKeywords), PREFIX_IC);
        Predicate<Patient> dobPred = new FieldContainsKeywordsPredicate(Arrays.asList(dobKeywords), PREFIX_DOB);
        Predicate<Patient> phonePred = new FieldContainsKeywordsPredicate(Arrays.asList(phoneKeywords), PREFIX_PHONE);

        List<Predicate<Patient>> predicates = Arrays.asList(namePred, icPred, dobPred, phonePred);

        FindCommand expectedFindCommand = new FindCommand(predicates);

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + nameArg + icArg + dobArg + phoneArg,
                expectedFindCommand);

        // calling with arguments in different order returns the same FindCommand
        assertParseSuccess(parser, phoneArg + icArg + dobArg + nameArg,
                expectedFindCommand);

        // multiple whitespaces between, before and after keywords
        String nameArgWithWhitespaces = "  \t " + nameArg.replaceAll(" ", " \n \t ");
        String icArgWithWhitespaces = icArg.replaceAll(" ", " \n   \r \t ") + "  \t ";
        String dobArgWithWhitespaces = dobArg.replaceAll(" ", " \n \r   ");
        String phoneArgWithWhitespaces = phoneArg.replaceAll(" ", "  \r  \t  ");

        assertParseSuccess(parser, nameArgWithWhitespaces + icArgWithWhitespaces + dobArgWithWhitespaces
                + phoneArgWithWhitespaces, expectedFindCommand);

        // multiple names - last name accepted
        assertParseSuccess(parser, nameArgAlt + nameArg + icArg + dobArg + phoneArg,
                expectedFindCommand);

        // multiple ics - last ic accepted
        assertParseSuccess(parser, nameArg + icArgAlt + icArg + dobArg + phoneArg,
                expectedFindCommand);

        // multiple dates of birth - last date of birth accepted
        assertParseSuccess(parser, nameArg + icArg + dobArgAlt + dobArg + phoneArg,
                expectedFindCommand);

        // multiple phones - last phone accepted
        assertParseSuccess(parser, nameArg + icArg + dobArg + phoneArgAlt + phoneArg,
                expectedFindCommand);
    }

    @Test
    public void parse_fieldsMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE);

        // whitespace as argument
        assertParseFailure(parser, "       ", expectedMessage);

        // no prefixes provided
        assertParseFailure(parser, "Alice", expectedMessage);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + NAME_DESC_AMY, expectedMessage);
    }

    private String generateArgument(Prefix prefix, String... keywords) {
        return SINGLE_WHITESPACE + prefix + String.join(" ", keywords);
    }

}
