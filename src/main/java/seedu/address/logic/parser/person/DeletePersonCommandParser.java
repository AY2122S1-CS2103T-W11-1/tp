package seedu.address.logic.parser.person;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.person.DeletePersonCommand;
import seedu.address.logic.parser.DeleteCommandParser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Person;

public class DeletePersonCommandParser implements DeleteCommandParser<Person> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeletePersonCommand
     * and returns a DeletePersonCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand<Person> parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePersonCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePersonCommand.MESSAGE_USAGE), pe);
        }
    }
}
