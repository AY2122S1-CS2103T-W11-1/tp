package seedu.address.logic.commands.person;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.model.person.Person;

/**
 * Deletes a person identified using it's displayed index from the address book.
 */
public class DeletePersonCommand extends DeleteCommand<Person> {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    public DeletePersonCommand(Index targetIndex) {
        super(targetIndex);
    }

    @Override
    public String getMessageUsage() {
        return MESSAGE_USAGE;
    }
    @Override
    public String getMessageSuccess() {
        return MESSAGE_DELETE_PERSON_SUCCESS;
    }
}
