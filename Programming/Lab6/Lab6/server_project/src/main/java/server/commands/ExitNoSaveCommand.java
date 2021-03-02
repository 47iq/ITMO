package server.commands;

import common.Response;
import org.apache.logging.log4j.LogManager;
import server.collection.CollectionManager;
import server.command_manager.Visitor;

public class ExitNoSaveCommand implements ServerCommand{

    public ExitNoSaveCommand() {

    }

    public void execute(CollectionManager manager) {
        LogManager.getLogger().info("Program has been exited.");
        System.exit(0);
    }

    public Response accept(Visitor visitor) {
        return visitor.doForServer(this);
    }
}
