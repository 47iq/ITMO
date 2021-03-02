package server.commands;

import common.Response;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of clear command
 * @autor 47iq
 * @version 1.0
 */

public class ClearCommand implements SimpleCommand {

    public ClearCommand() {
    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory) {
        try {
            collectionManager.clear();
            return factory.getResponse(true, "");
        } catch (Exception e) {
            return factory.getResponse(false, "Error got while clearing collection");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForSimple(this);
    }
}
