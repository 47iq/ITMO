package server.commands;

import common.Response;
import server.Main;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.CommandExecutionException;
import server.exceptions.InvalidIdException;

/**
 * Class of remove_by_id command
 * @autor 47iq
 * @version 1.0
 */

public class RemoveByIdCommand implements SimpleCommand {

    public RemoveByIdCommand() {

    }

    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory, String login) {
        int id;
        try{
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new InvalidIdException();
        }
        collectionManager.removeById(id, login);
        return factory.getResponse(true, "");
    }

    public Response accept(Visitor visitor) {
        return visitor.doForSimple(this);
    }
}
