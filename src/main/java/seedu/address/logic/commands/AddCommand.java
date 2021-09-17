package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

public abstract class AddCommand<T> extends Command<T> {
    public static final String COMMAND_WORD = "add";

    private final T toAdd;

    /**
     * Creates an AddCommand to add a generic item.
     * @param item Item of generic type T to be added.
     */
    public AddCommand(T item) {
        requireNonNull(item);
        this.toAdd = item;
    }

    /**
     * @return String that shows how to use this command.
     */
    public abstract String getMessageUsage();
    /**
     * @return String when command executed successfully.
     */
    public abstract String getMessageSuccess();
    /**
     * @return String when trying to add a duplicate item.
     */
    public abstract String getMessageDuplicate();


    @Override
    public CommandResult execute(Model<T> model) throws CommandException {
        requireNonNull(model);

        if (model.has(toAdd)) {
            throw new CommandException(getMessageDuplicate());
        }

        model.add(toAdd);
        return new CommandResult(String.format(getMessageSuccess(), toAdd));
    };

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
