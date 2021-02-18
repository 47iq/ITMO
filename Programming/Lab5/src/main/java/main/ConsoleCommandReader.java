package main;

import exceptions.InvalidTicketFieldException;
import main.ticket.*;

import java.io.*;

/**
 * Class that is used for reading commands from console
 */

public class ConsoleCommandReader extends AbstractCommandReader implements CasterOfDefaultTicket {

    /**
     * Constructor for console reader
     * @param commandFactory factory to resolve commands
     * @param manager ticket collection's manager
     * @param ticketFactory object factory
     * @param messenger class that provides local messaging
     */

    public ConsoleCommandReader(CommandFactory commandFactory, CollectionManager manager, ClientObjectFactory ticketFactory, Messenger messenger) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
        super.manager = manager;
        super.messenger = messenger;
    }

    public Ticket readTicket() throws IOException {
        System.out.println(messenger.getNameInputInvitationMessage());
        String name = catchCastName(reader.readLine());
        System.out.println(messenger.getXInputInvitationMessage());
        double xCoordinate = catchCastX(reader.readLine().trim());
        System.out.println(messenger.getYInputInvitationMessage());
        Integer yCoordinate = catchCastY(reader.readLine().trim());
        System.out.println(messenger.getDiscountInputInvitationMessage());
        double discount = catchCastDiscount(reader.readLine().trim());
        System.out.println(messenger.getPriceInputInvitationMessage());
        int price = catchCastPrice(reader.readLine().trim());
        System.out.println(messenger.getRefundableInputInvitationMessage());
        Boolean refundable = catchCastRefundable(reader.readLine().trim());
        System.out.println(messenger.getTicketTypeInputInvitationMessage());
        TicketType type = catchCastType(reader.readLine().trim());
        System.out.println(messenger.getWeightInputInvitationMessage());
        Long weight = catchCastWeight(reader.readLine().trim());
        System.out.println(messenger.getEyeColorInputInvitationMessage());
        EyesColor personEyesColor = catchCastEyesColor(reader.readLine().trim());
        System.out.println(messenger.getHairColorInputInvitationMessage());
        HairColor personHairColor = catchCastHairColor(reader.readLine().trim());
        System.out.println(messenger.getCountryInputInvitationMessage());
        Country country = catchCastCountry(reader.readLine().trim());
        return ticketFactory.getTicket(name, xCoordinate, yCoordinate, price, discount,
                refundable, type, weight, personEyesColor, personHairColor, country);
    }

    private String catchCastName(String str) throws IOException {
        try{
            return castName(str);
        } catch (Exception e) {
            repeatInput(e);
            return castName(reader.readLine());
        }
    }

    private double catchCastX(String str) throws IOException {
        try{
            return castXCoordinate(str);
        } catch (Exception e) {
            repeatInput(e);
            return castXCoordinate(reader.readLine());
        }
    }

    private Integer catchCastY(String str) throws IOException {
        try{
            return castYCoordinate(str);
        } catch (Exception e) {
            repeatInput(e);
            return castYCoordinate(reader.readLine());
        }
    }

    private double catchCastDiscount(String str) throws IOException {
        try{
            return castDiscount(str);
        } catch (Exception e) {
            repeatInput(e);
            return castDiscount(reader.readLine());
        }
    }

    private int catchCastPrice(String str) throws IOException {
        try{
            return castPrice(str);
        } catch (Exception e) {
            repeatInput(e);
            return castPrice(reader.readLine());
        }
    }

    private TicketType catchCastType(String str) throws IOException {
        try{
            return castType(str);
        } catch (Exception e) {
            repeatInput(e);
            return castType(reader.readLine());
        }
    }

    private Boolean catchCastRefundable(String str) throws IOException {
        try{
            return castRefundable(str);
        } catch (Exception e) {
            repeatInput(e);
            return castRefundable(reader.readLine());
        }
    }

    private Country catchCastCountry(String str) throws IOException {
        try{
            return castCountry(str);
        } catch (Exception e) {
            repeatInput(e);
            return castCountry(reader.readLine());
        }
    }

    private Long catchCastWeight(String str) throws IOException {
        try{
            return castWeight(str);
        } catch (Exception e) {
            repeatInput(e);
            return castWeight(reader.readLine());
        }
    }

    private EyesColor catchCastEyesColor(String str) throws IOException {
        try{
            return castEyesColor(str);
        } catch (Exception e) {
            repeatInput(e);
            return castEyesColor(reader.readLine());
        }
    }

    private HairColor catchCastHairColor(String str) throws IOException {
        try{
            return castHairColor(str);
        } catch (Exception e) {
            repeatInput(e);
            return castHairColor(reader.readLine());
        }
    }

    /**
     * Displays input error and offers to repeat the input of the value
     */

    private void repeatInput(Exception e) {
        if(e instanceof InvalidTicketFieldException)
            System.err.print(e.getMessage());
        else if(e instanceof NumberFormatException)
            System.err.print("Number format error got.");
        else
            System.err.print("Error.");
        System.err.println(" Try to enter the field again:");
    }

    protected boolean readyForInput() throws IOException {
        return true;
    }
}