package client.connection;

import client.reader.CommandReader;

import java.io.IOException;

public interface RequestFactory {
    void executeCommand(String command, CommandReader commandReader, String arg) throws IOException;
}
