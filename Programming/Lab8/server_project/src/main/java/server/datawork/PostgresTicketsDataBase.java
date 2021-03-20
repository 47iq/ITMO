package server.datawork;

import common.Coordinates;
import common.DefaultPerson;
import common.Person;
import common.Ticket;
import org.apache.logging.log4j.LogManager;
import server.ServerObjectFactory;
import server.ticket.ServerTicket;

import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;

public class PostgresTicketsDataBase implements TicketsDataBase {

    private final Connection connection;

    private final Statement statement;

    private final ServerObjectFactory factory;

    public PostgresTicketsDataBase(Connection connection, ServerObjectFactory factory) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        this.factory = factory;
        create();
    }

    public void update(ServerTicket ticket, int id, String owner) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE tickets SET owner = ?, name = ?, " +
                "coordinates_x = ?, coordinates_y = ?, creationDate = ?, price = ? , discount = ?, refundable = ?, type = ?, " +
                "person_weight = ?, person_eyes = ?, person_hair = ?, person_nation = ?, id = ? WHERE id = ?");
        updateStatement(ticket, preparedStatement);
        preparedStatement.setInt(14, ticket.getId());
        preparedStatement.setInt(15, ticket.getId());
        preparedStatement.execute();
    }

    public void clear(String owner) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("DELETE FROM TICKETS WHERE OWNER = ?");
        sql.setString(1, owner);
        sql.execute();
    }

    public void remove(int id, String owner) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("DELETE FROM TICKETS WHERE id = ? AND owner = ?");
        sql.setInt(1, id);
        sql.setString(2, owner);
        sql.execute();
    }

    private void create() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS  tickets (" +
                "id serial primary key not null," +
                "owner TEXT NOT NULL," +
                "name TEXT NOT NULL," +
                "coordinates_x DOUBLE PRECISION NOT NULL," +
                "coordinates_y DOUBLE PRECISION NOT NULL," +
                "creationDate TEXT NOT NULL," +
                "price INTEGER NOT NULL," +
                "discount DOUBLE PRECISION NOT NULL," +
                "refundable BOOLEAN," +
                "type TEXT," +
                "person_weight INTEGER," +
                "person_eyes TEXT NOT NULL," +
                "person_hair TEXT NOT NULL," +
                "person_nation TEXT NOT NULL)";
        statement.execute(create);
    }

    public void add(ServerTicket ticket) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tickets (owner, name, coordinates_x, " +
                "coordinates_y, creationDate, price, discount, refundable, type, " +
                "person_weight, person_eyes, person_hair, person_nation, id)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,DEFAULT)", Statement.RETURN_GENERATED_KEYS);
        updateStatement(ticket, preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet set = preparedStatement.getGeneratedKeys();
        if (set.next()) {
            ticket.setId(set.getInt(set.findColumn("id")));
        }
        else {
            throw new SQLException();
        }
    }

    private void updateStatement(ServerTicket ticket, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, ticket.getOwner());
        preparedStatement.setString(2, ticket.getName());
        preparedStatement.setDouble(3, ticket.getCoordinates().getX());
        preparedStatement.setInt(4, ticket.getCoordinates().getY());
        preparedStatement.setString(5, ticket.getCreationDate().toString());
        preparedStatement.setInt(6, ticket.getPrice());
        preparedStatement.setDouble(7, ticket.getDiscount());
        if(ticket.getRefundable() == null)
            preparedStatement.setNull(8, Types.BOOLEAN);
        else
            preparedStatement.setBoolean(8, ticket.getRefundable());
        if(ticket.getType() == null)
            preparedStatement.setNull(9, Types.VARCHAR);
        else
            preparedStatement.setString(9, ticket.getType().toString());
        if(ticket.getPerson().getWeight() == null)
            preparedStatement.setNull(10, Types.INTEGER);
        else
            preparedStatement.setLong(10, ticket.getPerson().getWeight());
        preparedStatement.setString(11, ticket.getPerson().getEyeColor().toString());
        preparedStatement.setString(12, ticket.getPerson().getHairColor().toString());
        preparedStatement.setString(13, ticket.getPerson().getNationality().toString());
    }

    public Collection<ServerTicket> getTickets() {
        try {
            String query = " SELECT * FROM tickets ORDER by id;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Collection<ServerTicket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                ServerTicket ticket = factory.getServerTicket();
                ticket.setId(resultSet.getInt(1));
                ticket.setOwner(resultSet.getString(2));
                ticket.setName(resultSet.getString(3));
                Coordinates coordinates = factory.getCoordinates();
                coordinates.setX(resultSet.getDouble(4));
                coordinates.setY(resultSet.getInt(5));
                ticket.setCoordinates(coordinates);
                ticket.setDateStr(resultSet.getString(6));
                ticket.setPrice(resultSet.getInt(7));
                ticket.setDiscount(resultSet.getDouble(8));
                Object obj = resultSet.getObject(9);
                if(obj == null)
                    ticket.setRefundable(null);
                else
                    ticket.setRefundable((Boolean) obj);
                ticket.setTypeStr((String) resultSet.getObject(10));
                DefaultPerson person = factory.getPerson();
                Integer buffer = (Integer) resultSet.getObject(11);
                if(buffer == null)
                    person.setWeight(null);
                else
                    person.setWeight(Long.valueOf(buffer));
                person.setEyeColorStr(resultSet.getString(12));
                person.setHairColorStr(resultSet.getString(13));
                person.setNationalityStr(resultSet.getString(14));
                ticket.setPerson(person);
                tickets.add(ticket);
            }
            return tickets;
        } catch (Exception e) {
            e.printStackTrace();
            LogManager.getLogger().error("Can't load tickets from data base.");
            System.err.println("Can't load tickets from data base.");
            System.exit(1);
            return null;
        }
    }
}
