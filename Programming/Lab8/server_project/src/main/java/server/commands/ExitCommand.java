package server.commands;

import common.Response;
import org.apache.logging.log4j.LogManager;
import server.collection.CollectionManager;
import server.command_manager.Visitor;
import server.connection.Server;

/**
 * Class of exit command
 *
 * @version 1.0
 * @autor 47iq
 */

public class ExitCommand implements ServerCommand {

    private Server server;

    public ExitCommand(Server server) {
        this.server = server;
    }

    @Override
    public void execute(CollectionManager manager) {
        LogManager.getLogger().info("Program has been exited.");
        server.exit();
    }

    @Override
    public Response accept(Visitor visitor) {
        return visitor.visit(this);
    }
}
