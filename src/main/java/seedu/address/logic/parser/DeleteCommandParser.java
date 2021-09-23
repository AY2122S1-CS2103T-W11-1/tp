package seedu.address.logic.parser;

import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePersonCommand object
 */
public interface DeleteCommandParser<T> extends Parser<DeleteCommand<T>> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePersonCommand
     * and returns a DeletePersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    DeleteCommand<T> parse(String args) throws ParseException;
}
