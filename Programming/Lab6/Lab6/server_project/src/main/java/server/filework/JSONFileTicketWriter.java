package server.filework;

import org.apache.logging.log4j.LogManager;
import org.json.simple.JSONArray;
import server.ticket.ServerTicket;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Collection;

/**
 * Abstract class which is used to save given collection of tickets to the given file
 * @autor 47iq
 * @version 1.0
 */

public class JSONFileTicketWriter implements TicketWriter {

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

    public void saveTickets(Collection<ServerTicket> tickets) {
        JSONArray jsonArray = new JSONArray();
        for (ServerTicket ticket : tickets) {
            try {
                jsonArray.add(ticket.toJSON());
            } catch (Exception e) {
                LogManager.getLogger().error("{} got while converting ticket.", e.getClass());
            }
        }
        try {
            File output = new File(dataFileName);
            FileOutputStream outputStream = new FileOutputStream(output);
            outputStream.write(jsonArray.toJSONString().getBytes());
            outputStream.close();
            LogManager.getLogger().info("Data has been successfully saved to file.");
        } catch (IOException e) {
            LogManager.getLogger().error("IOException got while saving data.");
        }
    }
}
