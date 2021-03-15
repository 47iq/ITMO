package server.connection;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 * Interface of command reader
 */

public interface Server {

    /**
     * Read commands
     */

    void start() throws IOException, ExecutionException, InterruptedException;

    void exit();
}
