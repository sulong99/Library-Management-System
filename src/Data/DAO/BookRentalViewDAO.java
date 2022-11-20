package Data.DAO;

import Data.ConnectionFactory;
import Mappers.BookRentalViewMapper;
import Models.BookRentalViewModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookRentalViewDAO {
    private static BookRentalViewMapper mapper = new BookRentalViewMapper();

    public static BookRentalViewModel get(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            BookRentalViewModel book = mapper.mapInstance(result);
            conn.close();

            return book;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static List<BookRentalViewModel> getList(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<BookRentalViewModel> books = mapper.mapList(result);
            conn.close();

            return books;
        } catch (SQLException ex) {
            return null;
        }
    }
}
