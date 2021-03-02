package client.connection;

import common.Request;

import java.io.IOException;

public interface RequestSender {
    void sendRequest(Request request) throws IOException;
}
