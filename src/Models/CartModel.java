package Models;

import Data.DAO.BookDAO;
import Data.DAO.CartDAO;

import java.util.ArrayList;
import java.util.List;
/**
 * Klasa model dla tabeli Cart (koszyk) zawierająca metody czyszczenia koszyka, dodawania książki oraz usuwania książki z koszyka.
 */
public class CartModel {
    private int CardId;
    public static List<BookModel> books = new ArrayList<>();

    public CartModel(int cardId) {
        CardId = cardId;
    }

    /**
     * Metoda czyszcząca koszyk, usuwa wszystkie książki z koszyka.
     */
    public static void clearCart() {
        books.clear();
        CartDAO.deleteAll();
    }

    /**
     * Metoda dodająca książkę do koszyka.
     * @param book - zawierający dane książki
     */
    public static void addBook(BookModel book) {
        books.add(book);
        CartDAO.save(book.getId());
    }

    /**
     * Metoda usuwająca książkę z koszyka.
     * @param book - zawierający dane książki
     */
    public static void removeBook(BookModel book) {
        books.remove(book);
    }

    /**
     * Metoda usuwająca książkę z kosszyka po danym id.
     * @param id - parametr zawierający id książki
     */
    public static void removeBook(int id) {
        books.remove(id);
    }
}


