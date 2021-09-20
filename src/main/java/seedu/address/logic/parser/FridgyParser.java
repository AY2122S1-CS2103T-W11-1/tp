package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

public interface FridgyParser<T> {
    Command<T> parseCommand(String userInput) throws ParseException;
}
