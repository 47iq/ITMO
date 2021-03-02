package server.commands;

import common.Response;
import exceptions.TicketNotFoundException;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of remove_by_id command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveByIdCommand implements SimpleCommand {

    public RemoveByIdCommand() {

    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory) {
        int id;
        try{
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            return factory.getResponse(false, "Error. ID must be int.");
        }
        try {
            collectionManager.removeById(id);
            return factory.getResponse(true, "");
        } catch (TicketNotFoundException e) {
            return factory.getResponse(false, e.getMessage());
        } catch (Exception e) {
            return factory.getResponse(false, "Error got  while removing ticket");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForSimple(this);
    }
}
