package server.commands;

import common.Response;
import org.apache.logging.log4j.LogManager;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.connection.Server;

/**
 * Class of exit command
 * @autor 47iq
 * @version 1.0
 */

public class ExitCommand implements ServerCommand {

    private Server server;
    public ExitCommand(Server server) {
        this.server = server;
    }

    public void execute(CollectionManager manager) {
        //manager.saveData();
        LogManager.getLogger().info("Program has been exited.");
        server.exit();
    }

    public Response accept(Visitor visitor) {
        return visitor.doForServer(this);
    }
}
