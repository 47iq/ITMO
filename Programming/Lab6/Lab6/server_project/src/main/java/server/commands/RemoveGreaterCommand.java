package server.commands;

import common.Response;
import common.Ticket;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of remove_greater command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveGreaterCommand implements TicketCommand {

    public RemoveGreaterCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory) {
        try {
            if(ticket != null)
                collectionManager.removeGreater(ticket);
            return factory.getResponse(true, "");
        } catch (Exception e) {
            return factory.getResponse(false, "Error got while removing elements");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForTicket(this);
    }
}
