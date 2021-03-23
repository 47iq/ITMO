package client.reader;

import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.messages.Messenger;
import common.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.ConnectException;

public abstract class AbstractCommandReader implements CommandReader{

    protected ObjectFactory ticketFactory;

    protected BufferedReader reader;

    protected ConnectionManager commandFactory;

    protected Messenger messenger;

    public final void executeNext() {
        try {
            String command = reader.readLine();
            getResponse(command);
        } catch (IOException e) {
            //FIXME
            ticketFactory.getResponse(false, "IOException");
        }
    }

    public final Response getResponse(String command1) {
        printInputInvitation();
        try {
            if (readyForInput()) {
                String[] command = command1.trim().split("\\s+");
                if(command.length == 3) {
                    command[1] += " " + command[2];
                }
                if (command.length == 1)
                    return commandFactory.executeCommand(command[0], this, null);
                else
                    return commandFactory.executeCommand(command[0], this, command[1]);
            }
        } catch (ConnectException ex) {
            //FIXME
            return ticketFactory.getResponse(false, "Connect Error");
        } catch (Exception e) {
            //FIXME
            return ticketFactory.getResponse(false, "Unknown Error");
        }
        //FIXME
        return ticketFactory.getResponse(false,"Unknown error");
    }

    protected abstract boolean readyForInput() throws IOException;

    protected void printInputInvitation(){}
}
