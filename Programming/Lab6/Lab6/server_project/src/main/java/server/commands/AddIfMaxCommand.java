package server.commands;

import common.Response;
import common.Ticket;
import exceptions.InvalidTicketException;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of add_if_max command
 * @autor 47iq
 * @version 1.0
 */

public class AddIfMaxCommand implements TicketCommand {

    public AddIfMaxCommand() {
    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory) {
        if(ticket == null)
            return factory.getResponse(false, new InvalidTicketException().getLocalizedMessage());
        try{
            collectionManager.addIfMax(ticket);
            return factory.getResponse(true, "");
        } catch (Exception e) {
            return factory.getResponse(false, "Error got while adding the ticket");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForTicket(this);
    }
}
