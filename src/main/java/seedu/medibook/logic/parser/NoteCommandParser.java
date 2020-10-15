package seedu.medibook.logic.parser;

import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_BLOOD_TYPE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_CONTENT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_HEIGHT;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.medibook.logic.parser.CliSyntax.PREFIX_WEIGHT;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import seedu.medibook.logic.commands.AddCommand;
import seedu.medibook.logic.commands.NoteCommand;
import seedu.medibook.logic.parser.exceptions.ParseException;
import seedu.medibook.model.medicalnote.MedicalNote;
import seedu.medibook.model.patient.Address;
import seedu.medibook.model.patient.BloodType;
import seedu.medibook.model.patient.DateOfBirth;
import seedu.medibook.model.patient.Email;
import seedu.medibook.model.patient.Height;
import seedu.medibook.model.patient.Ic;
import seedu.medibook.model.patient.Name;
import seedu.medibook.model.patient.Patient;
import seedu.medibook.model.patient.Phone;
import seedu.medibook.model.patient.Weight;
import seedu.medibook.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class NoteCommandParser implements Parser<NoteCommand> {
    private final Patient displayedPatient;

    public NoteCommandParser(Patient displayedPatient) {
        this.displayedPatient = displayedPatient;
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public NoteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_DATE, PREFIX_NAME, PREFIX_CONTENT);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_CONTENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, NoteCommand.MESSAGE_USAGE));
        }

        String date = argMultimap.getValue(PREFIX_DATE).orElse(getTodayDate());
        String name = argMultimap.getValue(PREFIX_NAME).get();
        String content = argMultimap.getValue(PREFIX_CONTENT).get();

        MedicalNote medicalNote = new MedicalNote(date, name, content);

        return new NoteCommand(displayedPatient, medicalNote);
    }

    private String getTodayDate() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        return formatter.format(date);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
