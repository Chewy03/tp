package seedu.noknock.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.noknock.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.ArrayList;
import java.util.List;

import seedu.noknock.commons.core.index.Index;
import seedu.noknock.logic.Messages;
import seedu.noknock.logic.commands.exceptions.CommandException;
import seedu.noknock.model.Model;
import seedu.noknock.model.person.Patient;
import seedu.noknock.model.session.CaringSession;

/**
 * Adds a caring session to an existing patient.
 */
public class AddCaringSessionCommand extends Command {

    public static final String COMMAND_WORD = "add-session";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Adds a caring session to the patient identified by the patient index.\n"
        + "Parameters: PATIENT_INDEX (must be a positive integer) "
        + "d/DATE t/TIME type/CARE_TYPE [notes/NOTES]\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + "d/2025-10-16 t/14:30 type/medication notes/Give insulin shot";

    public static final String MESSAGE_ADD_SESSION_SUCCESS = "Added CaringSession: %1$s to Patient: %2$s";
    public static final String MESSAGE_DUPLICATE_SESSION = "This caring session already exists for this patient";

    private final Index patientIndex;
    private final CaringSession sessionToAdd;

    /**
     * Creates an AddCaringSessionCommand to add the specified caring session to a patient.
     *
     * @param patientIndex Index of the patient in the filtered patient list.
     * @param sessionToAdd The caring session to add.
     */
    public AddCaringSessionCommand(Index patientIndex, CaringSession sessionToAdd) {
        requireNonNull(patientIndex);
        requireNonNull(sessionToAdd);
        this.patientIndex = patientIndex;
        this.sessionToAdd = sessionToAdd;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> patientList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= patientList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
        }

        Patient patient = patientList.get(patientIndex.getZeroBased());
        List<CaringSession> sessionList = patient.getCaringSessionList();

        if (patient.hasOverlappingSession(sessionToAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_SESSION);
        }

        List<CaringSession> updatedSessionList = new ArrayList<>(sessionList);
        updatedSessionList.add(sessionToAdd);

        Patient editedPatient = patient.withCaringSessionList(updatedSessionList);
        model.setPerson(patient, editedPatient);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_ADD_SESSION_SUCCESS,
            Messages.formatSession(sessionToAdd), Messages.formatPerson(patient)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddCaringSessionCommand otherAddCommand)) {
            return false;
        }
        return patientIndex.equals(otherAddCommand.patientIndex)
            && sessionToAdd.equals(otherAddCommand.sessionToAdd);
    }
}
