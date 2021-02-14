package main;

import exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Abstract class which parses {@link AbstractTicket} and its fields from {@link String}
 * @autor 47iq
 * @version 1.0
 */


public abstract class TicketParser {

    private static BufferedReader input;

    private static boolean inputIsFromFile = false;

    public static void setInput(BufferedReader reader) {
        input = reader;
    }

    /**
     * Parses and returns {@link AbstractTicket} from {@link #input} with outputting tips for user
     * @return {@link AbstractTicket} ticket
     */

    public static AbstractTicket parseTicket() {
        try {
            String name;
            EyesColor personEyesColor;
            HairColor personHairColor;
            double xCoordinate;
            Integer yCoordinate;
            double discount;
            int price;
            Boolean refundable;
            TicketType type;
            Long weight;
            Country country;
            System.out.println("Enter the ticket data.\nEnter the name:");
            name = parseName(input.readLine());
            System.out.println("Enter coordinates.\nEnter x coordinate:");
            xCoordinate = parseX(input.readLine());
            System.out.println("Enter y coordinate:");
            yCoordinate = parseY(input.readLine());
            Coordinates coordinates = Main.getCoordinates(xCoordinate, yCoordinate);
            System.out.println("Enter the discount value:");
            discount = parseDiscount(input.readLine());
            System.out.println("Enter the price:");
            price = parsePrice(input.readLine());
            System.out.println("Enter the refundable:");
            refundable = parseRefundable(input.readLine());
            System.out.println("Enter the ticket type. Choose one of the following: VIP, CHEAP, USUAL");
            type = parseType(input.readLine());
            System.out.println("Enter the person data.\nEnter person's weight:");
            weight = parseWeight(input.readLine());
            System.out.println("Enter person's eyes color. Choose one of the following: BLACK, BLUE, YELLOW");
            personEyesColor = parseEyesColor(input.readLine());
            System.out.println("Enter person's hair color. Choose one of the following: BLACK, GREEN, RED, YELLOW");
            personHairColor = parseHairColor(input.readLine());
            System.out.println("Enter person's nationality. Choose one of the following: RUSSIA, SPAIN, FRANCE, CHINA");
            country = parseCountry(input.readLine());
            Person person = Main.getPerson(weight, personEyesColor, personHairColor, country);
            return Main.getTicket(name, coordinates, price, discount, refundable, type, person);
        } catch (Exception e) {
            System.err.println("Error got while getting a ticket. Aborting the command...");
            return null;
        }
    }

    /**
     * Parses and returns {@link AbstractTicket} from {@link #input} without outputting tips for user
     * @return {@link AbstractTicket} ticket
     */

