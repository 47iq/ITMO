package main;

import main.ticket.Ticket;

import java.util.HashMap;
import java.util.Map;

/**
 * Abstract class for messenger realization
 */

public abstract class AbstractMessenger implements Messenger {

    protected Map<String, String> translations = new HashMap<>();

    protected Map<String, String> commands = new HashMap<>();

    /**
     * Returns localized user output of the ticket in string format
     * @param ticket ticket we want to convert
     * @return ticket in string format
     */

    public String getTicketMessage(Ticket ticket) {
        String message = "";
        message += getFieldMessage("id", ticket.getId());
        message += getFieldMessage("name", ticket.getName());
        message += getFieldMessage("x", ticket.getCoordinates().getX());
        message += getFieldMessage("y", ticket.getCoordinates().getY());
        message += getFieldMessage("creation_date", ticket.getCreationDate());
        message += getFieldMessage("price", ticket.getPrice());
        message += getFieldMessage("discount", ticket.getDiscount());
        message += getFieldMessage("refundable", ticket.getRefundable());
        message += getFieldMessage("type", ticket.getType());
        message += getFieldMessage("weight", ticket.getPerson().getWeight());
        message += getFieldMessage("eye_color", ticket.getPerson().getEyeColor());
        message += getFieldMessage("hair_color", ticket.getPerson().getHairColor());
        message += getFieldMessage("nationality", ticket.getPerson().getNationality());
        return message;
    }

    /**
     * Returns localized information about existing commands in string format
     * @return information about the commands
     */

    public String getCommandsMessages() {
        StringBuilder message = new StringBuilder();
        for (String command: commands.keySet())
            message.append(getCommandMessage(command));
        return message.toString() + "\n" + getCommandsMessageEnding();
    }

    protected String getFieldMessage(String field, Object value) {
        return translations.get(field) + ": " + value + "\n";
    }

    protected String getCommandMessage(String command) {
        return  command + ": " + commands.get(command) +".\n";
    }

    protected abstract void setTicketFieldTranslations();

    protected abstract void setCommandTranslations();

    protected abstract String getCommandsMessageEnding();
}
