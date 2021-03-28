package server.datawork;

import common.UpdateData;
import server.ticket.ServerTicket;

import java.sql.SQLException;

public interface TicketsDataBase extends TicketReader {
    void add(ServerTicket ticket) throws SQLException;

    void update(ServerTicket ticket, int id, String owner, UpdateData updateData) throws SQLException;

    void setCommonDataBase(CommonDataBase commonDataBase);

    void clear(String owner) throws SQLException;

    void remove(int id, String owner) throws SQLException;
}
