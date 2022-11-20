package Data.DAO;

import Data.ConnectionFactory;
import Mappers.BookRentalMapper;
import Models.BookRentalModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookRentalDAO {
    private static BookRentalMapper bookMapper = new BookRentalMapper();

    public static BookRentalModel get(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            BookRentalModel book = bookMapper.mapInstance(result);
            conn.close();

            return book;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static List<BookRentalModel> getList(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<BookRentalModel> books = bookMapper.mapList(result);
            conn.close();

            return books;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void save(BookRentalModel bookModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = bookModel.prepareInsertQuery();
            statement.executeUpdate(query);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void update(BookRentalModel bookModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = bookModel.prepareUpdateQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {

        }
    }


    public static void delete(BookRentalModel bookModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = bookModel.prepareDeleteQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
