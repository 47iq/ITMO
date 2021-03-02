package server.command_manager;

import common.Response;
import common.Ticket;
import exceptions.UnknownCommandException;
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
    private Messenger messenger;
    private final CollectionManager collectionManager;
    private final Visitor visitor;

    private ServerCommandFactory(Map<String, Command> commands, ObjectFactory ticketFactory, Messenger messenger, CollectionManager manager, Visitor visitor) {
        this.commands = commands;
        this.ticketFactory = ticketFactory;
        this.messenger = messenger;
        collectionManager = manager;
        this.visitor = visitor;
    }

    public static CommandFactory getInstance(Map<String, Command> commands, ObjectFactory ticketFactory, Messenger messenger, CollectionManager manager, Visitor visitor) {
        if(instance == null)
            synchronized (ServerCommandFactory.class) {
                if (instance == null)
                    instance = new ServerCommandFactory(commands, ticketFactory, messenger, manager, visitor);
        }
        return instance;
    }


    public void setMessenger(Locale locale) {
        this.messenger = ticketFactory.getLocalMessenger(locale);
    }

    public void setServerCommands(Map<String, Command> serverCommands) {
        this.serverCommands = serverCommands;
    }

    public Response executeCommand(String commandName, Ticket ticket, String arg, String password){
        Command command;
        visitor.configure(ticket, arg, collectionManager, ticketFactory, messenger);
        if(password != null && password.equals(System.getenv("PASSWORD")))
            command = getServerCommand(commandName);
        else
            command = getCommand(commandName);
        try {
            return command.accept(visitor);
        } catch (Exception e) {
            LogManager.getLogger().info("Unknown command : {} got.", commandName);
            return ticketFactory.getResponse(false, new UnknownCommandException().getMessage());
        }
    }

    private Command getCommand(String commandName) {
        return commands.get(commandName);
    }

    private Command getServerCommand(String commandName) {
        return serverCommands.get(commandName);
    }
}