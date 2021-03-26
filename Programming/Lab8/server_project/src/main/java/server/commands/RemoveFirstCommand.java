package server.commands;

import common.Response;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of remove_first command
 *
 * @version 1.0
 * @autor 47iq
 */

public class RemoveFirstCommand implements SimpleCommand {

    public RemoveFirstCommand() {

    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory, String login) {
        collectionManager.removeFirst(login);
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
