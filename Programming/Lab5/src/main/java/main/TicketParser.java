package main;

import exceptions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Abstract class which parses {@link AbstractTicket} and its fields from {@link String}
 * @autor 47iq
 * @version 1.0
 */


public abstract class TicketParser {

    /**
     * Input stream
     */

    private static BufferedReader input;

    /**
     * Flag that shows if input is from file or not
     */

    private static boolean inputIsFromFile = false;

    /**
     * Input strem setter
     * @param reader input stream reader
     */

    public static void setInput(BufferedReader reader) {
        input = reader;
    }

    /**
     * Parses and returns {@link AbstractTicket} from {@link #input}. If input is from file, disables logging.
     * @return {@link AbstractTicket} ticket
     */

    public static AbstractTicket parseTicket(Boolean isFromFile) {
        inputIsFromFile = isFromFile;
        PrintStream original = System.out;
        try {
            if(inputIsFromFile) {
                System.setOut(new PrintStream(new OutputStream() {
                    public void write(int b) {
                        //DO NOTHING
                    }
                }));
            }
            System.out.println("Enter the ticket data.\nEnter the name:");
            String name = parseName(input.readLine());
            System.out.println("Enter coordinates.\nEnter x coordinate:");
            double xCoordinate = parseX(input.readLine());
            System.out.println("Enter y coordinate:");
            Integer yCoordinate = parseY(input.readLine());
            Coordinates coordinates = Main.getCoordinates(xCoordinate, yCoordinate);
            System.out.println("Enter the discount value:");
            double discount = parseDiscount(input.readLine());
            System.out.println("Enter the price:");
            int price = parsePrice(input.readLine());
            System.out.println("Enter the refundable:");
            Boolean refundable = parseRefundable(input.readLine());
            System.out.println("Enter the ticket type. Choose one of the following: VIP, CHEAP, USUAL");
            TicketType type = parseType(input.readLine());
            System.out.println("Enter the person data.\nEnter person's weight:");
            Long weight = parseWeight(input.readLine());
            System.out.println("Enter person's eyes color. Choose one of the following: BLACK, BLUE, YELLOW");
            EyesColor personEyesColor = parseEyesColor(input.readLine());
            System.out.println("Enter person's hair color. Choose one of the following: BLACK, GREEN, RED, YELLOW");
            HairColor personHairColor = parseHairColor(input.readLine());
            System.out.println("Enter person's nationality. Choose one of the following: RUSSIA, SPAIN, FRANCE, CHINA");
            Country country = parseCountry(input.readLine());
            Person person = Main.getPerson(weight, personEyesColor, personHairColor, country);
            return Main.getTicket(name, coordinates, price, discount, refundable, type, person);
        } catch (Exception e) {
            if(inputIsFromFile) {
                System.err.println();
            }
            return null;
        } finally {
            System.setOut(original);
            inputIsFromFile = false;
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getName()} from {@link String}
     * @param inputStr name in string format
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
            repeatInput(e);
            return parseName(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Coordinates#getX()} from {@link String}
     * @param inputStr x coordinate in string format
     * @return x double
     * @throws IOException in case of some {@link #input} error
     */

    public static double parseX(String inputStr) throws IOException {
        try {
            return Coordinates.castXCoordinate(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parseX(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getRefundable()} from {@link String}
     * @param inputStr refundable in string format
     * @return refundable {@link Boolean}
     * @throws IOException in case of some {@link #input} error
     */

    public static Boolean parseRefundable(String inputStr) throws IOException {
        try {
            if(inputStr.equals(""))
                return null;
            if(inputStr.equals("true"))
                return true;
            if(inputStr.equals("false"))
                return false;
            throw  new InvalidRefundableException();
        } catch (Exception e) {
            repeatInput(e);
            return parseRefundable(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getPrice()} from {@link String}
     * @param inputStr price in string format
     * @return price int
     * @throws IOException in case of some {@link #input} error
     */

    public static int parsePrice(String inputStr) throws IOException{
        try {
            return AbstractTicket.castPrice(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parsePrice(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Coordinates#getY()} from {@link String}
     * @param inputStr y coordinate in string format
     * @return y {@link Integer}
     * @throws IOException in case of some {@link #input} error
     */

    public static Integer parseY(String inputStr) throws IOException {
        try {
            return Coordinates.castYCoordinate(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parseY(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getId()} from {@link String}
     * @param inputStr id in string format
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
            repeatInput(e);
            return parseId(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getType()} from {@link String}
     * @param inputStr ticket type in string format
     * @return type {@link TicketType}
     * @throws IOException in case of some {@link #input} error
     */

    public static TicketType parseType(String inputStr) throws IOException {
        try {
            return AbstractTicket.castType(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parseType(input.readLine());
        }
    }

    /**
     * Parses and validates {@link AbstractTicket#getDiscount()} from {@link String}
     * @param inputStr discount in string format
     * @return discount {@link double}
     * @throws IOException in case of some {@link #input} error
     */

    public static double parseDiscount(String inputStr) throws IOException {
        try {
            return AbstractTicket.castDiscount(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parseDiscount(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Person#getWeight()}} from {@link String}
     * @param inputStr weight in string format
     * @return weight {@link Long}
     * @throws IOException in case of some {@link #input} error
     */

    public static Long parseWeight(String inputStr) throws IOException {
        try {
            return Person.castWeight(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parseWeight(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Person#getEyeColor()} ()} from {@link String}
     * @param inputStr eyesColor in string format
     * @return eyesColor {@link EyesColor}
     * @throws IOException in case of some {@link #input} error
     */

    public static EyesColor parseEyesColor(String inputStr) throws IOException {
        try {
            return Person.castEyesColor(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parseEyesColor(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Person#getHairColor()} ()} from {@link String}
     * @param inputStr hairColor in string format
     * @return hairColor {@link HairColor}
     * @throws IOException in case of some {@link #input} error
     */

    public static HairColor parseHairColor(String inputStr) throws IOException {
        try {
            return Person.castHairColor(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parseHairColor(input.readLine());
        }
    }

    /**
     * Parses and validates {@link Person#getHairColor()} ()} from {@link String}
     * @param inputStr country in string format
     * @return country {@link Country}
     * @throws IOException in case of some {@link #input} error
     */

    private static Country parseCountry(String inputStr) throws IOException {
        try {
            return Person.castCountry(inputStr);
        } catch (Exception e) {
            repeatInput(e);
            return parseCountry(input.readLine());
        }
    }

    /**
     * Displays input error and offers to repeat the input of the value
     */

    public static void repeatInput(Exception e) {
        if(e instanceof InvalidTicketFieldException)
            System.err.print(e.getMessage());
        else if(e instanceof NumberFormatException)
            System.err.print("Number format error got.");
        else
            System.err.println("Error.");
        if(inputIsFromFile)
            throw new InvalidTicketException();
        else
            System.err.println(" Try to enter the field again:");
    }
}
