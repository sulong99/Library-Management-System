package Mappers;

import Models.SettingsModel;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SettingsMapper {
    public static SettingsModel mapInstance(ResultSet result) {
        SettingsModel settingsModel = null;

        try {
            while (result.next()) {
                settingsModel = handleResultSet(result);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return settingsModel;
    }

    public static List<SettingsModel> mapList(ResultSet result) {
        List<SettingsModel> settingsModels = new ArrayList<SettingsModel>();

        try {
            while (result.next()) {
                settingsModels.add(handleResultSet(result));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return settingsModels;
    }

    private static SettingsModel handleResultSet(ResultSet result) {
        SettingsModel settingsModel = new SettingsModel();
        try {
            settingsModel.setSettingsId(Integer.parseInt(result.getString("Settings_id")));
            settingsModel.setDaysForReturn(Integer.parseInt(result.getString("days_for_return")));
            settingsModel.setPenalty(new BigDecimal(result.getString("penalty")));
            settingsModel.setPenaltyForDayOfDelay(new BigDecimal(result.getString("penalty_for_day_of_delay")));

            return settingsModel;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return settingsModel;
    }
}
