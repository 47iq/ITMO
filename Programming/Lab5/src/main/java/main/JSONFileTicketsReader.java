package main;

import exceptions.*;
import main.ticket.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.Collection;

/**
 * Class to parse tickers from json file
 */

public class JSONFileTicketsReader implements TicketReader, CasterOfDefaultTicket {

    private final String dataFileName;
    private final Reader reader;
    private final JSONParser parser = new JSONParser();
    private final ServerObjectFactory ticketFactory;

    /**
     * Constructor that also opens file input stream reader
     * @param dataFileName name of file
     * @throws FileNotFoundException if file not found
     */

    public JSONFileTicketsReader(String dataFileName, ServerObjectFactory ticketFactory) throws FileNotFoundException {
        this.dataFileName = dataFileName;
        this.ticketFactory = ticketFactory;
        try {
            reader = getFileReader();
        } catch (Exception e) {
            throw new InputFileNotFoundException();
        }
    }

    private Reader getFileReader() throws FileNotFoundException {
        File dataFile = new File(dataFileName);
        return new BufferedReader(new FileReader(dataFile));
    }

    public Collection<Ticket> getTickets() {
        Collection<Ticket> tickets = ticketFactory.getTicketsCollection();
        JSONArray ticketsJSON = getJSONTickets();
        for (Object obj : ticketsJSON)
            try {
                tickets.add(getTicket((JSONObject) obj));
            } catch (Exception e) {
                System.err.println("Error got while adding ticket from JSON file.");
            }
        return tickets;
    }

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

    private Ticket getTicket(JSONObject jsonTicket) {
        try {
            Object id = jsonTicket.get("id");
            Object time = jsonTicket.get("creationDate");
            String name = (String) jsonTicket.get("name");
            Coordinates coordinates = getCoordinates((JSONObject) jsonTicket.get("coordinates"));
            int price = castPrice((String) jsonTicket.get("price"));
            double discount = castDiscount((String) jsonTicket.get("discount"));
            Boolean refundable = manageRefundable(jsonTicket.get("refundable"));
            TicketType type = manageType(jsonTicket.get("type"));
            Person person = getPerson((JSONObject) jsonTicket.get("person"));
            return ticketFactory.getTicket(id, time, name, coordinates, price, discount, refundable, type, person);
        } catch (Exception e) {
            throw new InvalidTicketException();
        }
    }

    private Coordinates getCoordinates(JSONObject jsonCoordinates) {
        try {
            double x = castXCoordinate((String) jsonCoordinates.get("x"));
            Integer y = castYCoordinate((String) jsonCoordinates.get("y"));
            return ticketFactory.getCoordinates(x, y);
        } catch (Exception e) {
            throw new InvalidCoordinatesException();
        }
    }

    private Person getPerson(JSONObject jsonObject) {
        try {
            Long weight = manageWeight(jsonObject.get("weight"));
            EyesColor eyesColor = castEyesColor((String) jsonObject.get("eyeColor"));
            HairColor hairColor = castHairColor((String) jsonObject.get("hairColor"));
            Country country = castCountry((String) jsonObject.get("nationality"));
            return ticketFactory.getPerson(weight, eyesColor, hairColor, country);
        } catch (Exception e) {
            throw new InvalidPersonException();
        }
    }


    private Long manageWeight(Object jsonWeight) {
        if(jsonWeight == null)
            return null;
        return castWeight((String) jsonWeight);
    }

    private Boolean manageRefundable(Object jsonRefundable) {
        if(jsonRefundable == null)
            return null;
        else
            return castRefundable((String) jsonRefundable);
    }

    private TicketType manageType(Object jsonType) {
        if(jsonType == null)
            return null;
        else
            return castType((String) jsonType);
    }
}
