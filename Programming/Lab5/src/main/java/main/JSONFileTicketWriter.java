package main;

import main.ticket.Ticket;
import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Abstract class which is used to save given collection of tickets to the given file
 * @autor 47iq
 * @version 1.0
 */

public class JSONFileTicketWriter implements TicketWriter {

    private Collection<Ticket> tickets = new ArrayList<>();

    private final String dataFileName;

    /**
     * Constructor of JSONFileTicketManager
     * @param dataFileName name of file
     */

    public JSONFileTicketWriter(String dataFileName){
        this.dataFileName = dataFileName;
    }

    /**
     * Method which is used to save given collection of tickets to the given file
     * @param tickets ticket collection we want to save into the file
     */

    public void saveTickets(Collection<Ticket> tickets) {
        this.tickets = tickets;
        JSONArray jsonArray = new JSONArray();
        //int cnt = 0;
        for (Ticket ticket : tickets) {
            //System.out.println("Converting manager.ticket " + manager.ticket.getId() + "...");
            try {
                jsonArray.add(ticket.toJSON());
                //cnt++;
                //System.out.println("AbstractTicket has been successfully converted");
            } catch (Exception e) {
                if(e.getMessage() != null)
                    System.err.println(e.getMessage());
                else
                    System.err.println("Error got while converting manager.ticket.");
            }
        }
        //System.out.println(cnt + " of " + tickets.size() + " tickets have been converted successfully. Saving...");
        try {
            File output = new File(dataFileName);
            FileOutputStream outputStream = new FileOutputStream(output);
            outputStream.write(jsonArray.toJSONString().getBytes());
            outputStream.close();
            //System.out.println("Data has been successfully saved to the file");
        } catch (IOException e) {
            System.err.println("IOException got while saving data.");
        }
    }
}
