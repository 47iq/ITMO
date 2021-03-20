package server.datawork;

public interface DataBaseManager {
    void start(String url, String user, String password);
    TicketsDataBase getTicketsData();
    UsersDataBase getUsersData();
}
