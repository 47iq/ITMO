package main;

import commands.Command;
import commands.ScriptContaining;
import exceptions.ScriptFileRecursionException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCommandReader implements CommandReader{

    protected CollectionManager manager;

    protected ClientObjectFactory ticketFactory;

    private boolean isRunning;

    protected BufferedReader reader;

    protected CommandFactory commandFactory;

    protected Messenger messenger;

    protected static Set<File> files = new HashSet<>();

    public void readCommands() {
        isRunning = true;
        while (isRunning)
            try {
                if (readyForInput()) {
                    Command command = getNextCommand();
                    if(command instanceof ScriptContaining) {
                        File file = new File(((ScriptContaining) command).getFileName());
                        if(files.contains(file)) {
                            isRunning = false;
                            throw new ScriptFileRecursionException();
                        }
                        files.add(file);
                        command.execute();
                        files.remove(file);
                    }
                    else
                        command.execute();
                }
                else
                    isRunning = false;
            } catch (Exception e) {
                if (e.getMessage() != null)
                    System.err.println(e.getMessage());
                else
                    System.err.println("Error got while getting the manager.ticket.");
            }
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("An IOException occurred");
        } finally {
            isRunning = false;
        }
    }

    protected Command getNextCommand() throws IOException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        String[] command = reader.readLine().trim().split("\\s+");
        if (command.length == 1)
            return commandFactory.getCommand(command[0], this, null, manager);
        else
            return commandFactory.getCommand(command[0], this, command[1], manager);
    }

    public void exit() {
        isRunning = false;
    }

    public CommandFactory getCommandFactory() {
        return commandFactory;
    }

    protected abstract boolean readyForInput() throws IOException;
}
