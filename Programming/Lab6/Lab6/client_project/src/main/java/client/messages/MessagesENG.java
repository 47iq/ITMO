package client.messages;

/**
 * Class of English localization of {@link Messenger}
 * @autor 47iq
 * @version 1.0
 */

public class MessagesENG extends ClientMessenger {

    public MessagesENG() {
        setCommandTranslations();
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

    protected String getCommandsMessageEnding() {
        return "Notice that arguments \"(argument)\" must be entered in the same line as the command.";
    }

    protected void setCommandTranslations() {
        commands.put("execute_script", "execute script from (file)");
        commands.put("exit", "exit the program");
    }
}