    public static AbstractTicket parseTicketFromFile() {
        try {
            inputIsFromFile = true;
            String name = parseName(input.readLine());
            double xCoordinate = parseX(input.readLine());
            Integer yCoordinate = parseY(input.readLine());
            Coordinates coordinates = Main.getCoordinates(xCoordinate, yCoordinate);
            double discount = parseDiscount(input.readLine());
            int price = parsePrice(input.readLine());
            Boolean refundable = parseRefundable(input.readLine());
            TicketType type = parseType(input.readLine());
            Long weight = parseWeight(input.readLine());
            EyesColor personEyesColor = parseEyesColor(input.readLine());
            HairColor personHairColor = parseHairColor(input.readLine());
            Country country = parseCountry(input.readLine());
            Person person = Main.getPerson(weight, personEyesColor, personHairColor, country);
            return Main.getTicket(name, coordinates, price, discount, refundable, type, person);
        } catch (Exception e) {
            if(e.getMessage() != null)
                System.err.print(e.getMessage());
            else
                System.err.print("Error got while parsing a ticket.");
            //System.out.println(" Aborting the command...");
            return null;
        } finally {
            inputIsFromFile = false;
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getName()} from {@link String}
     * @param inputStr {@link String}
     * @return name {@link String}
     * @throws IOException in case of some {@link #input} error
     */

    public static String parseName(String inputStr) throws IOException {
        try {
            if(inputStr != null)
                return inputStr;
            else
                throw new InvalidNameException();
        } catch (Exception e) {
            repeatInput();
            return parseName(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Coordinates#getX()} from {@link String}
     * @param inputStr {@link String}
     * @return x double
     * @throws IOException in case of some {@link #input} error
     */

    public static double parseX(String inputStr) throws IOException {
        try {
            double x = Double.parseDouble(inputStr);
            if(Coordinates.xCoordinateValid(x))
                return x;
            else
                throw new InvalidXCoordinateException();
        } catch (Exception e) {
            repeatInput();
            return parseX(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getRefundable()} from {@link String}
     * @param inputStr {@link String}
     * @return refundable {@link Boolean}
     * @throws IOException in case of some {@link #input} error
     */

    public static Boolean parseRefundable(String inputStr) throws IOException {
        try {
            return Boolean.parseBoolean(inputStr);
        } catch (Exception e) {
            repeatInput();
            return parseRefundable(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getPrice()} from {@link String}
     * @param inputStr {@link String}
     * @return price int
     * @throws IOException in case of some {@link #input} error
     */

    public static int parsePrice(String inputStr) throws IOException{
        try {
            int price = Integer.parseInt(inputStr);
            if(AbstractTicket.priceValid(price))
                return price;
            else
                throw new InvalidPriceException();
        } catch (Exception e) {
            repeatInput();
            return parsePrice(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Coordinates#getY()} from {@link String}
     * @param inputStr {@link String}
     * @return y {@link Integer}
     * @throws IOException in case of some {@link #input} error
     */

    public static Integer parseY(String inputStr) throws IOException {
        try {
            Integer y = Integer.parseInt(inputStr);
            if(Coordinates.yCoordinateValid(y))
                return y;
            else
                throw new InvalidYCoordinateException();
        } catch (Exception e) {
            repeatInput();
            return parseY(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getId()} from {@link String}
     * @param inputStr {@link String}
     * @return id int
     * @throws IOException in case of some {@link #input} error
     */

    public static int parseId(String inputStr) throws IOException {
        try {
            int id = Integer.parseInt(inputStr);
            if(AbstractTicket.idValid(id))
                return id;
            else
                throw new InvalidIdException();
        } catch (Exception e) {
            repeatInput();
            return parseId(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getType()} from {@link String}
     * @param inputStr {@link String}
     * @return type {@link TicketType}
     * @throws IOException in case of some {@link #input} error
     */

    public static TicketType parseType(String inputStr) throws IOException {
        try {
            return AbstractTicket.castType(inputStr);
        } catch (Exception e) {
            repeatInput();
            return parseType(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getDiscount()} from {@link String}
     * @param inputStr {@link String}
     * @return discount {@link double}
     * @throws IOException in case of some {@link #input} error
     */

    public static double parseDiscount(String inputStr) throws IOException {
        try {
            double discount = Double.parseDouble(inputStr);
            if(AbstractTicket.discountValid(discount))
                return discount;
            else
                throw new InvalidDiscountException();
        } catch (Exception e) {
            repeatInput();
            return parseDiscount(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Person#getWeight()}} from {@link String}
     * @param inputStr {@link String}
     * @return weight {@link Long}
     * @throws IOException in case of some {@link #input} error
     */

    public static Long parseWeight(String inputStr) throws IOException {
        try {
            Long weight = Long.parseLong(inputStr);
            if(Person.weightValid(weight))
                return weight;
            else
                throw new InvalidWeightException();
        } catch (Exception e) {
            repeatInput();
            return parseWeight(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Person#getEyeColor()} ()} from {@link String}
     * @param inputStr {@link String}
     * @return eyesColor {@link EyesColor}
     * @throws IOException in case of some {@link #input} error
     */

    public static EyesColor parseEyesColor(String inputStr) throws IOException {
        try {
            return Person.castEyesColor(inputStr);
        } catch (Exception e) {
            repeatInput();
            return parseEyesColor(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Person#getHairColor()} ()} from {@link String}
     * @param inputStr {@link String}
     * @return hairColor {@link HairColor}
     * @throws IOException in case of some {@link #input} error
     */

    public static HairColor parseHairColor(String inputStr) throws IOException {
        try {
            return Person.castHairColor(inputStr);
        } catch (Exception e) {
            repeatInput();
            return parseHairColor(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Person#getHairColor()} ()} from {@link String}
     * @param inputStr {@link String}
     * @return hairColor {@link HairColor}
     * @throws IOException in case of some {@link #input} error
     */

    private static Country parseCountry(String inputStr) throws IOException {
        try {
            return Person.castCountry(inputStr);
        } catch (Exception e) {
            repeatInput();
            return parseCountry(input.readLine());
        }
    }

    /**
     * Offers to repeat the input of the value
     */

    public static void repeatInput() {
        if(inputIsFromFile)
            throw new InvalidTicketException();
        else
            System.err.println("Error. Try to enter the field again:");
    }
}
