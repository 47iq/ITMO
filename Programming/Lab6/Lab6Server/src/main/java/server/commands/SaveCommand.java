package server.commands;

import server.collection.CollectionManager;
import server.command_manager.Visitor;
import common.Response;
import org.apache.logging.log4j.LogManager;

/**
 * Class of save command
 * @autor 47iq
 * @version 1.0
 */

public class SaveCommand implements ServerCommand {

    public SaveCommand() {
    }

    public void execute(CollectionManager collectionManager) {
        try {
            collectionManager.saveData();
        } catch (Exception e) {
            LogManager.getLogger().error("Error got while saving the collection");
        }
    }

    public Response accept(Visitor visitor) {
        return visitor.doForServer(this);
    }
}
