package client.reader;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import common.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ConnectException;

public abstract class AbstractCommandReader implements CommandReader {

    protected ObjectFactory ticketFactory;

    protected BufferedReader reader;

    protected ConnectionManager commandFactory;

    public final void executeNext() {
        try {
            String command = reader.readLine();
            getResponse(command);
        } catch (IOException e) {
            ticketFactory.getResponse(false, "ERR_UNK");
        }
    }

    public final Response getResponse(String command1) {
        printInputInvitation();
        try {
            if (readyForInput()) {
                String[] command = command1.trim().split("\\s+");
                if (command.length == 3) {
                    command[1] += " " + command[2];
                }
                if (command.length == 1)
                    return commandFactory.executeCommand(command[0], this, null);
                else
                    return commandFactory.executeCommand(command[0], this, command[1]);
            }
        } catch (ConnectException ex) {
            return ticketFactory.getResponse(false, "ERR_CONNECTION");
        } catch (Exception e) {
            e.printStackTrace();
            return ticketFactory.getResponse(false, "ERR_UNK");
        }
        return ticketFactory.getResponse(false, "ERR_UNK");
    }

    protected abstract boolean readyForInput() throws IOException;

    protected void printInputInvitation() {
    }
}
