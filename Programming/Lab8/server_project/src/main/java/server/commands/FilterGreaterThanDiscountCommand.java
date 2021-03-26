package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.InvalidDiscountException;
import server.messages.Messenger;

import java.util.List;

/**
 * Class of filter_greater_than_discount command
 *
 * @version 1.0
 * @autor 47iq
 */

public class FilterGreaterThanDiscountCommand implements MessagingCommand {

    public FilterGreaterThanDiscountCommand() {
    }

    public Response execute(CollectionManager collectionManager, Ticket ticket1, String arg, Messenger messenger,
                            ObjectFactory factory) {
        double discount;
        try {
            discount = Double.parseDouble(arg);
        } catch (Exception e) {
            throw new InvalidDiscountException();
        }
        StringBuilder message = new StringBuilder();
        List<Ticket> tickets = collectionManager.filterDiscount(discount);
        for (Ticket ticket : tickets)
            message.append(messenger.getTicketMessage(ticket)).append("\n");
        return factory.getResponse(true, message.toString());
    }

    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
