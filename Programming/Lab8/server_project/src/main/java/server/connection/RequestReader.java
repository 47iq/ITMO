package server.connection;

import common.Request;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;

public interface RequestReader {
    Request readRequest(Socket client) throws IOException, ClassNotFoundException;
}
