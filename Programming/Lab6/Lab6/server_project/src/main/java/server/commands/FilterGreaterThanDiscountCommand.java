package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.messages.Messenger;

import java.util.List;

/**
 * Class of filter_greater_than_discount command
 * @autor 47iq
 * @version 1.0
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
            return factory.getResponse(false, "Invalid discount value has been entered. Discount must be double.");
        }
        try {
            StringBuilder message = new StringBuilder();
            List<Ticket> tickets = collectionManager.filterDiscount(discount);
            for(Ticket ticket: tickets)
                message.append(messenger.getTicketMessage(ticket)).append("\n");
            return factory.getResponse(true, message.toString());
        } catch (Exception e) {
            return factory.getResponse(false, "Error got while displaying the elements");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForMessaging(this);
    }
}
