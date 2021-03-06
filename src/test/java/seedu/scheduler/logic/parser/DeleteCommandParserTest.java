package seedu.scheduler.logic.parser;

import static seedu.scheduler.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.scheduler.logic.parser.CliSyntax.PREFIX_ROLE;
import static seedu.scheduler.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.scheduler.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.scheduler.testutil.TypicalPersons.ALICE_INTERVIEWEE;
import static seedu.scheduler.testutil.TypicalPersons.ALICE_INTERVIEWER;

import org.junit.jupiter.api.Test;

import seedu.scheduler.logic.commands.DeleteCommand;
import seedu.scheduler.logic.commands.DeleteIntervieweeCommand;
import seedu.scheduler.logic.commands.DeleteInterviewerCommand;

/**
 * As we are only doing white-box testing, our test cases do not cover path variations
 * outside of the DeleteCommand code. For example, inputs "1" and "1 abc" take the
 * same path through the DeleteCommand, and therefore we test only one of them.
 * The path variation for those two cases occur inside the ParserUtil, and
 * therefore should be covered by the ParserUtilTest.
 */
public class DeleteCommandParserTest {

    private DeleteCommandParser parser = new DeleteCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, ALICE_INTERVIEWEE.getName() + " " + PREFIX_ROLE + "interviewee",
                new DeleteIntervieweeCommand(ALICE_INTERVIEWEE.getName()));

        assertParseSuccess(parser, ALICE_INTERVIEWER.getName() + " " + PREFIX_ROLE + "interviewer",
                new DeleteInterviewerCommand(ALICE_INTERVIEWER.getName()));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
    }
}
