package main;

import commands.*;

import exceptions.*;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Singleton class which parses commands and creates objects of them ({@link Command})
 * @autor 47iq
 * @version 1.0
 */

public class SingletonCommandFactory implements CommandFactory{

    private static SingletonCommandFactory instance = null;

    private final Map<String, Class<? extends Command>> commands;

    private SingletonCommandFactory(Map<String, Class<? extends Command>> commands) {
        this.commands = commands;
    }

    public static CommandFactory getInstance(Map<String, Class<? extends Command>> commands) {
        if(instance == null) {
            instance = new SingletonCommandFactory(commands);
        }
        return instance;
    }

    public Command getCommand(String commandName, CommandReader reader, String arg, CollectionManager collectionManager) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        try{
            Class[] params = {CollectionManager.class, CommandReader.class, String.class, CommandFactory.class};
            Constructor<? extends Command> constructor = commands.get(commandName).getConstructor(params);
            return constructor.newInstance(collectionManager, reader, arg, this);
        } catch (Exception e) {
            throw new UnknownCommandException();
        }
    }

    public Map<String, Class<? extends Command>> getAllCommands() {
        return commands;
    }
}
