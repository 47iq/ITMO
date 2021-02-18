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
 * Singleton class which parses commands and creates objects of them ({@link Command})
 * @autor 47iq
 * @version 1.0
 */

public class SingletonCommandFactory implements CommandFactory{

    private static SingletonCommandFactory instance = null;

    private final Map<String, Class<? extends Command>> commands;
    private final ClientObjectFactory ticketFactory;
    private final Messenger messenger;
    protected static Set<File> files = new HashSet<>();

    private SingletonCommandFactory(Map<String, Class<? extends Command>> commands, ClientObjectFactory ticketFactory, Messenger messenger) {
        this.commands = commands;
        this.ticketFactory = ticketFactory;
        this.messenger = messenger;
    }

    public static CommandFactory getInstance(Map<String, Class<? extends Command>> commands, ClientObjectFactory ticketFactory, Messenger messenger) {
        if(instance == null) {
            instance = new SingletonCommandFactory(commands, ticketFactory, messenger);
        }
        return instance;
    }

    public Command getCommand(String commandName) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Class[] params = {};
        Constructor<? extends Command> constructor = commands.get(commandName).getConstructor(params);
        Command command = constructor.newInstance();
        return command;
    }

    public Map<String, Class<? extends Command>> getAllCommands() {
        return commands;
    }

    public void executeCommand(String commandName, CommandReader commandReader, String arg, CollectionManager collectionManager) {
        Command command;
        try {
            command = getCommand(commandName);
        } catch (Exception e) {
            throw new UnknownCommandException();
        }
        try {
            executeSimpleCommand((SimpleCommand) command, commandReader, arg, collectionManager);
            return;
        } catch (Exception e) {
            //
        }
        try {
            executeMessagingCommand((MessagingCommand) command, commandReader, arg, collectionManager);
            return;
        } catch (Exception e) {
            //
        }
        try {
            executeScriptCommand((ScriptCommand) command, commandReader, arg, collectionManager);
        } catch (Exception e) {
            System.err.println("Something went wrong executing the program.");
        }
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
