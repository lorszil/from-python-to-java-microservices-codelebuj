package top5.dao.implement;

import top5.model.Client;

import java.sql.*;

public class ClientDaoJdbc extends ConnectionDB {

    private static ClientDaoJdbc instance = null;
    public static ClientDaoJdbc getInstance() {
        if (instance == null) {
            instance = new ClientDaoJdbc();
        }
        return instance;
    }



    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void executeQuery(String query) {
        super.executeQuery(query);
    }




    public void addClient(Client client) {
        try {
            String query = "INSERT INTO client (client_identifier, client_name) VALUES(?,?);";
            PreparedStatement safeInput = getConnection().prepareStatement(query);
            safeInput.setString(1,client.getClientKey());
            safeInput.setString(2,client.getClientName());
            safeInput.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param id
     * @return void
     */


    public Client findClient(String id) {
        String query = "SELECT * FROM client WHERE client_identifier = '" + id + "';";
        Client found = null;
        try {Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            if (resultSet.next()) {
                found = new Client(resultSet.getString("client_id"),
                        resultSet.getString("client_name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return found;
    }
}
