package top5.dao.implement;


import org.json.simple.JSONObject;
import top5.model.PaidProducts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

public class PaidProductDaoJdbc extends ConnectionDB {

    @Override
    public Connection getConnection() throws SQLException {
        return super.getConnection();
    }

    @Override
    public void executeQuery(String query) {
        super.executeQuery(query);
    }

    private static PaidProductDaoJdbc instance = null;

    public static PaidProductDaoJdbc getInstance() {
        if (instance == null) {
            instance = new PaidProductDaoJdbc();
        }
        return instance;
    }

    public void addPaidProducts(PaidProducts paidProduct){
        String query = "INSERT INTO paid_products (product_id, quantity, purchase_time, client_id)" +
                "VALUES (" + paidProduct.getProductID() + "," + paidProduct.getQuantity() + ",'" + paidProduct.getPurchaseTime() +"',(SELECT client_id from client WHERE client_identifier='"+ paidProduct.getClientKey()+"'));";
        System.out.println(query);
        executeQuery(query);
    }

    /**
     *
     * @param client_identifier
     */


    public List<PaidProducts> findPaidProducts(String client_identifier) {
        String query = "SELECT  product_id, SUM(quantity) AS quantity from paid_products INNER JOIN client ON paid_products.client_id=client.client_id WHERE client.client_identifier = '" + client_identifier + "' AND purchase_time > current_date - INTERVAL '30 days' GROUP BY product_id, client_identifier ORDER BY quantity DESC LIMIT 5;";
        PaidProducts found = null;
        List<PaidProducts> top5 = new ArrayList<>();

        try {Connection connection = getConnection();
            Statement stmt = connection.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);

            while (resultSet.next()) {

                found = new PaidProducts(
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"));
                top5.add(found);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return top5;
    }

}
