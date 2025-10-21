package seedu.noknock.logic.parser;

import static seedu.noknock.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;

import java.util.stream.Stream;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.logic.commands.AddNextOfKinCommand;
import seedu.noknock.logic.parser.exceptions.ParseException;
import seedu.noknock.model.person.Name;
import seedu.noknock.model.person.NextOfKin;
import seedu.noknock.model.person.Phone;
import seedu.noknock.model.person.Relationship;

/**
 * Parses input arguments and creates a new AddNextOfKinCommand object
 */
public class AddNextOfKinCommandParser implements Parser<AddNextOfKinCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the AddNextOfKinCommand
     * and returns an AddNextOfKinCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddNextOfKinCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_RELATIONSHIP);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddNextOfKinCommand.MESSAGE_USAGE), pe);
        }

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_PHONE, PREFIX_RELATIONSHIP)) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AddNextOfKinCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_PHONE, PREFIX_RELATIONSHIP);

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Relationship relationship = ParserUtil.parseRelationship(argMultimap.getValue(PREFIX_RELATIONSHIP).get());

        NextOfKin nextOfKin = new NextOfKin(name, phone, relationship);

        return new AddNextOfKinCommand(index, nextOfKin);
    }
}
