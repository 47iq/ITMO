package server.commands;

import server.collection.CollectionManager;
import server.command_manager.Visitor;
import common.Response;
import org.apache.logging.log4j.LogManager;

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
