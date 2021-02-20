package main;

import commands.*;

import exceptions.*;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Singleton class which parses {@link Command} and executes it
 * @autor 47iq
 * @version 1.0
 */

public class SingletonCommandFactory implements CommandFactory{

    private static SingletonCommandFactory instance = null;

    private final Map<String, Command> commands;
    private final ClientObjectFactory ticketFactory;
    private final Messenger messenger;
    protected static Set<File> files = new HashSet<>();

    private SingletonCommandFactory(Map<String, Command> commands, ClientObjectFactory ticketFactory, Messenger messenger) {
        this.commands = commands;
        this.ticketFactory = ticketFactory;
        this.messenger = messenger;
    }

    /**
     * Method to get instance of CommandFactory
     * @param commands map of command classes
     * @param ticketFactory object factory
     * @param messenger messenger
     * @return command factory
     */

    public static CommandFactory getInstance(Map<String, Command> commands, ClientObjectFactory ticketFactory, Messenger messenger) {
        if(instance == null) {
            instance = new SingletonCommandFactory(commands, ticketFactory, messenger);
        }
        return instance;
    }

    private Command getCommand(String commandName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return commands.get(commandName);

    }

    public void executeCommand(String commandName, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        Command command;
        try {
            command = getCommand(commandName);
        } catch (Exception e) {
            throw new UnknownCommandException();
        }
        if(command instanceof SimpleCommand)
            executeSimpleCommand((SimpleCommand) command, commandReader, arg, collectionManager);
        else if(command instanceof MessagingCommand)
            executeMessagingCommand((MessagingCommand) command, commandReader, arg, collectionManager);
        else if(command instanceof ScriptCommand)
            executeScriptCommand((ScriptCommand) command, commandReader, arg, collectionManager);
        else
            System.err.println("Error got while executing the command.");
    }

    private void executeSimpleCommand(SimpleCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, commandReader, arg);
    }

    private void executeMessagingCommand(MessagingCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        command.execute(collectionManager, commandReader, arg, messenger);
    }

    private void executeScriptCommand(ScriptCommand command, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        File file = new File(arg);
        if(files.contains(file)) {
            System.err.println("Error. Script recursion has been detected.");
            return;
        }
        files.add(file);
        command.execute(collectionManager, commandReader, arg, this, ticketFactory);
        files.remove(file);
    }
}
