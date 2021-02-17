package main;

import commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface CommandFactory {
    Command getCommand(String commandName, CommandReader commandReader, String arg, CollectionManager collectionManager) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
    Map<String, Class<? extends Command>> getAllCommands();
}
