package server.commands;

import common.Response;
import common.Ticket;
import common.UpdateData;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.InvalidIdException;
import server.exceptions.InvalidTicketException;

/**
 * Class of update command
 *
 * @version 1.0
 * @autor 47iq
 */

public class UpdateCommand implements UpdatingCommand {

    public UpdateCommand() {

    }

    public Response execute(CollectionManager collectionManager, Ticket ticket, String arg, UpdateData updateData,
                            ObjectFactory factory, String login) {
        int id;
        try {
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new InvalidIdException();
        }
        if (ticket == null)
            throw new InvalidTicketException();
        collectionManager.updateId(id, ticket, login, updateData);
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
