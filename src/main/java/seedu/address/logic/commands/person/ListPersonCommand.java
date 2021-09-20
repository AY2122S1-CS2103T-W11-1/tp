package seedu.address.logic.commands.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Lists all persons in the address book to the user.
 */
public class ListPersonCommand extends Command<Person> {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model<Person> model) {
        requireNonNull(model);
        model.updateFilteredList(PREDICATE_SHOW_ALL);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
