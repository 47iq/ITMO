package main;

import exceptions.InputFileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Collection;

/**
 * Class to parse tickers from json file
 */

public class JSONTicketsReader implements TicketsParser, TicketFieldCaster {

    private String dataFileName;
    private Reader reader;
    private JSONParser parser = new JSONParser();

    /**
     * Constructor that also opens file input stream reader
     * @param dataFileName name of file
     * @throws FileNotFoundException if file not found
     */

    public JSONTicketsReader(String dataFileName) throws FileNotFoundException {
        this.dataFileName = dataFileName;
        reader = getFileReader();
    }

    private Reader getFileReader() throws FileNotFoundException {
        File dataFile = new File(dataFileName);
        if(dataFileName == null)
            throw new InputFileNotFoundException();
        return new BufferedReader(new FileReader(dataFile));
    }

    /**
     * Method used to get {@link JSONArray} of {@link Ticket} from {@link File} by its {@link String} name
     * @return {@link JSONArray} array of json tickets
     */

    private JSONArray getJSONTickets() {
        File dataFile = new File(dataFileName);
        JSONArray jsonData = null;
        char[] fileContent = new char[(int) dataFile.length()];
        try {
            reader.read(fileContent);
            reader.close();
        } catch (IOException e) {
            System.err.println("Can't read data from the file " + dataFileName + ".\n Data reading has been stopped");
        }
        String dataString = new String (fileContent);
        try {
            jsonData = (JSONArray) parser.parse(dataString);
        } catch (ParseException e) {
            System.err.println("Parsing error. Can't get data from the file");
        }
        return jsonData;
    }

    public Collection<Ticket> getTickets() {
        Collection<Ticket> tickets = ObjectFactory.getTicketsCollection();
        JSONArray ticketsJSON = getJSONTickets();
        try {
            for (Object obj : ticketsJSON) {
                tickets.add(ObjectFactory.getTicket((JSONObject) obj));
            }
        }catch (ClassCastException e) {
            System.err.println("Error while adding ticket. All fields should be entered as strings");
            return tickets;
        }
        return tickets;
    }
}
