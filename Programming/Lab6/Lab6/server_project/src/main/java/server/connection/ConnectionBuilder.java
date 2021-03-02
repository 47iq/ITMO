package server.connection;

import java.io.IOException;
import java.nio.channels.Selector;

public interface ConnectionBuilder {
    Selector accept() throws IOException;
}
