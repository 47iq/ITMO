package server.connection;

import common.Request;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Selector;

public interface RequestReader {
    Request readRequest(Selector selector, ByteBuffer buffer) throws IOException, ClassNotFoundException;
}
