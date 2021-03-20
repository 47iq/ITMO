package client.reader;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import common.Response;
import common.Ticket;
import common.User;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class DefaultControllerCommandReader extends AbstractCommandReader {

    private Response response;

    public DefaultControllerCommandReader(ConnectionManager commandFactory, ObjectFactory ticketFactory)  {
        // FIXME reader= ;
        super.ticketFactory = ticketFactory;
        super.commandFactory = commandFactory;
    }

    protected boolean readyForInput() throws IOException {
        return true;
    }
}
