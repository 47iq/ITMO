package client.reader;

import client.Main;
import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.exceptions.CommandInputException;
import client.exceptions.ConnectionException;
import client.messages.Messenger;
import common.Request;
import common.Response;
import org.apache.logging.log4j.LogManager;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.util.NoSuchElementException;

public abstract class AbstractCommandReader implements CommandReader{

    protected ObjectFactory ticketFactory;

    protected BufferedReader reader;

    protected ConnectionManager commandFactory;

    protected Messenger messenger;

    public final Response executeNext() {
        try {
            String command = reader.readLine();
            return getResponse(command);
        } catch (IOException e) {
            //FIXME
            return ticketFactory.getResponse(false, "IOException");
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
