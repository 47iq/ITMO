package client.commands;

import client.ObjectFactory;
import client.connection.RequestFactory;
import client.connection.RequestSender;
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

    public void execute(RequestSender requestSender, CommandReader commandReader, String fileName, RequestFactory commandFactory, ObjectFactory ticketFactory) {
        File file;
        try{
            file = new File(fileName);
        } catch (Exception e) {
            System.err.println("File named: " + fileName + " not found.");
            return;
        }
        try {
            CommandReader fileCommandReader = ticketFactory.getFileReader(commandFactory, file);
            fileCommandReader.readCommands();
        } catch (Exception e) {
            System.err.println("Error got while executing script from the file: " + file);
        } finally {
            commandReader.readCommands();
        }
    }

    public String getCommandName() {
        return "execute_script";
    }
}
