package common;

import java.util.List;

public interface Response {
    boolean isSuccessful();
    String getMessage();
    List<Ticket> getCollection();
    void setCollection(List<Ticket> tickets);
}
