package main;

import commands.Command;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public interface CommandFactory {
    void executeCommand(String command, CommandReader commandReader, String arg, CollectionManager collectionManager);
}
