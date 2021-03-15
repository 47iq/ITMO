package client.connection;

import client.reader.CommandReader;
import common.User;

import java.io.IOException;

public interface ConnectionManager {
    void executeCommand(String command, CommandReader commandReader, String arg) throws IOException;
    void setUser(User user);
}
