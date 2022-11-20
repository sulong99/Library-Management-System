package Mappers;

import Models.AuthorModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

    public static AuthorModel mapInstance(ResultSet result) {
        AuthorModel authorModel = null;

        try {
            while (result.next()) {
                authorModel = handleResultSet(result);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authorModel;
    }

    public static List<AuthorModel> mapList(ResultSet result) {
        List<AuthorModel> authorModels = new ArrayList<AuthorModel>();

        try {
            while (result.next()) {
                authorModels.add(handleResultSet(result));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authorModels;
    }

    private static AuthorModel handleResultSet(ResultSet result) {
        AuthorModel authorModel = new AuthorModel();
        try {
            authorModel.setAuthorId(Integer.parseInt(result.getString("Author_id")));
            authorModel.setFirstName(result.getString("FirstName"));
            authorModel.setSecondName(result.getString("SecondName"));

            return authorModel;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authorModel;
    }
}
