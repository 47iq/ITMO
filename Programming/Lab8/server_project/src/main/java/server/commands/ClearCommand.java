package server.commands;

import common.Response;
import server.Main;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.CommandExecutionException;

/**
 * Class of clear command
 * @autor 47iq
 * @version 1.0
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
