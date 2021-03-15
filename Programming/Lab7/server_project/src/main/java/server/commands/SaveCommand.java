package server.commands;

import common.Response;
import org.apache.logging.log4j.LogManager;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

/**
 * Class of save command
 * @autor 47iq
 * @version 1.0
 */

public class SaveCommand implements ServerCommand {

    public SaveCommand() {
    }

    public void execute(CollectionManager collectionManager) {

    }

    public Response accept(Visitor visitor) {
        return visitor.doForServer(this);
    }
}
