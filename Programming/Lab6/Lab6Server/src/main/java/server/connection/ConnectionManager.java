package server.connection;

import java.nio.channels.Selector;

public interface ConnectionManager {
    void manageConnection(Selector selector);
}
