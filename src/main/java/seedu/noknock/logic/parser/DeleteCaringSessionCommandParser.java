package seedu.noknock.logic.parser;

import static seedu.noknock.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.logic.commands.DeleteCaringSessionCommand;
import seedu.noknock.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteCaringSessionCommand object.
 */
public class DeleteCaringSessionCommandParser implements Parser<DeleteCaringSessionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCaringSessionCommand
     * and returns a DeleteCaringSessionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    @Override
    public DeleteCaringSessionCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        String[] splitArgs = trimmedArgs.split("\\s+");
        if (splitArgs.length != 2) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCaringSessionCommand.MESSAGE_USAGE));
        }
        try {
            Index patientIndex = ParserUtil.parseIndex(splitArgs[0]);
            Index sessionIndex = ParserUtil.parseIndex(splitArgs[1]);
            return new DeleteCaringSessionCommand(patientIndex, sessionIndex);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCaringSessionCommand.MESSAGE_USAGE), pe);
        }
    }
}
