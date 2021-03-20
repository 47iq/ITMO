package server.command_manager;

import common.Response;
import common.Ticket;
import server.exceptions.CommonException;
import server.exceptions.ServerExceptionMessenger;
import server.exceptions.UnknownCommandException;
import org.apache.logging.log4j.LogManager;
import server.ObjectFactory;
import server.collection.CollectionManager;
import server.commands.Command;
import server.messages.Messenger;

import java.util.Locale;
import java.util.Map;

/**
 * Singleton class which parses {@link Command} and executes it
 * @autor 47iq
 * @version 1.0
 */

public class ServerCommandFactory implements CommandFactory{

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
        if(instance == null)
            synchronized (ServerCommandFactory.class) {
                if (instance == null)
                    instance = new ServerCommandFactory(commands, ticketFactory, manager);
        }
        return instance;
    }

    public void setServerCommands(Map<String, Command> serverCommands) {
        this.serverCommands = serverCommands;
    }

    public Response executeCommand(String commandName, Ticket ticket, String arg, String user, Locale locale) {
        Command command;
        Messenger messenger = ticketFactory.getLocalMessenger(locale);
        ServerExceptionMessenger errVisitor = ticketFactory.getLocalErrMessenger(locale);
        Visitor visitor = ticketFactory.getCommandVisitor(arg, ticket, collectionManager, ticketFactory, messenger, user);
        if (user != null && user.equals(System.getenv("ADMIN")))
            command = getServerCommand(commandName);
        else
            command = getCommand(commandName);
        try {
            return command.accept(visitor);
        } catch (Exception e) {
            if(e instanceof CommonException)
                ticketFactory.getResponse(false, ((CommonException) e).accept(errVisitor));
            LogManager.getLogger().info("Unknown command : {} got.", commandName);
            return ticketFactory.getResponse(false, new UnknownCommandException().accept(errVisitor));
        }
    }

    private Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    private Command getServerCommand(String commandName) {
        return serverCommands.get(commandName);
    }
}