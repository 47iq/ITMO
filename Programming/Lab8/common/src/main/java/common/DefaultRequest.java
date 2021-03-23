package common;

import java.io.Serial;
import java.io.Serializable;
import java.util.Locale;

public class DefaultRequest implements Request, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private RequestType type;

    private Ticket ticket;

    private Locale locale;

    private String arg;

    private User user;

    private UpdateData updateData;

    private final String commandName;

    public DefaultRequest(RequestType type, String commandName, Locale locale) {
        this.commandName = commandName;
        this.type = type;
        this.locale = locale;
    }

    public UpdateData getUpdateData() {
        return updateData;
    }

    public void setUpdateData(UpdateData updateData) {
        this.updateData = updateData;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public String getArg() {
        return arg;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public void setArg(String arg) {
        this.arg = arg;
    }

    public RequestType getType() {
        return type;
    }

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setType(RequestType type) {
        this.type = type;
    }
}
