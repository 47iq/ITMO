package client.commands;

import common.Response;
import common.Ticket;

import java.util.List;
import java.util.ResourceBundle;

public class ClientShowCommand implements MessagingCommand {
    public void execute(Response response) {
        List<Ticket> ticketList = response.getCollection();
        ResourceBundle bundle = getLocalResourceBundle();
        String message = "";
        for (Ticket ticket : ticketList) {
            message += bundle.getString("ID") + ": " + ticket.getId() + "\n" +
                    bundle.getString("OWNER") + ": " + ticket.getOwner() + "\n" +
                    bundle.getString("NAME") + ": " + ticket.getName() + "\n" +
                    bundle.getString("PRICE") + ": " + ticket.getPrice() + "\n" +
                    bundle.getString("DISCOUNT") + ": " + ticket.getDiscount() + "\n" +
                    bundle.getString("TYPE") + ": " + ticket.getType() + "\n" +
                    bundle.getString("DATE") + ": " + ticket.getCreationDate() + "\n" +
                    bundle.getString("REFUNDABLE") + ": " + ticket.getRefundable() + "\n" +
                    bundle.getString("X") + ": " + ticket.getX() + "\n" +
                    bundle.getString("Y") + ": " + ticket.getY() + "\n" +
                    bundle.getString("WEIGHT") + ": " + ticket.getWeight() + "\n" +
                    bundle.getString("EYES") + ": " + ticket.getEyeColor() + "\n" +
                    bundle.getString("HAIR") + ": " + ticket.getHairColor() + "\n" +
                    bundle.getString("REFUNDABLE") + ": " + ticket.getRefundable() + "\n" +
                    bundle.getString("NATIONALITY") + ": " + ticket.getNationality() + "\n\n";
        }
        response.setMessage(message);
    }
}
