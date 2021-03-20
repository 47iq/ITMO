package client;

import client.reader.CommandReader;

public class DefaultClient implements Client {

    private boolean isRunning = true;

    public void start(CommandReader commandReader) {
        while (isRunning)
            commandReader.executeNext();
    }

    public void stop() {
        isRunning = false;
    }
}
