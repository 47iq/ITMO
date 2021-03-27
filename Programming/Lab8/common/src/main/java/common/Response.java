package common;

import java.awt.*;
import java.util.List;
import java.util.Map;

public interface Response {
    boolean isSuccessful();

    void setMessage(String message);

    String getMessage();

    List<Ticket> getCollection();

    void setCollection(List<Ticket> tickets);

    void setInfo(String[] info);

    String[] getInfo();

    void setColor(Color color);

    Color getColor();

    void setColorMap(Map<String, Color> colorMap);

    Map<String, Color> getColorMap();
}
