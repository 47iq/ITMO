package server.commands;

import common.Response;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of clear command
 *
 * @version 1.0
 * @autor 47iq
 */

public class ClearCommand implements SimpleCommand {

    public ClearCommand() {
    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory, String login) {
        collectionManager.clear(login);
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
