package server.commands;

import server.command_manager.Visitor;
import common.Response;
import common.Ticket;
import exceptions.InvalidTicketException;
import server.collection.CollectionManager;
import server.ObjectFactory;

/**
 * Class of add command
 * @autor 47iq
 * @version 1.0
 */

public class AddCommand implements TicketCommand {

    /**
     * Constructor of the add command
     */

    public AddCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory) {
        if(ticket == null)
            return factory.getResponse(false, new InvalidTicketException().getMessage());
        try {
            collectionManager.convertAddTicket(ticket);
            return factory.getResponse(true, "");
        } catch (Exception e) {
            return factory.getResponse(false, new InvalidTicketException().getMessage());
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForTicket(this);
    }
}
