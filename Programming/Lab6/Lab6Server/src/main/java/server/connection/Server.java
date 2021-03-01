package server.connection;

import java.io.IOException;

/**
 * Interface of command reader
 */

public interface Server {

    /**
     * Read commands
     */

    void readCommands() throws IOException;

    void exit();
}
