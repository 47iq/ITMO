package common;

import java.io.Serializable;
import java.util.Locale;

public class DefaultRequest implements Request, Serializable {

    private static final long serialVersionUID = 1L;

    private final RequestType type;

    private Ticket ticket;

    private Locale locale;

    private String arg;

    protected String password;

    private final String commandName;

    public DefaultRequest(RequestType type, String commandName, Locale locale) {
        this.commandName = commandName;
        this.type = type;
        this.locale = locale;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
