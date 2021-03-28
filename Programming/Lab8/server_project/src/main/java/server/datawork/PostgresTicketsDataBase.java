package server.datawork;

import common.Coordinates;
import common.DefaultPerson;
import common.UpdateData;
import org.apache.logging.log4j.LogManager;
import server.ServerObjectFactory;
import server.ticket.ServerTicket;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;

public class PostgresTicketsDataBase implements TicketsDataBase {

    private final Connection connection;

    private final Statement statement;

    private final ServerObjectFactory factory;

    private CommonDataBase commonDataBase;


    public PostgresTicketsDataBase(Connection connection, ServerObjectFactory factory) throws SQLException {
        this.connection = connection;
        this.statement = connection.createStatement();
        this.factory = factory;
        create();
    }

    public void setCommonDataBase(CommonDataBase commonDataBase) {
        this.commonDataBase = commonDataBase;
    }

    @Override
    public void update(ServerTicket ticket, int id, String owner, UpdateData updateData) throws SQLException {
        if(ticket.getOwner().equals(commonDataBase.getOwner(id))) {
            int index = 1;
            String sql = prepareString(updateData);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(index, id);
            index++;
            if (updateData.isNameSelected()) {
                preparedStatement.setString(index, ticket.getName());
                index++;
            }
            if (updateData.isXSelected()) {
                preparedStatement.setDouble(index, ticket.getCoordinates().getX());
                index++;
            }
            if (updateData.isYSelected()) {
                preparedStatement.setInt(index, ticket.getCoordinates().getY());
                index++;
            }
            if (updateData.isPriceSelected()) {
                preparedStatement.setInt(index, ticket.getPrice());
                index++;
            }
            if (updateData.isDiscountSelected()) {
                preparedStatement.setDouble(index, ticket.getDiscount());
                index++;
            }
            if (updateData.isRefundableSelected()) {
                if (ticket.getRefundable() == null)
                    preparedStatement.setNull(index, Types.BOOLEAN);
                else
                    preparedStatement.setBoolean(index, ticket.getRefundable());
                index++;
            }
            if (updateData.isTypeSelected()) {
                if (ticket.getType() == null)
                    preparedStatement.setNull(index, Types.VARCHAR);
                else
                    preparedStatement.setString(index, ticket.getType().toString());
                index++;
            }
            if (updateData.isWeightSelected()) {
                if (ticket.getPerson().getWeight() == null)
                    preparedStatement.setNull(index, Types.INTEGER);
                else
                    preparedStatement.setLong(index, ticket.getPerson().getWeight());
                index++;
            }
            if (updateData.isEyeColorSelected()) {
                preparedStatement.setString(index, ticket.getPerson().getEyeColor().toString());
                index++;
            }
            if (updateData.isHairColorSelected()) {
                preparedStatement.setString(index, ticket.getPerson().getHairColor().toString());
                index++;
            }
            if (updateData.isCountrySelected()) {
                preparedStatement.setString(index, ticket.getPerson().getNationality().toString());
                index++;
            }
            preparedStatement.setInt(index, id);
            preparedStatement.execute();
        }
    }

    private String prepareString(UpdateData updateData) {
        String sql = "UPDATE tickets SET id = ?";
        if (updateData.isNameSelected())
            sql += ", name = ?";
        if (updateData.isXSelected())
            sql += ", coordinates_x = ?";
        if (updateData.isYSelected())
            sql += ", coordinates_y = ?";
        if (updateData.isPriceSelected())
            sql += ", price = ?";
        if (updateData.isDiscountSelected())
            sql += ", discount = ?";
        if (updateData.isRefundableSelected())
            sql += ", refundable = ?";
        if (updateData.isTypeSelected())
            sql += ", type = ?";
        if (updateData.isWeightSelected())
            sql += ", person_weight = ?";
        if (updateData.isEyeColorSelected())
            sql += ", person_eyes = ?";
        if (updateData.isHairColorSelected())
            sql += ", person_hair = ?";
        if (updateData.isCountrySelected())
            sql += ", person_nation = ?";
        sql += " WHERE id = ?";
        return sql;
    }

