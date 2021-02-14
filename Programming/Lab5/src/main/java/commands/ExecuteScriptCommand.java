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

public class ExecuteScriptCommand implements Command {
    String fileName;

    /**
     * Constructor of the execute_script command
     * @param fileName {@link String}
     */

    public ExecuteScriptCommand(String fileName) {
        this.fileName = fileName;
        execute();
    }

    public void execute() {
        //System.out.println("Executing script from the file has started");
        try {
            File file = new File(fileName);
            BufferedReader reader = CommandFactory.getInput();
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            Main.setReader(fileReader);
            CommandFactory.executeCommandsFromFile();
            //System.out.println("Executing script from the file has finished");
            Main.setReader(reader);
        } catch (FileNotFoundException e) {
            System.err.println("There is no file with this name: " + fileName);
        } catch (Exception e) {
            System.err.println("Error got while executing script from the file: " + fileName);
        }
    }
}
