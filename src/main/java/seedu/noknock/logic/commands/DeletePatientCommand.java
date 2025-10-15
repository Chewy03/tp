package seedu.noknock.logic.commands;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.commons.util.ToStringBuilder;
import seedu.noknock.logic.Messages;
import seedu.noknock.logic.commands.exceptions.CommandException;
import seedu.noknock.model.Model;
import seedu.noknock.model.person.Patient;

import java.util.List;

import static java.util.Objects.requireNonNull;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePatientCommand extends Command {

    public static final String COMMAND_WORD = "delete-patient";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Deleted Patient: %1$s";
    public static final String MESSAGE_INVALID_PATIENT_INDEX = "Invalid patient index. Please use a number from the patient list.";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient not found at index %d";

    private final Index targetIndex;

    public DeletePatientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        int zeroBasedIndex = targetIndex.getZeroBased();
        if (zeroBasedIndex >= lastShownList.size() || zeroBasedIndex < 0) {
            throw new CommandException(MESSAGE_INVALID_PATIENT_INDEX);
        }

        Patient patientToDelete = lastShownList.get(zeroBasedIndex);
        if (patientToDelete == null) {
            throw new CommandException(String.format(MESSAGE_PATIENT_NOT_FOUND, targetIndex.getOneBased()));
        }

        model.deletePerson(patientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, Messages.format(patientToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePatientCommand)) {
            return false;
        }

        DeletePatientCommand otherDeleteCommand = (DeletePatientCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}
