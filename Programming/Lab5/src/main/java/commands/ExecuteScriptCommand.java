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

    /**
     * File containing script
     */

    private final File file;

    /**
     * Constructor of the execute_script command
     * @param file file containing script
     */

    public ExecuteScriptCommand(File file) {
        this.file = file;
    }

    public void execute() {
        //System.out.println("Executing script from the file has started");
        try {
            BufferedReader reader = CommandFactory.getInput();
            BufferedReader fileReader = new BufferedReader(new FileReader(file));
            Main.setReader(fileReader);
            CommandFactory.executeCommandsFromFile(file);
            //System.out.println("Executing script from the file has finished");
            Main.setReader(reader);
        } catch (Exception e) {
            System.err.println("Error got while executing script from the file: " + file);
        }
    }
}
