package server.commands;

import common.Response;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of remove_first command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveFirstCommand implements SimpleCommand {

    public RemoveFirstCommand() {

    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory) {
        try {
            collectionManager.removeFirst();
            return factory.getResponse(true, "");
        } catch (Exception e) {
            return factory.getResponse(false, "Error got while removing the first element");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForSimple(this);
    }
}
