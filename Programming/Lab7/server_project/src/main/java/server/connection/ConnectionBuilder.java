package server.connection;

import java.io.IOException;
import java.net.Socket;
import java.nio.channels.Selector;

public interface ConnectionBuilder {
    Socket accept() throws IOException;
    void stop();
}
