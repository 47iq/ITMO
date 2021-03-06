package client;

import client.reader.CommandReader;

public class DefaultClient implements Client {

    private boolean isRunning = true;

    @Override
    public void start(CommandReader commandReader) {
        while (isRunning)
            commandReader.executeNext();
    }

    @Override
    public void stop() {
        isRunning = false;
    }
}
