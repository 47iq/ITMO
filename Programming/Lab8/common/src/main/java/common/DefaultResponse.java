package common;

import java.awt.*;
import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class DefaultResponse implements Response, Serializable {

    @Serial
    private static final long serialVersionUID = 1000L;

    private final boolean successful;

    private String message;

    private List<Ticket> tickets;

    private String[] info;

    private Color color;

    private Map<String, Color> colorMap;

    public DefaultResponse(boolean successful, String message) {
        this.successful = successful;
        this.message = message;
    }

    @Override
    public boolean isSuccessful() {
        return successful;
    }

    @Override
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

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public Map<String, Color> getColorMap() {
        return colorMap;
    }

    @Override
    public void setColorMap(Map<String, Color> colorMap) {
        this.colorMap = colorMap;
    }

    @Override
    public String toString() {
        return "DefaultResponse{" +
                "successful=" + successful +
                ", message='" + message + '\'' +
                ", tickets=" + tickets +
                ", info=" + Arrays.toString(info) +
                ", color=" + color +
                ", colorMap=" + colorMap +
                '}';
    }
}
