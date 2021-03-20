package server.commands;

import common.Response;
import common.Ticket;
import server.Main;
import server.exceptions.CommandExecutionException;
import server.exceptions.InvalidIdException;
import server.exceptions.InvalidTicketException;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of update command
 * @autor 47iq
 * @version 1.0
 */

public class UpdateCommand implements TicketCommand {

    public UpdateCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, ObjectFactory factory, String login) {
        int id;
        try {
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new InvalidIdException();
        }
        if (ticket == null)
            throw new InvalidTicketException();
        collectionManager.updateId(id, ticket, login);
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.doForTicket(this);
    }
}
