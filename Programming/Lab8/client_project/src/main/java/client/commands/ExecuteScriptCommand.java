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
 *
 * @version 1.0
 * @autor 47iq
 */

public class ExecuteScriptCommand implements ScriptCommand {

    public ExecuteScriptCommand() {

    }

    public void execute(CommandReader commandReader, String fileName, ConnectionManager commandFactory, ObjectFactory ticketFactory) {
        File file;
        try {
            file = new File(fileName);
        } catch (Exception e) {
            throw new ScriptFileNotFoundException();
        }
        try {
            CommandReader fileCommandReader = ticketFactory.getFileReader(commandFactory, file);
            fileCommandReader.executeNext();
        } catch (Exception e) {
            throw new ScriptException();
        } finally {
            commandReader.executeNext();
        }
    }
}
