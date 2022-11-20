package Mappers;

import Models.BookRentalModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BookRentalMapper {
    public static BookRentalModel mapInstance(ResultSet result) {
        BookRentalModel bookModel = null;

        try {
            while (result.next()) {
                bookModel = handleResultSet(result);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookModel;
    }

    public static List<BookRentalModel> mapList(ResultSet result) {
        List<BookRentalModel> bookModels = new ArrayList<BookRentalModel>();

        try {
            while (result.next()) {
                bookModels.add(handleResultSet(result));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookModels;
    }

    private static BookRentalModel handleResultSet(ResultSet result) {
        BookRentalModel bookModel = new BookRentalModel();
        try {
            bookModel.setBookRentalId(Integer.parseInt(result.getString("BookRentals_id")));
            bookModel.setBookId(Integer.parseInt(result.getString("Book_id")));
            bookModel.setDateOfBorrow(new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("DateOfBorrow")));
            if (result.getString("DateOfReturn") != null)
                bookModel.setDateOfReturn(new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("DateOfReturn")));
            bookModel.setSettingsId(Integer.parseInt(result.getString("Settings_id")));

            return bookModel;
        } catch (SQLException | ParseException ex) {
            ex.printStackTrace();
        }
        return bookModel;
    }
}
