package server.command_manager;

import common.Response;
import common.Ticket;
import common.UpdateData;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.commands.Command;
import server.exceptions.CommonException;
import server.exceptions.UnknownCommandException;

import java.util.Map;

/**
 * Singleton class which parses {@link Command} and executes it
 *
 * @version 1.0
 * @autor 47iq
 */

public class ServerCommandFactory implements CommandFactory {

    private volatile static ServerCommandFactory instance = null;

    private final Map<String, Command> commands;
    private Map<String, Command> serverCommands;
    private final ObjectFactory ticketFactory;
    private final CollectionManager collectionManager;

    private ServerCommandFactory(Map<String, Command> commands, ObjectFactory ticketFactory, CollectionManager manager) {
        this.commands = commands;
        this.ticketFactory = ticketFactory;
        collectionManager = manager;
    }

    public static CommandFactory getInstance(Map<String, Command> commands, ObjectFactory ticketFactory, CollectionManager manager) {
        if (instance == null)
            synchronized (ServerCommandFactory.class) {
                if (instance == null)
                    instance = new ServerCommandFactory(commands, ticketFactory, manager);
            }
        return instance;
    }

    public void setServerCommands(Map<String, Command> serverCommands) {
        this.serverCommands = serverCommands;
    }

    public Response executeCommand(String commandName, Ticket ticket, String arg, String user, UpdateData updateData) {
        Command command;
        Visitor visitor = ticketFactory.getCommandVisitor(arg, ticket, collectionManager, ticketFactory, user, updateData);
        if (user != null && user.equals(System.getenv("ADMIN")))
            command = getServerCommand(commandName);
        else
            command = getCommand(commandName);
        try {
            return command.accept(visitor);
        } catch (Exception e) {
            if (e instanceof CommonException)
                return ticketFactory.getResponse(false, e.getMessage());
            else {
                LogManager.getLogger().info("Unknown command : {} got.", commandName);
                throw new UnknownCommandException();
            }
        }
    }

    private Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    private Command getServerCommand(String commandName) {
        return serverCommands.get(commandName);
    }
}