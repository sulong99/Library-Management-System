package Data.DAO;

import Data.ConnectionFactory;
import Services.SceneManagerService;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    public static List<Integer> get(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            List<Integer> bookIds = new ArrayList<>();
            while (result.next()) {
                bookIds.add(Integer.parseInt(result.getString("Book_id")));
            }
            conn.close();

            return bookIds;
        } catch (SQLException ex) {
            return new ArrayList<Integer>();
        }
    }

    public static void save(int bookId) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = "INSERT INTO `Cart`(`Book_id`, `User_id`) VALUES (" + bookId + "," + SceneManagerService.loggedUser.getUserId() + ") ";
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {

        }
    }

    public static void delete(int bookId) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = "DELETE FROM `Cart` WHERE `Book_id` = " + bookId + " AND User_id = " + SceneManagerService.loggedUser.getUserId() + "";
            System.out.println(query);
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {

        }
    }

    public static void deleteAll() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = "DELETE FROM `Cart` WHERE User_id = " + SceneManagerService.loggedUser.getUserId() + "";
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {

        }
    }
}
