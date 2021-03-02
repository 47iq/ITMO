package server.filework;

import common.Coordinates;
import common.DefaultCoordinates;
import common.DefaultPerson;
import common.Person;
import exceptions.InputFileNotFoundException;
import exceptions.InvalidCoordinatesException;
import exceptions.InvalidPersonException;
import exceptions.InvalidTicketException;
import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import server.ObjectFactory;
import server.ticket.ServerTicket;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class to parse tickers from json file
 */

public class JSONFileTicketsReader implements TicketReader {

    private final String dataFileName;

    private final Reader reader;

    private final JSONParser parser = new JSONParser();

    private final ObjectFactory ticketFactory;

    /**
     * JSONFileTicketsReader constructor
     * @param dataFileName name of JSON file
     * @param ticketFactory object factory
     * @throws FileNotFoundException if file doesn't exist
     */

    public JSONFileTicketsReader(String dataFileName, ObjectFactory ticketFactory) throws FileNotFoundException {
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

    public Collection<ServerTicket> getTickets() {
        Collection<ServerTicket> tickets = new ArrayList<>();
        JSONArray ticketsJSON = getJSONTickets();
        for (Object obj : ticketsJSON)
            try {
                tickets.add(getTicket((JSONObject) obj));
            } catch (Exception e) {
                LogManager.getLogger().error("Error got while adding ticket from JSON file.");
            }
        LogManager.getLogger().info("{} tickets have been parsed from file.", tickets.size());
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
            LogManager.getLogger().error("Can't read data from the file " + dataFileName + ".\n Data reading has been stopped");
        }
        String dataString = new String (fileContent);
        try {
            jsonData = (JSONArray) parser.parse(dataString);
            LogManager.getLogger().info("Data has been successfully parsed from file.");
        } catch (ParseException e) {
            LogManager.getLogger().error("Parsing error. Can't get data from the file.");
        }
        return jsonData;
    }

    private ServerTicket getTicket(JSONObject jsonTicket) {
        try {
            ServerTicket ticket = ticketFactory.getServerTicket();
            ticket.setIdStr((String) jsonTicket.get("id"));
            ticket.setDateStr((String) jsonTicket.get("creationDate"));
            ticket.setNameStr((String) jsonTicket.get("name"));
            ticket.setCoordinates(getCoordinates((JSONObject) jsonTicket.get("coordinates")));
            ticket.setPriceStr((String) jsonTicket.get("price"));
            ticket.setDiscountStr((String) jsonTicket.get("discount"));
            ticket.setRefundableStr(manageRefundable(jsonTicket.get("refundable")));
            ticket.setTypeStr(manageType(jsonTicket.get("type")));
            ticket.setPerson(getPerson((JSONObject) jsonTicket.get("person")));
            return ticket;
        } catch (Exception e) {
            throw new InvalidTicketException();
        }
    }

    private Coordinates getCoordinates(JSONObject jsonCoordinates) {
        try {
            DefaultCoordinates coordinates = ticketFactory.getCoordinates();
            coordinates.setXStr((String) jsonCoordinates.get("x"));
            coordinates.setYStr((String) jsonCoordinates.get("y"));
            return coordinates;
        } catch (Exception e) {
            throw new InvalidCoordinatesException();
        }
    }

    private Person getPerson(JSONObject jsonObject) {
        try {
            DefaultPerson person = ticketFactory.getPerson();
            person.setWeightStr(manageWeight(jsonObject.get("weight")));
            person.setEyeColorStr((String)  jsonObject.get("eyeColor"));
            person.setHairColorStr((String) jsonObject.get("hairColor"));
            person.setNationalityStr((String) jsonObject.get("nationality"));
            return person;
        } catch (Exception e) {
            throw new InvalidPersonException();
        }
    }


    private String manageWeight(Object jsonWeight) {
        if(jsonWeight == null)
            return null;
        return ((String) jsonWeight);
    }

    private String manageRefundable(Object jsonRefundable) {
        if(jsonRefundable == null)
            return null;
        else
            return ((String) jsonRefundable);
    }

    private String manageType(Object jsonType) {
        if(jsonType == null)
            return null;
        else
            return ((String) jsonType);
    }
}
