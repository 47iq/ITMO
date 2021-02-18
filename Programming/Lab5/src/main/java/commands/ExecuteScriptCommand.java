package commands;

import main.*;

import java.io.File;

/**
 * Class of execute_script command
 * @autor 47iq
 * @version 1.0
 */

public class ExecuteScriptCommand implements ScriptCommand {

    public ExecuteScriptCommand() {

    }

    public void execute(CollectionManager collectionManager, CommandReader commandReader, String fileName, CommandFactory commandFactory, ClientObjectFactory ticketFactory) {
        File file;
        try{
            file = new File(fileName);
        } catch (Exception e) {
            System.err.println("File named: " + fileName + " not found.");
            return;
        }
        //System.out.println("Executing script from the file has started");
        try {
            CommandReader fileCommandReader = ticketFactory.getFileReader(commandFactory, collectionManager, file);
            fileCommandReader.readCommands();
            //System.out.println("Executing script from the file has finished");
        } catch (Exception e) {
            System.err.println("Error got while executing script from the file: " + file);
        } finally {
            commandReader.readCommands();
        }
    }
}
