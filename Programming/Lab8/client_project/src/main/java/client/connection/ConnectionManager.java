package client.connection;

import client.reader.CommandReader;
import common.Response;
import common.Ticket;
import common.User;

import java.io.IOException;
import java.util.Collection;
import java.util.concurrent.ExecutionException;

public interface ConnectionManager {
    Response executeCommand(String command, CommandReader commandReader, String arg) throws IOException;
    //Collection<Ticket> getCollection() throws InterruptedException, ExecutionException;
    void setUser(User user);

    Collection<Ticket> getCollection() throws InterruptedException, ExecutionException;
}
