package Mappers;

import Data.DAO.UserDAO;
import Models.RequestModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RequestMapper {

    public static RequestModel mapInstance(ResultSet result) {
        RequestModel requestModel = null;

        try {
            while (result.next()) {
                requestModel = handleResultSet(result);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return requestModel;
    }

    public static List<RequestModel> mapList(ResultSet result) {
        List<RequestModel> brequestModelsokModels = new ArrayList<RequestModel>();

        try {
            while (result.next()) {
                brequestModelsokModels.add(handleResultSet(result));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return brequestModelsokModels;
    }

    private static RequestModel handleResultSet(ResultSet result) {
        RequestModel requestModel = new RequestModel();
        try {
            requestModel.setRequestId(Integer.parseInt(result.getString("Request_id")));
            requestModel.setBookTitle(result.getString("BookTitle"));
            requestModel.setBookAuthor(result.getString("BookAuthor"));
            requestModel.setCategory(result.getString("Category"));
            requestModel.setDescription(result.getString("Description"));
            requestModel.setUserId(Integer.parseInt(result.getString("UserId")));
            boolean isAdd = (Integer.parseInt(result.getString("isAddRequest")) != 0);
            requestModel.setAddRequest(isAdd);
            requestModel.setUser(UserDAO.get("SELECT * FROM `Users` WHERE Users_id = " + requestModel.getUserId()));

            return requestModel;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return requestModel;
    }
}
