package Models;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CartModelTest {
    private BookModel book1;
    private BookModel book2;
    private BookModel book3;

    @BeforeEach
    void setUp() {
        book1 = new BookModel(1, "Książka", "Ktosik", "Wydawca", "Cosik", "9899898989898", "Opis", "2011", false);
        book2 = new BookModel(2, "Książka2", "Ktosik2", "Wydawca2", "Cosik", "9299898989898", "Opis", "2011", false);
        book3 = new BookModel(3, "Książka3", "Ktosik3", "Wydawca2", "Cosik", "9229898989898", "Opis", "2011", false);

        CartModel.addBook(book1);
        CartModel.addBook(book2);
    }

    @Test
    void clearCart() {
        CartModel.clearCart();
        assertTrue(CartModel.books.isEmpty());
    }

    @Test
    void addBook() {
        CartModel.addBook(book3);
        assertTrue(CartModel.books.contains(book3));
    }

    @Test
    void removeBook() {
        CartModel.removeBook(book2);
        assertFalse(CartModel.books.contains(book2));
    }

    @Test
    void removeBookByID() {
        CartModel.removeBook(book2.getId());
        assertFalse(CartModel.books.contains(book2.getId()));
    }
}