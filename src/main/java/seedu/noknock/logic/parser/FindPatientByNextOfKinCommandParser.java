package seedu.noknock.logic.parser;

import static seedu.noknock.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.noknock.logic.commands.FindPatientByNextOfKinCommand;
import seedu.noknock.logic.parser.exceptions.ParseException;
import seedu.noknock.model.person.PatientNokContainsKeywordsPredicate;

/**
 * Parses input arguments and creates a new FindPatientByNextOfKinCommand object
 */
public class FindPatientByNextOfKinCommandParser implements Parser<FindPatientByNextOfKinCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindPatientByNextOfKinCommand
     * and returns a FindPatientByNextOfKinCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format
     */
    public FindPatientByNextOfKinCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindPatientByNextOfKinCommand.MESSAGE_USAGE));
        }

        String[] nameKeywords = trimmedArgs.split("\\s+");

        return new FindPatientByNextOfKinCommand(new PatientNokContainsKeywordsPredicate(Arrays.asList(nameKeywords)));
    }
}
