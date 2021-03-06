package server.connection;

import common.Response;

import java.io.IOException;
import java.net.Socket;

public interface ResponseSender {
    void sendResponse(Response response, Socket client) throws IOException;
}
