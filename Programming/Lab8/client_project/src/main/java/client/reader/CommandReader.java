package client.reader;

import common.Response;
import common.Ticket;
import common.User;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Interface of command reader
 */

public interface CommandReader {

    Response executeNext();
    Response getResponse(String command);
}
