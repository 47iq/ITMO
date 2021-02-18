package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractCommandReader implements CommandReader{

    protected CollectionManager manager;

    protected ClientObjectFactory ticketFactory;

    private boolean isRunning;

    protected BufferedReader reader;

    protected CommandFactory commandFactory;

    protected Messenger messenger;

    public void readCommands() {
        isRunning = true;
        while (isRunning)
            try {
                if (readyForInput()) {
                    String[] command = reader.readLine().trim().split("\\s+");
                    if (command.length == 1)
                        commandFactory.executeCommand(command[0], this, null, manager);
                    else
                        commandFactory.executeCommand(command[0], this, command[1], manager);
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

    public void exit() {
        isRunning = false;
    }

    protected abstract boolean readyForInput() throws IOException;
}
