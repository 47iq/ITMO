package client.messages;

import org.apache.logging.log4j.LogManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class DefaultPrinter implements Printer {

    private final BufferedWriter infoStream;

    public DefaultPrinter(OutputStream infoStream) {
        this.infoStream = new BufferedWriter(new OutputStreamWriter(infoStream));
    }

    public void print(String info) {
        try {
            infoStream.write(info);
            infoStream.flush();
        } catch (IOException e) {
            System.err.println("Error. Can't access output stream to write message.");
            LogManager.getLogger().error("Error. Can't access output stream to write message.");
        }
    }

    public void println(String info) {
        print(info + "\n");
    }
}
