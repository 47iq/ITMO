package server.connection;

import common.Request;

import java.io.IOException;
import java.net.Socket;

public interface RequestReader {
    Request readRequest(Socket client) throws IOException, ClassNotFoundException;
}
