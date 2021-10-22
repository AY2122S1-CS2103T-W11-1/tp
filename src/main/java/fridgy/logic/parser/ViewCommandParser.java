package fridgy.logic.parser;

import fridgy.commons.core.index.Index;
import fridgy.logic.commands.ViewCommand;
import fridgy.logic.parser.exceptions.ParseException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static fridgy.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class ViewCommandParser implements Parser<ViewCommand>{

    private static final Pattern INGREDIENT_VIEW_COMMAND_ARGUMENT_FORMAT = Pattern
            .compile(ViewCommand.INGREDIENT_KEYWORD + "(?<arguments>.*)");

    @Override
    public ViewCommand parse(String userInput) throws ParseException {
        final Matcher matcher = INGREDIENT_VIEW_COMMAND_ARGUMENT_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
        }
        final String args = matcher.group("arguments");

        try {
            Index index = ParserUtil.parseIndex(args);
            return new ViewCommand(index);
        } catch (ParseException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE), e);
        }
    }
}