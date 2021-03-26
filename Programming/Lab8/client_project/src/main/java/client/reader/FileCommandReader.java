package client.reader;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.ticket.TicketBuilder;
import common.Ticket;
import common.UpdateData;

import java.io.*;

/**
 * Class that is used for reading commands(scripts) from file
 */

public class FileCommandReader extends AbstractCommandReader {

    private TicketBuilder builder;

    /**
     * Constructor for file command reader
     *
     * @param commandFactory factory to resolve commands
     * @param file           file we want to read from
     * @param ticketFactory  object factory
     * @throws FileNotFoundException if file we want to read from doesn't exist
     */

    public FileCommandReader(ConnectionManager commandFactory, File file, ObjectFactory ticketFactory) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
        builder = ticketFactory.getTicketBuilder();
    }

    public Ticket readTicket() throws IOException {
        builder.setName(reader.readLine());
        builder.setX(reader.readLine().trim());
        builder.setY(reader.readLine().trim());
        builder.setDiscount(reader.readLine().trim());
        builder.setPrice(reader.readLine().trim());
        builder.setRefundable(reader.readLine().trim());
        builder.setType(reader.readLine().trim());
        builder.setWeight(reader.readLine().trim());
        builder.setEyeColor(reader.readLine().trim());
        builder.setHairColor(reader.readLine().trim());
        builder.setCountry(reader.readLine().trim());
        return builder.getResult();
    }

    public TicketBuilder getBuilder() {
        return builder;
    }

    public UpdateData getUpdateData() {
        return ticketFactory.getDefaultUpdateData();
    }

    protected boolean readyForInput() throws IOException {
        return reader.ready();
    }
}
