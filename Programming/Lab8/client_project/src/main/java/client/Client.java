package client;

import client.reader.CommandReader;

public interface Client {
    void start(CommandReader commandReader);

    void stop();
}
