package Data.DAO;

import Data.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class HelperDAO {

    public static void executeQuery(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {

        }
    }
}
