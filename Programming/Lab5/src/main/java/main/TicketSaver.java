package main;

import org.json.simple.JSONArray;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;
import java.util.Queue;

/**
 * Abstract class which is used to save given collection of tickets to the given file
 * @autor 47iq
 * @version 1.0
 */

public abstract class TicketSaver {

    /**
     * Method which is used to save given collection of tickets to the given file
     * @param tickets {@link Collection<AbstractTicket>} of tickets
     * @param dataFileName name of the output {@link File}
     */

    public static void saveTickets(Collection<AbstractTicket> tickets, String dataFileName) {
        JSONArray jsonArray = new JSONArray();
        //int cnt = 0;
        for (AbstractTicket ticket : tickets) {
            //System.out.println("Converting ticket " + ticket.getId() + "...");
            try {
                jsonArray.add(ticket.toJSON());
                //cnt++;
                //System.out.println("AbstractTicket has been successfully converted");
            } catch (Exception e) {
                if(e.getMessage() != null)
                    System.err.println(e.getMessage());
                else
                    System.err.println("Error got while converting ticket.");
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
