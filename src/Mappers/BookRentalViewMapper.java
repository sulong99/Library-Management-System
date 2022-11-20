package Mappers;

import Models.BookRentalViewModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BookRentalViewMapper {

    public static BookRentalViewModel mapInstance(ResultSet result) {
        BookRentalViewModel bookRentalViewModel = null;

        try {
            while (result.next()) {
                bookRentalViewModel = handleResultSet(result);
            }
        } catch (SQLException ex) {

        }
        return bookRentalViewModel;
    }

    public static List<BookRentalViewModel> mapList(ResultSet result) {
        List<BookRentalViewModel> bookRentalViewModel = new ArrayList<BookRentalViewModel>();

        try {
            while (result.next()) {
                bookRentalViewModel.add(handleResultSet(result));
            }
        } catch (SQLException ex) {

        }
        return bookRentalViewModel;
    }

    private static BookRentalViewModel handleResultSet(ResultSet result) {
        BookRentalViewModel bookRentalViewModel = new BookRentalViewModel();
        try {
            bookRentalViewModel.setBookRentalsId(Integer.parseInt(result.getString("BookRentals_id")));
            bookRentalViewModel.setTitle(result.getString("Title"));
            bookRentalViewModel.setAuthor(result.getString("Author"));
            bookRentalViewModel.setUser(result.getString("User"));
            bookRentalViewModel.setCategory(result.getString("Category"));
            bookRentalViewModel.setPublisher(result.getString("Publisher"));
            bookRentalViewModel.setISBN(result.getString("ISBN"));
            bookRentalViewModel.setDateOfBorrow(new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("DateOfBorrow")));
            if (result.getString("DateOfReturn") != null)
                bookRentalViewModel.setDateOfReturn(new SimpleDateFormat("yyyy-MM-dd").parse(result.getString("DateOfReturn")));
            else
                bookRentalViewModel.setDateOfReturn(new Date());

            return bookRentalViewModel;
        } catch (Exception ex) {

        }

        return bookRentalViewModel;
    }
}
