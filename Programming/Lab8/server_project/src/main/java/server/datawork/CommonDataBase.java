package server.datawork;

import java.sql.SQLException;

public interface CommonDataBase{
   String getOwner(int ticketId);
    void add(int id, String owner) throws SQLException;
}
