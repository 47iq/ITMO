package commands;

import main.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Class of execute_script command
 * @autor 47iq
 * @version 1.0
 */

public class ExecuteScriptCommand implements Command, ScriptContaining {

    /**
     * File containing script
     */

    private File file;

    private String fileName;

    private CollectionManager collectionManager;

    private CommandReader commandReader;


    public ExecuteScriptCommand(CollectionManager collectionManager, CommandReader reader, String arg) {
        this.collectionManager = collectionManager;
        this.commandReader = reader;
        this.fileName = arg;
    }

    public void execute() {
        try{
            file = new File(fileName);
        } catch (Exception e) {
            System.err.println("File named: " + fileName + " not found.");
        }
        //System.out.println("Executing script from the file has started");
        try {
            CommandReader fileCommandReader = ObjectFactory.getFileReader(commandReader.getCommandFactory(), collectionManager, file);
            fileCommandReader.readCommands();
            //System.out.println("Executing script from the file has finished");
        } catch (Exception e) {
            System.err.println("Error got while executing script from the file: " + file);
        } finally {
            commandReader.readCommands();
        }
    }

    public String getFileName(){
        return fileName;
    }

    public static String strConvert() {
        return "execute_script (file_name): execute script from the given file.";
    }
}
