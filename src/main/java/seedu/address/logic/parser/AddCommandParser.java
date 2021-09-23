package seedu.address.logic.parser;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddPersonCommand object
 */
public interface AddCommandParser<T> extends Parser<AddCommand<T>> {
    AddCommand<T> parse(String args) throws ParseException;
}
