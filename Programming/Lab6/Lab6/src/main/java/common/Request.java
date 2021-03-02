package common;

import java.util.Locale;

public interface Request {
    Ticket getTicket();

    String getArg();

    String getCommandName();

    void setArg(String arg);

    void setTicket(Ticket ticket);

    RequestType getType();

    Locale getLocale();

    void setLocale(Locale locale);
}
