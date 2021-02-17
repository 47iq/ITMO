package manager;

import exceptions.InvalidTicketFieldException;
import ticket.*;

import java.io.*;

public class ConsoleCommandReader extends AbstractCommandReader implements CasterOfDefaultTicket {

    public ConsoleCommandReader(CommandFactory commandFactory, CollectionManager manager) {
        reader = new BufferedReader(new InputStreamReader(System.in));
        super.commandFactory = commandFactory;
        super.manager = manager;
    }

    public Ticket readTicket() throws IOException {
        System.out.println("Enter the ticket data.\nEnter the name:");
        String name = catchCastName(reader.readLine());
        System.out.println("Enter coordinates.\nEnter x coordinate:");
        double xCoordinate = catchCastX(reader.readLine().trim());
        System.out.println("Enter y coordinate:");
        Integer yCoordinate = catchCastY(reader.readLine().trim());
        System.out.println("Enter the discount value:");
        double discount = catchCastDiscount(reader.readLine().trim());
        System.out.println("Enter the price:");
        int price = catchCastPrice(reader.readLine().trim());
        System.out.println("Enter the refundable:");
        Boolean refundable = catchCastRefundable(reader.readLine().trim());
        System.out.println("Enter the ticket type. Choose one of the following: VIP, CHEAP, USUAL");
        TicketType type = catchCastType(reader.readLine().trim());
        System.out.println("Enter the person data.\nEnter person's weight:");
        Long weight = catchCastWeight(reader.readLine().trim());
        System.out.println("Enter person's eyes color. Choose one of the following: BLACK, BLUE, YELLOW");
        EyesColor personEyesColor = catchCastEyesColor(reader.readLine().trim());
        System.out.println("Enter person's hair color. Choose one of the following: BLACK, GREEN, RED, YELLOW");
        HairColor personHairColor = catchCastHairColor(reader.readLine().trim());
        System.out.println("Enter person's nationality. Choose one of the following: RUSSIA, SPAIN, FRANCE, CHINA");
        Country country = catchCastCountry(reader.readLine().trim());
        return ObjectFactory.getTicket(name, xCoordinate, yCoordinate, price, discount,
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