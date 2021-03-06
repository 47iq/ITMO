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

    User getUser();

    void setUser(User user);

    void setType(RequestType type);

    UpdateData getUpdateData();

    void setUpdateData(UpdateData data);
}
