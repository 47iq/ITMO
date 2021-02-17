package manager;

import exceptions.InputFileNotFoundException;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import ticket.Ticket;

import java.io.*;
import java.util.Collection;

/**
 * Class to parse tickers from json file
 */

public class JSONFileTicketsReader implements TicketReader, CasterOfDefaultTicket {

    private String dataFileName;
    private Reader reader;
    private JSONParser parser = new JSONParser();

    /**
     * Constructor that also opens file input stream reader
     * @param dataFileName name of file
     * @throws FileNotFoundException if file not found
     */

    public JSONFileTicketsReader(String dataFileName) throws FileNotFoundException {
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
        for (Object obj : ticketsJSON)
            try {
                tickets.add(ObjectFactory.getTicket((JSONObject) obj));
            } catch (Exception e) {
                String id;
                System.err.println("Error got while adding ticket from JSON.");
            }
        return tickets;
    }
}
