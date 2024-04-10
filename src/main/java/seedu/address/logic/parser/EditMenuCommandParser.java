package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MENU;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_COST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRODUCT_SALES;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditMenuCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input for EditMenuCommand.
 */
public class EditMenuCommandParser implements Parser<EditMenuCommand> {
    /**
     * Parses input arguments and creates a new EditMenuCommand object.
     */
    public EditMenuCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args,
                        PREFIX_MENU, PREFIX_PRODUCT_NAME, PREFIX_PRODUCT_COST, PREFIX_PRODUCT_SALES);

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_MENU, PREFIX_PRODUCT_NAME,
                PREFIX_PRODUCT_COST, PREFIX_PRODUCT_SALES);

        Index index;

        try {
            index = ParserUtil.parseIndex(
                    argMultimap.getValue(PREFIX_MENU)
                            .orElseThrow(() -> new ParseException("")));
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditMenuCommand.MESSAGE_USAGE), pe);
        }

        EditMenuCommand.EditProductDescriptor editProductDescriptor =
                new EditMenuCommand.EditProductDescriptor();

        if (argMultimap.getValue(PREFIX_PRODUCT_NAME).isPresent()) {
            editProductDescriptor.setName(ParserUtil.parseProduct(
                    argMultimap.getValue(PREFIX_PRODUCT_NAME).get()).getName());
        }

        if (argMultimap.getValue(PREFIX_PRODUCT_COST).isPresent()) {
            editProductDescriptor.setCost(ParserUtil.parsePrice(
                    argMultimap.getValue(PREFIX_PRODUCT_COST).get()));
        }

        if (argMultimap.getValue(PREFIX_PRODUCT_SALES).isPresent()) {
            editProductDescriptor.setSales(ParserUtil.parsePrice(
                    argMultimap.getValue(PREFIX_PRODUCT_SALES).get()));
        }

        if (!editProductDescriptor.isAllFieldsEdited()) {
            throw new ParseException(EditMenuCommand.MESSAGE_NOT_EDITED);
        }

        return new EditMenuCommand(index, editProductDescriptor);
    }
}
