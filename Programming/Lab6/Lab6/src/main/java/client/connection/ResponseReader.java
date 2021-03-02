package client.connection;

import common.Response;

import java.io.IOException;

public interface ResponseReader {
    Response readResponse() throws IOException, ClassNotFoundException;
}
