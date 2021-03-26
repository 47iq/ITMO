package common;

import java.util.List;

public interface Response {
    boolean isSuccessful();

    void setMessage(String message);

    String getMessage();

    List<Ticket> getCollection();

    void setCollection(List<Ticket> tickets);

    void setInfo(String[] info);

    String[] getInfo();
}
