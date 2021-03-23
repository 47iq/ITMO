package common;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class DefaultResponse implements Response, Serializable {

    @Serial
    private static final long serialVersionUID = 1000L;

    private final boolean successful;

    private final String message;

    private List<Ticket> tickets;

    public DefaultResponse(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public List<Ticket> getCollection() {
        return tickets;
    }

    @Override
    public void setCollection(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