    @Override
    public void clear(String owner) throws SQLException {
        PreparedStatement sql = connection.prepareStatement("delete from tickets using common, users where " +
                "tickets.id = common.ticket_id and common.login = ?");
        sql.setString(1, owner);
        sql.execute();
    }

    @Override
    public void remove(int id, String owner) throws SQLException {
        if(commonDataBase.getOwner(id).equals(owner)) {
            PreparedStatement sql = connection.prepareStatement("DELETE FROM TICKETS WHERE id = ?");
            sql.setInt(1, id);
            sql.execute();
        }
    }

    private void create() throws SQLException {
        String create = "CREATE TABLE IF NOT EXISTS  tickets (" +
                "id serial primary key not null," +
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

    @Override
    public void add(ServerTicket ticket) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO tickets (name, coordinates_x, " +
                "coordinates_y, creationDate, price, discount, refundable, type, " +
                "person_weight, person_eyes, person_hair, person_nation, id)" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,DEFAULT)", Statement.RETURN_GENERATED_KEYS);
        updateStatement(ticket, preparedStatement);
        preparedStatement.executeUpdate();
        ResultSet set = preparedStatement.getGeneratedKeys();
        if (set.next()) {
            int id = set.getInt(set.findColumn("id"));
            ticket.setId(id);
            //TODO:
            System.out.println(ticket.getOwner() + " " + id);
            commonDataBase.add(id, ticket.getOwner());
        } else {
            throw new SQLException();
        }
    }

    private void updateStatement(ServerTicket ticket, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, ticket.getName());
        preparedStatement.setDouble(2, ticket.getCoordinates().getX());
        preparedStatement.setInt(3, ticket.getCoordinates().getY());
        preparedStatement.setString(4, ticket.getCreationDate().toString());
        preparedStatement.setInt(5, ticket.getPrice());
        preparedStatement.setDouble(6, ticket.getDiscount());
        if (ticket.getRefundable() == null)
            preparedStatement.setNull(7, Types.BOOLEAN);
        else
            preparedStatement.setBoolean(7, ticket.getRefundable());
        if (ticket.getType() == null)
            preparedStatement.setNull(8, Types.VARCHAR);
        else
            preparedStatement.setString(8, ticket.getType().toString());
        if (ticket.getPerson().getWeight() == null)
            preparedStatement.setNull(9, Types.INTEGER);
        else
            preparedStatement.setLong(9, ticket.getPerson().getWeight());
        preparedStatement.setString(10, ticket.getPerson().getEyeColor().toString());
        preparedStatement.setString(11, ticket.getPerson().getHairColor().toString());
        preparedStatement.setString(12, ticket.getPerson().getNationality().toString());
    }

    @Override
    public Collection<ServerTicket> getTickets() {
        try {
            String query = " SELECT * FROM tickets ORDER by id;";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            Collection<ServerTicket> tickets = new ArrayList<>();
            while (resultSet.next()) {
                ServerTicket ticket = factory.getServerTicket();
                int id = resultSet.getInt(1);
                ticket.setId(id);
                ticket.setOwner(commonDataBase.getOwner(id));
                ticket.setName(resultSet.getString(2));
                Coordinates coordinates = factory.getCoordinates();
                coordinates.setX(resultSet.getDouble(3));
                coordinates.setY(resultSet.getInt(4));
                ticket.setCoordinates(coordinates);
                ticket.setDateStr(resultSet.getString(5));
                ticket.setPrice(resultSet.getInt(6));
                ticket.setDiscount(resultSet.getDouble(7));
                Object obj = resultSet.getObject(8);
                if (obj == null)
                    ticket.setRefundable(null);
                else
                    ticket.setRefundable((Boolean) obj);
                ticket.setTypeStr((String) resultSet.getObject(9));
                DefaultPerson person = factory.getPerson();
                Integer buffer = (Integer) resultSet.getObject(10);
                if (buffer == null)
                    person.setWeight(null);
                else
                    person.setWeight(Long.valueOf(buffer));
                person.setEyeColorStr(resultSet.getString(11));
                person.setHairColorStr(resultSet.getString(12));
                person.setNationalityStr(resultSet.getString(13));
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
