package client.commands;

import client.Main;
import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.exceptions.ScriptException;
import client.exceptions.ScriptFileNotFoundException;
import client.reader.CommandReader;

import java.io.File;

/**
 * Class of execute_script command
 * @autor 47iq
 * @version 1.0
 */

public class ExecuteScriptCommand implements ScriptCommand {

    public ExecuteScriptCommand() {

    }

    public void execute(CommandReader commandReader, String fileName, ConnectionManager commandFactory, ObjectFactory ticketFactory) {
        File file;
        try{
            file = new File(fileName);
        } catch (Exception e) {
            Main.getErr().println(new ScriptFileNotFoundException(fileName).accept(Main.getExceptionMessenger()));
            return;
        }
        try {
            CommandReader fileCommandReader = ticketFactory.getFileReader(commandFactory, file);
            fileCommandReader.readCommands();
        } catch (Exception e) {
            Main.getErr().println(new ScriptException(fileName).accept(Main.getExceptionMessenger()));
        } finally {
            commandReader.readCommands();
        }
    }
}
