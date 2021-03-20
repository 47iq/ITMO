package server.commands;

import common.Response;
import server.Main;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.CommandExecutionException;

/**
 * Class of remove_first command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveFirstCommand implements SimpleCommand {

    public RemoveFirstCommand() {

    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory, String login) {
        collectionManager.removeFirst(login);
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.doForSimple(this);
    }
}
