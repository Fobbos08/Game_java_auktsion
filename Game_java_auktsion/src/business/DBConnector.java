package business;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.mysql.jdbc.*;
import game.Player;
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
        try {
            if (connection == null)
            {
                connection = loadDriver();
            }
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(
                    "INSERT INTO users (login,firstName,lastName,email,password,isAdmin,gamesCount,totalScore) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)"
            );
            preparedStmt.setString(1, login);
            preparedStmt.setString(2, firstName);
            preparedStmt.setString(3, lastName);
            preparedStmt.setString(4, email);
            preparedStmt.setString(5, password);
            preparedStmt.setString(6, "false");
            preparedStmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
            return false;
        }
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
            user.setName(rs.getString("login"));
            user.setAdmin(Boolean.parseBoolean(rs.getString("isAdmin")));
            user.setGUID(UUID.randomUUID());

            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return user;
    }

    public static String getLogin(int userId)
    {
        String login = "";
        try {
            String query = "SELECT * FROM users where idusers=\""+ userId+"\"";
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
        return goods;
    }

    public static int getUserGameCount(int userId){
        int gamesCount = 0;
        try {
            String query = "SELECT * FROM users where idusers=\""+ userId+"\"";
            if (connection == null)
            {
                connection = loadDriver();
            }
            Statement st = (Statement) connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            rs.next();
            gamesCount = Integer.parseInt(rs.getString("gamesCount"));
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return gamesCount;
    }

    public static int getUserTotalScore(int userId){
        int totalScore = 0;
        try {
            String query = "SELECT * FROM users where idusers=\""+ userId+"\"";
            if (connection == null)
            {
                connection = loadDriver();
            }
            Statement st = (Statement) connection.createStatement();
            ResultSet rs = st.executeQuery(query);

            // iterate through the java resultset
            rs.next();
            totalScore = Integer.parseInt(rs.getString("totalScore"));
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }

        return totalScore;
    }

    public static void setStatistic(Player player)
    {

        int totalScore = getUserTotalScore(player.getId());
        int gamesCount = getUserGameCount(player.getId());
        try {
            PreparedStatement preparedStmt = (PreparedStatement) connection.prepareStatement(
                    "UPDATE users SET totalScore = ?, gamesCount = ? WHERE idusers=\""+player.getId()+"\""
            );
            preparedStmt.setString(1, String.valueOf(totalScore+player.getScore()));
            preparedStmt.setString(2, String.valueOf(gamesCount+1));
            preparedStmt.execute();

            /*String query = "UPDATE users SET \"totalScore\"=\""+totalScore+"\", \"gamesCount\"=\""+gamesCount+"\"  where idusers=\""+ player.getId()+"\"";
            if (connection == null)
            {
                connection = loadDriver();
            }
            Statement st = (Statement) connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            st.close();*/
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.toString());
        }
        player.getId();
    }
}
