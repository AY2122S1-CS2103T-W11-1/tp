package fridgy.logic.commands;

import static java.util.Objects.requireNonNull;

import fridgy.model.Inventory;
import fridgy.model.Model;

/**
 * Clears the Inventory.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Description book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setInventory(new Inventory());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
