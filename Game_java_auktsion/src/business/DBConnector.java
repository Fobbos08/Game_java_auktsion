package business;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.mysql.jdbc.*;
import models.Goods;
import models.User;


/**
 * Created by Эмиль on 12.05.2015.
 */
public final class DBConnector implements IDBConnector {

    private static Connection connection;

    public DBConnector() {
        connection = loadDriver();
    }

    /**
     * Loads the mysql connection with the given username, password, database
     * and mysql jar driver
     *
     * @return Connection
     */
    public static Connection loadDriver() {
        Connection currentConnection = null;
        try {
            // Path/database name [here guru is the database name]
            String url = "jdbc:mysql://localhost:3306/gamedb";
            Class.forName("com.mysql.jdbc.Driver");
            //new com.mysql.jdbc.Driver();
            // User name and password
            currentConnection = DriverManager.getConnection(url, "root", "1234");

        } catch (Exception exception) {
            System.err.println("Exception! ");
            System.err.println(exception.getMessage());
        }
        return currentConnection;
    }

    public static boolean registerUser(String login, String firstName,
                            String lastName, String email, String password) throws SQLException{
        System.out.println("Insert started!");
        try {
            if (connection == null)
            {
                connection = loadDriver();
            }
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(
                    "INSERT INTO users (login,firstName,lastName,email,password) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );
            preparedStmt.setString(1, login);
            preparedStmt.setString(2, firstName);
            preparedStmt.setString(3, lastName);
            preparedStmt.setString(4, email);
            preparedStmt.setString(5, password);
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return false;
        }
        System.out.println("Insert complete!");
        return true;
    }

    public static User getUser(String login, String password)
    {
        User user = new User();

        try {
            String query = "SELECT * FROM users where login=\""+ login+"\" && password=\"" + password+"\"";

            if (connection == null)
            {
                connection = loadDriver();
            }
            // create the java statement
            Statement st = (Statement) connection.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            rs.next();
            user.setId(rs.getInt("idusers"));
            user.setGUID(UUID.randomUUID());

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        System.out.println("Insert complete!");

        return user;
    }

    public static String getLogin(int userId)
    {
        String login = "";
        try {
            String query = "SELECT * FROM users where iduser=\""+ userId;
            if (connection == null)
            {
                connection = loadDriver();
            }
            Statement st = (Statement) connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            rs.next();
            login = rs.getString("login");
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        System.out.println("Insert complete!");

        return login;
    }



    public static Goods getGoods(int id)
    {
        Goods goods = new Goods();

        try {
            String query = "SELECT * FROM goods where idgoods=\""+ id+"\"";

            if (connection == null)
            {
                connection = loadDriver();
            }
            // create the java statement
            Statement st = (Statement) connection.createStatement();

            // execute the query, and get a java resultset
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            rs.next();
            goods.setId(rs.getInt("idgoods"));
            goods.setDescription(rs.getString("description"));
            goods.setTitle(rs.getString("title"));
            goods.setImagePath(rs.getString("imagePath"));

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        System.out.println("Insert complete!");

        return goods;
    }
}
