package server.commands;

import common.Response;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.exceptions.InvalidIdException;

/**
 * Class of remove_by_id command
 *
 * @version 1.0
 * @autor 47iq
 */

public class RemoveByIdCommand implements SimpleCommand {

    public RemoveByIdCommand() {

    }

    @Override
    public Response execute(CollectionManager collectionManager, String arg, ObjectFactory factory, String login) {
        int id;
        try {
            id = Integer.parseInt(arg);
        } catch (Exception e) {
            throw new InvalidIdException();
        }
        collectionManager.removeById(id, login);
        return factory.getResponse(true, "");
    }

    @Override
    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
