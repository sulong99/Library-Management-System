package Mappers;

import Models.BookModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static BookModel mapInstance(ResultSet result) {
        BookModel bookModel = null;

        try {
            while (result.next()) {
                bookModel = handleResultSet(result);
            }
        } catch (SQLException ex) {
            return null;
        }
        return bookModel;
    }

    public static List<BookModel> mapList(ResultSet result) {

        List<BookModel> bookModels = new ArrayList<BookModel>();

        try {
            while (result.next()) {
                bookModels.add(handleResultSet(result));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return bookModels;
    }

    private static BookModel handleResultSet(ResultSet result) {

        BookModel bookModel = new BookModel();
        try {
            bookModel.setId(Integer.parseInt(result.getString("Books_id")));
            bookModel.setISBN(result.getString("ISBN"));
            bookModel.setTitle(result.getString("Title"));
            bookModel.setCategory(result.getString("Category"));
            bookModel.setPublisher(result.getString("Publisher"));
            bookModel.setReleaseYear(result.getString("Year"));
            bookModel.setDescription(result.getString("Description"));
            bookModel.setIsAvailableStr(result.getString("is_taken"));
            bookModel.setAuthor(result.getString("Authors"));

            return bookModel;
        } catch (SQLException ex) {

        }
        return bookModel;
    }
}
