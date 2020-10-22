package seedu.medibook.logic.parser;


import static seedu.medibook.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.medibook.logic.commands.CommandTestUtil.INVALID_NOTE_DATE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_CONTENT_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_DOCTOR_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_FUTURE_DATE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.NOTE_NON_FUTURE_DATE_DESC;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_CONTENT;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_DOCTOR_NAME;
import static seedu.medibook.logic.commands.CommandTestUtil.VALID_NON_FUTURE_DATE;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.medibook.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.medibook.logic.commands.AddNoteCommand;
import seedu.medibook.model.Date;
import seedu.medibook.model.medicalnote.MedicalNote;

class AddNoteCommandParserTest {
    private final AddNoteCommandParser parser = new AddNoteCommandParser();

    @Test
    public void parse_optionalNonFutureDateSpecified_success() {
        assertParseSuccess(parser, NOTE_NON_FUTURE_DATE_DESC + NOTE_DOCTOR_DESC + NOTE_CONTENT_DESC,
                new AddNoteCommand(new MedicalNote(new Date(VALID_NON_FUTURE_DATE, true),
                        VALID_DOCTOR_NAME, VALID_CONTENT)));
    }

    @Test
    public void parse_optionalFutureDateSpecified_failure() {
        assertParseFailure(parser, NOTE_FUTURE_DATE_DESC + NOTE_DOCTOR_DESC + NOTE_CONTENT_DESC,
                Date.MESSAGE_NON_FUTURE);
    }

    @Test
    public void parse_compulsoryFieldsMissing_failure() {
        // missing doctor name field
        assertParseFailure(parser, NOTE_NON_FUTURE_DATE_DESC + NOTE_CONTENT_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));

        // missing content field
        assertParseFailure(parser, NOTE_DOCTOR_DESC,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));

        // missing doctor name and content fields
        assertParseFailure(parser, " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddNoteCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_optionalDateMissing_success() {
        assertParseSuccess(parser, NOTE_DOCTOR_DESC + NOTE_CONTENT_DESC,
                new AddNoteCommand(new MedicalNote(new Date(), VALID_DOCTOR_NAME, VALID_CONTENT)));
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid date
        assertParseFailure(parser, INVALID_NOTE_DATE_DESC + NOTE_DOCTOR_DESC + NOTE_CONTENT_DESC,
                Date.MESSAGE_CONSTRAINTS);
    }
}
