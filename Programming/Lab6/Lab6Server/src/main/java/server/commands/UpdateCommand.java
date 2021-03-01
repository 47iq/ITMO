package server.commands;

import server.command_manager.Visitor;
import common.Response;
import common.Ticket;
import exceptions.InvalidTicketException;
import exceptions.TicketNotFoundException;
import server.collection.CollectionManager;
import server.ObjectFactory;

/**
 * Class of update command
 * @autor 47iq
 * @version 1.0
 */

public class UpdateCommand implements TicketCommand {

    public UpdateCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory) {
        int id;
        try {
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            return factory.getResponse(false, "Invalid ID has been entered. ID must be int.");
        }
        if (ticket == null)
            return factory.getResponse(false, new InvalidTicketException().getMessage());
        try {
            collectionManager.updateId(id, ticket);
        } catch (TicketNotFoundException e) {
            return factory.getResponse(false, e.getMessage());
        }  catch (Exception e) {
            return factory.getResponse(false, "Error got while updating ticket");
        }
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.doForTicket(this);
    }
}
