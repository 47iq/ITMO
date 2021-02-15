package main;

import exceptions.InputFileNotFoundException;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class which starts the program
 * @autor 47iq
 * @version 1.0
 */

public class Main {

    /**
     * The entry point of the program
     * @param args String[] args
     */

    public static void main(String[] args) {
        //Scanner scanner = new Scanner(System.in);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String fileName;
        try {
            if(args.length == 0)
                throw new InputFileNotFoundException();
            fileName = args[0];
            CollectionManager taskManager = new QueueManager(fileName);
            CommandFactory.setTaskManager(taskManager);
            setReader(input);
            taskManager.start();
        } catch (Exception e) {
            if(e.getMessage() != null)
                System.err.println(e.getMessage());
            System.err.println("Error got while executing the program.");
        }
    }

    public static void setReader(BufferedReader input) {
        CommandFactory.setInput(input);
        TicketParser.setInput(input);
    }

    public static AbstractTicket getTicket(JSONObject jsonTicket) {
        return new Ticket(jsonTicket);
    }

    public static AbstractTicket getTicket(String name, Coordinates coordinates, int price, double discount, Boolean refundable, TicketType type, Person person) {
        return new Ticket(name, coordinates, price, discount, refundable, type, person);
    }

    public static Coordinates getCoordinates(double x, Integer y) {
        return new Coordinates(x, y);
    }

    public static Coordinates getCoordinates(JSONObject jsonCoordinates) {
        return new Coordinates(jsonCoordinates);
    }

    public static Person getPerson(Long weight, EyesColor eyesColor, HairColor hairColor, Country country) {
        return new Person(weight, eyesColor, hairColor, country);
    }

    public static Person getPerson(JSONObject jsonPerson) {
        return new Person(jsonPerson);
    }
}

