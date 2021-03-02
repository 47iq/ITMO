package client.reader;

import client.ObjectFactory;
import client.connection.RequestFactory;
import client.messages.Messenger;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.net.ConnectException;
import java.util.NoSuchElementException;

public abstract class AbstractCommandReader implements CommandReader{

    protected ObjectFactory ticketFactory;

    protected BufferedReader reader;

    protected RequestFactory commandFactory;

    protected Messenger messenger;

    public final void readCommands() {
        printInputInvitation();
        try {
            if (readyForInput()) {
                String[] command = reader.readLine().trim().split("\\s+");
                if (command.length == 1)
                    commandFactory.executeCommand(command[0], this, null);
                else
                    commandFactory.executeCommand(command[0], this, command[1]);
            }
        } catch (EOFException | NoSuchElementException | NullPointerException ignored) {
        } catch (ConnectException ex) {
            System.err.println("Server is temporary unavailable.");
        } catch (Exception e) {
            if (e.getMessage() != null) {
                System.err.println(e.getMessage());
            } else
                System.err.println("Error got while getting command.");
        }
    }

    protected abstract boolean readyForInput() throws IOException;

    protected void printInputInvitation(){}
}
