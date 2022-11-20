package Mappers;

import Models.PermissionEnum;
import Models.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMapper {

    public static UserModel mapInstance(ResultSet result) {
        UserModel userModel = null;

        try {
            while (result.next()) {
                userModel = handleResultSet(result);
            }
        } catch (SQLException ex) {

        }
        return userModel;
    }

    public static List<UserModel> mapList(ResultSet result) {
        List<UserModel> userModels = new ArrayList<UserModel>();

        try {
            while (result.next()) {
                userModels.add(handleResultSet(result));
            }
        } catch (SQLException ex) {

        }
        return userModels;
    }

    private static UserModel handleResultSet(ResultSet result) {
        UserModel userModel = new UserModel();
        try {
            userModel.setUserId(Integer.parseInt(result.getString("Users_id")));
            userModel.setLogin(result.getString("Login"));
            userModel.setPassword(result.getString("Password"));
            userModel.setFirstName(result.getString("FirstName"));
            userModel.setLastName(result.getString("LastName"));
            userModel.setAddress(result.getString("Address"));
            userModel.setPermissions(PermissionEnum.valueOf(result.getString("Permission")));
            userModel.setCount(Integer.parseInt(result.getString("countBr")));

            return userModel;
        } catch (SQLException ex) {

        }
        return userModel;
    }
}
