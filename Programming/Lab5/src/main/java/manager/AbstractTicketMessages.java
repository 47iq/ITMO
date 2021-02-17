package manager;

import ticket.Ticket;

import java.util.HashMap;
import java.util.Map;

public abstract class AbstractTicketMessages implements TicketMessenger{

    protected Map<String, String> translations = new HashMap<>();

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

    protected String getFieldMessage(String field, Object value) {
        return translations.get(field) + ": " + value + "\n";
    }

    protected abstract void setTranslations();
}
