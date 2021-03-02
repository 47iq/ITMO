package server.messages;

import common.Ticket;

/**
 * Abstract class which manages user-friendly output of {@link Ticket}
 * @autor 47iq
 * @version 1.0
 */

public class MessagesENG extends ServerMessenger {

    public MessagesENG() {
        setTicketFieldTranslations();
        setCommandTranslations();
    }

    protected void setTicketFieldTranslations() {
        translations.put("id", "ID");
        translations.put("name", "Name");
        translations.put("x", "X coordinate");
        translations.put("y", "Y coordinate");
        translations.put("creation_date", "Creation time");
        translations.put("price", "Price");
        translations.put("discount", "Discount");
        translations.put("refundable", "Refundable");
        translations.put("type", "Ticket type");
        translations.put("weight", "Person's weight");
        translations.put("eye_color", "Person's eye color");
        translations.put("hair_color", "Person's hair color");
        translations.put("nationality", "Person's nationality");
    }

    protected void setCommandTranslations() {
        commands.put("help", "get information about the commands");
        commands.put("info", "get information about the collection");
        commands.put("show", "get the collection's elements");
        commands.put("add", "add {Ticket} to the collection");
        commands.put("add_if_max", "add given {Ticket} if it is bigger than any other ticket of the collection");
        commands.put("update", "update {Ticket} with the given (id)");
        commands.put("save", "save the collection into the file");
        commands.put("remove_by_id", "remove ticket with the given (id)");
        commands.put("remove_greater", "remove tickets bigger than given {Ticket}");
        commands.put("remove_first", "remove first ticket from the collection");
        commands.put("max_by_coordinates", "get the ticket, which is biggest by coordinates");
        commands.put("filter_greater_than_discount", "get elements, which have bigger (discount) than given");
        commands.put("print_field_descending_refundable", "get refundable fields of the elements sorted in descending order");
        commands.put("exit", "stop the program execution");
        commands.put("clear", "clear the collection");
    }

    public String getCollectionMessage(String type, String size, String creationDate) {
        return "Collection type: " + type + "\nCollection current size: " + size +"\nInitialization time: " + creationDate + "\n";
    }
}
