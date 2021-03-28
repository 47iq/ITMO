package server.commands;

import common.Response;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of save command
 *
 * @version 1.0
 * @autor 47iq
 */

public class SaveCommand implements ServerCommand {

    public SaveCommand() {
    }

    @Override
    public void execute(CollectionManager collectionManager) {

    }

    @Override
    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
