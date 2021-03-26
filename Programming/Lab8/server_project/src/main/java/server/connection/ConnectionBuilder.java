package server.connection;

import java.io.IOException;
import java.net.Socket;

public interface ConnectionBuilder {
    Socket accept() throws IOException;

    void stop();
}
