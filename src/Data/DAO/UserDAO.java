package Data.DAO;

import Data.ConnectionFactory;
import Mappers.UserMapper;
import Models.UserModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class UserDAO {
    private static UserMapper userMapper = new UserMapper();

    public static UserModel get(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            UserModel user = userMapper.mapInstance(result);
            conn.close();

            return user;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static List<UserModel> getList(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<UserModel> users = userMapper.mapList(result);
            conn.close();

            return users;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void save(UserModel userModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = userModel.prepareInsertQuery();
            statement.executeUpdate(query);


        } catch (SQLException ex) {

        }
    }

    public static void update(UserModel userModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = userModel.prepareUpdateQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {

        }
    }

    public static void delete(UserModel userModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = userModel.prepareDeleteQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {

        }
    }
}
