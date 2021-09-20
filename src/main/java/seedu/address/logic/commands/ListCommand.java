package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL;

import seedu.address.model.Model;

public abstract class ListCommand<T> extends Command<T> {

    /**
     * @return String when command executed successfully.
     */
    public abstract String getMessageSuccess();

    @Override
    public CommandResult execute(Model<T> model) {
        requireNonNull(model);
        model.updateFilteredList(PREDICATE_SHOW_ALL);
        return new CommandResult(getMessageSuccess());
    }

}
