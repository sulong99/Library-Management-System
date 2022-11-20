package Services;

import Data.DAO.BookDAO;
import Data.DAO.BookRentalDAO;
import Data.DAO.CartDAO;
import Data.DAO.HelperDAO;
import Models.BookModel;
import Models.BookRentalModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Klasa wspomagająca pracę koszyka.
 */
public class CartService {

    /**
     * Metoda odpowiadająca za wyświetlenie książek w koszyku przypisanych do konkretnego użytkownika
     * @return result - Lista książek
     */
    public static List<BookModel> fillCart() {
        List<Integer> bookIds = CartDAO.get("SELECT * FROM `Cart` WHERE `User_id` = " + SceneManagerService.loggedUser.getUserId() + "");
        List<BookModel> result = new ArrayList<>();
        for (int i : bookIds) {
            result.add(BookDAO.get("SELECT DISTINCT Books.Books_id, Books.ISBN, Books.Title, Books.Description, Books.`is_taken`, CONCAT(Authors.FirstName, ' ', Authors.SecondName) AS 'Authors', Books.Category, Books.Publisher, Books.Year FROM Books LEFT JOIN Authors_Books ON Authors_Books.Book_id = Books.Books_id LEFT JOIN Authors ON Authors.Author_id = Authors_Books.Author_id WHERE Books.Books_id = " + i + ""));
        }
        return result;
    }

    /**
     * Metoda odpowiadająca za dokonanie wypożyczeń, książek znajdujących się w koszyku.
     * @param books Lista książek
     * @param settingsId id przypisanych ustawień wypożyczenia
     * @see BookModel
     * @see BookRentalModel
     * @see BookDAO
     * @see BookRentalDAO
     * @see HelperDAO
     */
    public static void borrowBooks(List<BookModel> books, int settingsId) {

        for (BookModel book : books) {
            book.setIsTaken(true);
            BookDAO.update(book);
            BookRentalModel toSave = new BookRentalModel(book.getId(), new Date(), settingsId);
            BookRentalDAO.save(toSave);
            BookRentalModel latestRental = BookRentalDAO.get("SELECT * FROM `BookRentals` ORDER BY BookRentals_id DESC LIMIT 1");
            HelperDAO.executeQuery("INSERT INTO `BookRentals_Users`(`BookRentals_id`, `User_id`) VALUES (" + latestRental.getBookRentalId() + "," + SceneManagerService.loggedUser.getUserId() + ")");
        }
    }

}
