package Data.DAO;

import Data.ConnectionFactory;
import Mappers.AuthorMapper;
import Models.AuthorModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AuthorDAO {
    private static AuthorMapper authorMapper = new AuthorMapper();

    public static AuthorModel get(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            AuthorModel author = authorMapper.mapInstance(result);
            conn.close();

            return author;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static List<AuthorModel> getList(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<AuthorModel> authors = authorMapper.mapList(result);
            conn.close();

            return authors;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void save(AuthorModel authorModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = authorModel.prepareInsertQuery();
            statement.executeUpdate(query);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void update(AuthorModel authorModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = authorModel.prepareUpdateQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }



    public static void delete(AuthorModel authorModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = authorModel.prepareDeleteQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
