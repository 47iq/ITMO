package main;

import commands.*;

import exceptions.*;

import java.lang.reflect.Constructor;
import java.util.Map;

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

    public Command getCommand(String commandName, CommandReader reader, String arg, CollectionManager collectionManager) {
        Command command = getSimpleCommand(commandName, reader, arg, collectionManager);
        if(command == null)
            command = getScriptCommand(commandName, reader, arg, collectionManager);
        if(command == null)
            command = getMessageOperatingCommand(commandName, reader, arg, collectionManager);
        if(command == null)
            throw new UnknownCommandException();
        return command;
    }

    private Command getSimpleCommand(String commandName, CommandReader reader, String arg, CollectionManager collectionManager) {
        try {
            Class[] params = {CollectionManager.class, CommandReader.class, String.class};
            Constructor<? extends Command> constructor = commands.get(commandName).getConstructor(params);
            return constructor.newInstance(collectionManager, reader, arg);
        } catch (Exception e) {
            return null;
        }
    }

    private Command getMessageOperatingCommand(String commandName, CommandReader reader, String arg, CollectionManager collectionManager) {
        try{
            Class[] params = {CollectionManager.class, CommandReader.class, String.class, Messenger.class};
            Constructor<? extends Command> constructor = commands.get(commandName).getConstructor(params);
            return constructor.newInstance(collectionManager, reader, arg, messenger);
        } catch (Exception e) {
            return null;
        }
    }

    private Command getScriptCommand(String commandName, CommandReader reader, String arg, CollectionManager collectionManager) {
        try{
            Class[] params = {CollectionManager.class, CommandReader.class, String.class, CommandFactory.class, ClientObjectFactory.class};
            Constructor<? extends Command> constructor = commands.get(commandName).getConstructor(params);
            return constructor.newInstance(collectionManager, reader, arg, this, ticketFactory);
        } catch (Exception e) {
            return null;
        }
    }

    public Map<String, Class<? extends Command>> getAllCommands() {
        return commands;
    }
}
