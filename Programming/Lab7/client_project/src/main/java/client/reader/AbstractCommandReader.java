package client.reader;

import client.Main;
import client.ObjectFactory;
import client.connection.ConnectionManager;
import client.exceptions.CommandInputException;
import client.exceptions.ConnectionException;
import client.messages.Messenger;
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
            Main.getErr().println(new ConnectionException().accept(Main.getExceptionMessenger()));
        } catch (Exception e) {
            Main.getErr().println(new CommandInputException().accept(Main.getExceptionMessenger()));
            LogManager.getLogger().error(e.getStackTrace());
        }
    }

    protected abstract boolean readyForInput() throws IOException;

    protected void printInputInvitation(){}
}
