package Data.DAO;

import Data.ConnectionFactory;
import Mappers.BookMapper;
import Models.BookModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class BookDAO {
    private static BookMapper bookMapper = new BookMapper();

    public static BookModel get(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            BookModel book = bookMapper.mapInstance(result);
            conn.close();

            return book;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static List<BookModel> getList(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<BookModel> books = bookMapper.mapList(result);
            conn.close();

            return books;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void save(BookModel bookModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = bookModel.prepareInsertQuery();
            statement.executeUpdate(query);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void update(BookModel bookModel) {
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


    public static void delete(BookModel bookModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = bookModel.prepareDeleteQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {

        }
    }
}
