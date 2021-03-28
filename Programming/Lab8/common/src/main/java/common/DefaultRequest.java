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

    @Override
    public UpdateData getUpdateData() {
        return updateData;
    }

    @Override
    public void setUpdateData(UpdateData updateData) {
        this.updateData = updateData;
    }

    @Override
    public Ticket getTicket() {
        return ticket;
    }

    @Override
    public String getArg() {
        return arg;
    }

    @Override
    public String getCommandName() {
        return commandName;
    }

    @Override
    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    @Override
    public void setArg(String arg) {
        this.arg = arg;
    }

    @Override
    public RequestType getType() {
        return type;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void setType(RequestType type) {
        this.type = type;
    }
}
