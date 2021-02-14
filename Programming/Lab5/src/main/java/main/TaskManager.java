package main;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import exceptions.EmptyTicketsException;
import exceptions.TicketNotFoundException;
import exceptions.UnknownCommandException;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Class, which is the realization of {@link AbstractTaskManager}
 * @autor 47iq
 * @version 1.0
 */

public class TaskManager extends AbstractTaskManager {

    /**
     * {@link JSONParser} to get data from json file
     */
    private final JSONParser parser = new JSONParser();

    /**
     * Condition of the program (true if executing, false if not)
     */

    private boolean isRunning;

    /**
     * Creation time of {@link #tickets}
     */

    private java.time.ZonedDateTime creationDate;

    /**
     * Method used to get {@link #reader}
     * @return reader {@link BufferedReader} for the {@link #dataFileName}
     */

    private Reader getFileReader() {
        try {
            File dataFile = new File(dataFileName);
            return new BufferedReader(new FileReader(dataFile));
        } catch (FileNotFoundException e) {
            System.err.println("Error! There's no file named like this: " + dataFileName + "\nTry another name.");
            return getFileReader();
        }
    }


    /**
     * Constructor of the {@link TaskManager}
     * @param dataFileName {@link String} name ot the data file
     */

    public TaskManager(String dataFileName){
        this.dataFileName = dataFileName;
        reader = getFileReader();
        tickets = new PriorityQueue<>();
        creationDate = ZonedDateTime.now(ZoneId.of("Europe/Moscow"));
        isRunning = false;
    }


