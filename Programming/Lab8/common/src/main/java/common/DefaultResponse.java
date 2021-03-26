package common;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class DefaultResponse implements Response, Serializable {

    @Serial
    private static final long serialVersionUID = 1000L;

    private final boolean successful;

    private String message;

    private List<Ticket> tickets;

    private String[] info;

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

    @Override
    public String[] getInfo() {
        return info;
    }

    @Override
    public void setInfo(String[] info) {
        this.info = info;
    }

    @Override
    public void setMessage(String message) {
        this.message = message;
    }
}
