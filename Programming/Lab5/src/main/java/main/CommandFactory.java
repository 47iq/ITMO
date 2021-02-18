package main;

import commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface CommandFactory {
    Map<String, Class<? extends Command>> getAllCommands();
    void executeCommand(String command, CommandReader commandReader, String arg, CollectionManager collectionManager);
}
