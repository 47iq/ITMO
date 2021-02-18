package main;

import main.ticket.*;

import java.io.*;

/**
 * Class that is used for reading commands(scripts) from file
 */

public class FileCommandReader extends AbstractCommandReader implements CasterOfDefaultTicket {

    /**
     * Constructor for file command reader
     * @param commandFactory factory to resolve commands
     * @param manager ticket collection manager
     * @param file file we want to read from
     * @param ticketFactory object factory
     * @throws FileNotFoundException if file we want to read from doesn't exist
     */

    public FileCommandReader(CommandFactory commandFactory, CollectionManager manager, File file, ClientObjectFactory ticketFactory) throws FileNotFoundException {
        reader = new BufferedReader(new FileReader(file));
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
        super.manager = manager;
    }

    public Ticket readTicket() throws IOException {
        String name = castName(reader.readLine());
        double xCoordinate = castXCoordinate(reader.readLine().trim());
        Integer yCoordinate = castYCoordinate(reader.readLine().trim());
        double discount = castDiscount(reader.readLine().trim());
        int price = castPrice(reader.readLine().trim());
        Boolean refundable = castRefundable(reader.readLine().trim());
        TicketType type = castType(reader.readLine().trim());
        Long weight = castWeight(reader.readLine().trim());
        EyesColor personEyesColor = castEyesColor(reader.readLine().trim());
        HairColor personHairColor = castHairColor(reader.readLine().trim());
        Country country = castCountry(reader.readLine().trim());
        return ticketFactory.getTicket(name, xCoordinate, yCoordinate, price, discount, refundable, type, weight, personEyesColor, personHairColor, country);
    }

    protected boolean readyForInput() throws IOException {
        return reader.ready();
    }
}
