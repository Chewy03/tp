package seedu.noknock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_IC;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.noknock.logic.parser.CliSyntax.PREFIX_WARD;

import seedu.noknock.commons.util.ToStringBuilder;
import seedu.noknock.logic.Messages;
import seedu.noknock.logic.commands.exceptions.CommandException;
import seedu.noknock.model.Model;
import seedu.noknock.model.person.Patient;

/**
 * Adds a person to the address book.
 */
public class AddPatientCommand extends Command {

    public static final String COMMAND_WORD = "add-patient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_WARD + "WARD "
            + PREFIX_IC + "IC "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_WARD + "2A "
            + PREFIX_IC + "S1234567A "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This patient already exists in the address book";

    private final Patient toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    //Change constructor
    public AddPatientCommand(Patient patient) {
        requireNonNull(patient);
        toAdd = patient;
    }

    @Override
    //model should be able to identify runtime type of Patient or NOK
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPatientCommand)) {
            return false;
        }

        AddPatientCommand otherAddCommand = (AddPatientCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