    public void start(){
        isRunning = true;
        try {
            parseDataToCollection();
            //System.out.println("Data has been successfully parsed into the collection.\n" +
            //        "Enter the command. You can see the command list by typing \"help\" command.");
        } catch (Exception e) {
            System.err.println("Error while parsing data into the collection");
        }
        while(isRunning)
            try {
                CommandFactory.executeNextCommand();
            } catch (UnknownCommandException e) {
                System.err.println(e.getMessage());
            } catch (IOException e) {
                System.err.println("Error got waiting for the command");
            }
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("An I/O exception occurred");
        }
        System.exit(0);
    }

    /**
     * Parses data from {@link #dataFileName} to the {@link #tickets} using {@link #reader} if data is a valid JSON
     */

    void parseDataToCollection() {

        File dataFile = new File(dataFileName);
        JSONArray jsonData = null;
        char[] fileContent = new char[(int) dataFile.length()];
        try {
            reader.read(fileContent);
            reader.close();
        } catch (IOException e) {
            //System.out.println("Can't read data from the file " + dataFileName + ".\n Data reading has been stopped");
            System.out.println(e.getMessage());
            System.exit(1);
        }
        String dataString = new String (fileContent);
        try {
            jsonData = (JSONArray) parser.parse(dataString);
        } catch (ParseException e) {
            //System.out.println("Parsing error. Check if the file is valid JSON");
            System.out.println(e.getMessage());;
            System.exit(1);
        }
        jsonData.forEach(obj->addTicket((JSONObject) obj));
    }

    /**
     * Parses {@link AbstractTicket} from {@link JSONObject} and adds it to {@link #tickets}
     * @param jsonObject {@link JSONObject} containing data of the {@link AbstractTicket}
     */

    private void addTicket(JSONObject jsonObject) {
        AbstractTicket ticket = null;
        try {
            ticket = Main.getTicket(jsonObject);
            addTicket(ticket);
            //System.out.println("AbstractTicket " + ticket.getId() + " has been added successfully.");
        } catch (ClassCastException e) {
            System.err.println("Error while adding ticket. All fields should be entered as strings");
        }
    }

    public void addTicket(AbstractTicket ticket) {
        tickets.add(ticket);
    }

    public void displayInfo() {
         System.out.println("Collection type: " + tickets.getClass().toString() +
                 "\nNumber of tickets: " + tickets.size() + "\nCreation time: " + creationDate);
    }

    public void displayElements() {
         for (AbstractTicket ticket: tickets) {
             System.out.println(TicketMessagesFactory.getTicketMessage(ticket));
         }
    }

    /**
     * Gets {@link List<AbstractTicket>} containing all of the {@link #tickets}
     * @return ticketsList {@link List<AbstractTicket>}
     */

    private List<AbstractTicket> getTicketsList() {
        List<AbstractTicket> ticketsList = new ArrayList<>();
        PriorityQueue<AbstractTicket> ticketsBackup = new PriorityQueue<>();
        int sz = tickets.size();
        for(int i = 0; i < sz; i++){
            AbstractTicket ticket = tickets.poll();
            ticketsList.add(ticket);
            ticketsBackup.add(ticket);
        }
        tickets = ticketsBackup;
        return ticketsList;
    }

    public void printRefundable() {
        sort();
        List<AbstractTicket> ticketsList = getTicketsList();
        for(int i = ticketsList.size() - 1; i >= 0; i--) {
            System.out.println(ticketsList.get(i).getRefundable());
        }
    }


    public void updateId(int id, AbstractTicket myTicket) {
        boolean found = false;
        for(AbstractTicket ticket: tickets) {
            if(ticket.getId() == id) {
                tickets.remove(ticket);
                tickets.add(myTicket);
                found = true;
                break;
            }
        }
        if(!found)
            throw new TicketNotFoundException();
    }


    public boolean elementExists(int id) {
        boolean found = false;
        for(AbstractTicket ticket: tickets) {
            if(ticket.getId() == id) {
                found = true;
                break;
            }
        }
        return found;
    }


    public void removeById(int id) {
        boolean found = false;
        for(AbstractTicket ticket: tickets) {
            if(ticket.getId() == id) {
                tickets.remove(ticket);
                found = true;
                break;
            }
        }
        if(!found)
            throw new TicketNotFoundException();
    }


    public void clear() {
        tickets = new PriorityQueue<>();
    }


    public void exit() {
        isRunning = false;
    }

    public void removeFirst() {
        tickets.remove();
    }

    /**
     * Sorts {@link #tickets} using {@link TreeSet<Ticket>}
     */

    public void sort() {
        SortedSet<AbstractTicket> ticketsSet = new TreeSet<>();
        while (!tickets.isEmpty()){
            ticketsSet.add(tickets.poll());
        }
        tickets = new PriorityQueue<>(ticketsSet);
    }

    private AbstractTicket maxTicket() {
        AbstractTicket ticketMax = tickets.peek();
        for(AbstractTicket ticket: tickets) {
            if(ticket.compareTo(ticketMax) > 0)
                ticketMax = ticket;
        }
        return ticketMax;
    }

    public void addIfMax(AbstractTicket ticket) {
        if(!tickets.isEmpty() && ticket.compareTo(maxTicket()) > 0)
            addTicket(ticket);
            //System.out.println("Ticket has been successfully added");
    }

    public void removeGreater(AbstractTicket ticket) {
        List<AbstractTicket> ticketList = getTicketsList();
        Collections.sort(ticketList);
        for(int i = ticketList.size() - 1; i >= 0; i--) {
            AbstractTicket ticketI = ticketList.get(i);
            if(ticketI.compareTo(ticket) > 0)
                tickets.remove(ticketI);
            else
                break;
        }
    }

    public void maxByCoordinates() {
        Coordinates maxCoordinates = Coordinates.getLeastCoordinates();
        AbstractTicket maxTicket = null;
        for(AbstractTicket ticket: tickets) {
            if(maxCoordinates.compareTo(ticket.getCoordinates()) <= 0) {
                maxCoordinates = ticket.getCoordinates();
                maxTicket = ticket;
            }
        }
        if(maxTicket != null)
            System.out.println(TicketMessagesFactory.getTicketMessage(maxTicket));
        else
            throw new EmptyTicketsException();
    }

    public void filterDiscount(double discount) {
        for(AbstractTicket ticket: tickets)
            if(discount < ticket.getDiscount())
                System.out.println(TicketMessagesFactory.getTicketMessage(ticket));
    }

    public void saveDataToFile() {
        JSONArray jsonArray = new JSONArray();
        int cnt = 0;
        for(AbstractTicket ticket: tickets) {
            //System.out.println("Converting ticket " + ticket.getId() + "...");
            try {
                jsonArray.add(ticket.toJSON());
                cnt++;
               //System.out.println("AbstractTicket has been successfully converted");
            } catch (Exception e) {
                e.printStackTrace();
                System.err.println("Error got while converting ticket.");
            }
        }
        //System.out.println(cnt + " of " + tickets.size() + " tickets have been converted successfully. Saving...");
        try {
            File output = new File(dataFileName);
            FileOutputStream outputStream = new FileOutputStream(output);
            outputStream.write(jsonArray.toJSONString().getBytes());
            outputStream.close();
            System.err.println("Data has been successfully saved to the file");
        } catch (IOException e) {
            System.err.println("IOException got while saving data.");
        }
    }
}
