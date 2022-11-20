package Data.DAO;

import Data.ConnectionFactory;
import Mappers.SettingsMapper;
import Models.SettingsModel;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class SettingsDAO {
    private static SettingsMapper settingsMapper = new SettingsMapper();

    public static SettingsModel get(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);

            SettingsModel settings = settingsMapper.mapInstance(result);
            conn.close();

            return settings;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static List<SettingsModel> getList(String query) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            ResultSet result = statement.executeQuery(query);
            List<SettingsModel> settings = settingsMapper.mapList(result);
            conn.close();

            return settings;
        } catch (SQLException ex) {
            return null;
        }
    }

    public static void save(SettingsModel settingsModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = settingsModel.prepareInsertQuery();
            statement.executeUpdate(query);


        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void update(SettingsModel settingsModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = settingsModel.prepareUpdateQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }


    public static void delete(SettingsModel settingsModel) {
        try {
            Connection conn = ConnectionFactory.getConnection();
            Statement statement = conn.createStatement();
            String query = settingsModel.prepareDeleteQuery();
            statement.executeUpdate(query);

            conn.commit();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
