package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class DeleteCommand<T> extends Command<T> {

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    /**
     * @return String that shows how to use this command.
     */
    public abstract String getMessageUsage();
    /**
     * @return String when command executed successfully.
     */
    public abstract String getMessageSuccess();

    @Override
    public CommandResult execute(Model<T> model) throws CommandException {
        requireNonNull(model);
        List<T> lastShownList = model.getFilteredList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        T itemToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.delete(itemToDelete);
        return new CommandResult(String.format(getMessageSuccess(), itemToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteCommand) other).targetIndex)); // state check
    }
}
