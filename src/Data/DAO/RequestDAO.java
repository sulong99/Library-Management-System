package Data.DAO;

import Data.ConnectionFactory;
import Mappers.RequestMapper;
import Models.RequestModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class RequestDAO {
    private static RequestMapper requestMapper = new RequestMapper();

    public static RequestModel get(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            RequestModel request = requestMapper.mapInstance(result);
            conn.close();

            return request;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static List<RequestModel> getList(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<RequestModel> requests = requestMapper.mapList(result);
            conn.close();

            return requests;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void save(RequestModel requestModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = requestModel.prepareInsertQuery();
            statement.executeUpdate(query);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void update(RequestModel requestModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = requestModel.prepareUpdateQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static void delete(RequestModel requestModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = requestModel.prepareDeleteQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
