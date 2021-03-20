package client.messages;

import org.apache.logging.log4j.LogManager;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class DefaultErrorPrinter implements ErrorPrinter{

    private final BufferedWriter errorStream;

    public DefaultErrorPrinter(OutputStream errorStream) {
        this.errorStream = new BufferedWriter(new OutputStreamWriter(errorStream));
    }


    public void println(String s) {
        print(s + "\n");
    }

    public void print(String s) {
        try {
            errorStream.write(s);
            errorStream.flush();
        } catch (IOException e) {
            System.err.println("Error. Can't access error output stream to write another error message.");
            LogManager.getLogger().error("Error. Can't access error output stream to write another error message.");
        }
    }
}
