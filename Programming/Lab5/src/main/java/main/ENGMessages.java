package main;

import main.ticket.Ticket;

/**
 * Abstract class which manages user-friendly output of {@link Ticket}
 * @autor 47iq
 * @version 1.0
 */

public class ENGMessages extends AbstractMessenger {

    public ENGMessages() {
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
        commands.put("add_if_max", "add given {Ticket} if it is bigger than any other manager.ticket of the collection");
        commands.put("update", "update {Ticket} with the given (id)");
        commands.put("save", "save the collection into the file");
        commands.put("remove_by_id", "remove manager.ticket with the given (id)");
        commands.put("remove_greater", "remove tickets bigger than given {Ticket}");
        commands.put("remove_first", "remove first manager.ticket from the collection");
        commands.put("max_by_coordinates", "get the manager.ticket, which is biggest by coordinates");
        commands.put("filter_greater_than_discount", "get elements, which have bigger (discount) than given");
        commands.put("print_field_descending_refundable", "get refundable fields of the elements sorted in descending order");
        commands.put("exit", "stop the program execution");
        commands.put("execute_script", "execute script from the given file by (file_name)");
        commands.put("clear", "clear the collection");
    }

    protected String getCommandsMessageEnding() {
        return "Notice that arguments \"(argument)\" must be entered in the same line as the command.";
    }

    public String getCollectionMessage(String type, String size, String creationDate) {
        return "Collection type: " + type + "\nCollection current size: " + size +"\nInitialization time: " + creationDate;
    }

    public String getNameInputInvitationMessage() {
        return "Enter ticket name:";
    }

    public String getXInputInvitationMessage() {
        return "Enter ticket x coordinate:";
    }

    public String getYInputInvitationMessage() {
        return "Enter ticket y coordinate:";
    }

    public String getDiscountInputInvitationMessage() {
        return "Enter ticket discount:";
    }

    public String getPriceInputInvitationMessage() {
        return "Enter ticket price:";
    }

    public String getRefundableInputInvitationMessage() {
        return "Enter ticket refundable: ";
    }

    public String getTicketTypeInputInvitationMessage() {
        return "Enter ticket type {VIP, CHEAP, USUAL}:";
    }

    public String getWeightInputInvitationMessage() {
        return "Enter ticket buyer's weight: ";
    }

    public String getEyeColorInputInvitationMessage() {
        return "Enter ticket buyer's eye color {BLACK, BLUE, YELLOW}: ";
    }

    public String getHairColorInputInvitationMessage() {
        return "Enter ticket buyer's hair color {BLACK, RED, GREEN, YELLOW}: ";
    }

    public String getCountryInputInvitationMessage() {
        return "Enter ticket buyer's nationality {RUSSIA, FRANCE, SPAIN, CHINA}: ";
    }
}
