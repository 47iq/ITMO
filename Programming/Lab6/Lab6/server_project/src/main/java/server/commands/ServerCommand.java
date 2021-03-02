package server.commands;

import server.collection.CollectionManager;

public interface ServerCommand extends Command{
    void execute(CollectionManager manager);
}
