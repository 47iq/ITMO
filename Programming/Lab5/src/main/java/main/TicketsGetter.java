package main;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.io.Reader;

public abstract class TicketsGetter {

    /**
     * Method used to get {@link JSONArray} of {@link AbstractTicket} from {@link File} by its {@link String} name
     * @param dataFileName name of input file
     * @param reader  file reader
     * @param parser json parser
     * @return {@link JSONArray} array of json tickets
     */

    public static JSONArray inputTickets(String dataFileName, Reader reader, JSONParser parser) {
        File dataFile = new File(dataFileName);
        JSONArray jsonData = null;
        char[] fileContent = new char[(int) dataFile.length()];
        try {
            reader.read(fileContent);
            reader.close();
        } catch (IOException e) {
            System.err.println("Can't read data from the file " + dataFileName + ".\n Data reading has been stopped");
            System.exit(1);
        }
        String dataString = new String (fileContent);
        try {
            jsonData = (JSONArray) parser.parse(dataString);
        } catch (ParseException e) {
            System.err.println("Parsing error. Can't get data from the file");;
        }
        return jsonData;
    }
}
