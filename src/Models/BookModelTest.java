package Models;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookModelTest {
    BigDecimal a = new BigDecimal("50.0");
    BigDecimal b = new BigDecimal("0.5");
    BookModel book1 = new BookModel();


    @Test
    void shouldPrepareInsertQuery() {
        book1.setId(20);
        book1.setISBN("9788366251182");
        book1.setTitle("Balladyna");
        book1.setCategory("Dramat");
        book1.setPublisher("Siedmiogród");
        book1.setReleaseYear("2019");
        book1.setDescription("Przedstawiamy ponadczasowy dramat Juliusza Słowackiego, który od pokoleń zaciekawia i intryguje widzów i " +
                "czytelników. Historia zaczyna się jak baśń, a kończy jak tragedia.");
        book1.setIsTaken(false);
        String query = "INSERT INTO Books (ISBN, Title, Category, Publisher, Year, Description, is_taken) " +
                "VALUES ('" + book1.getISBN() + "', '" + book1.getTitle() + "', '" + book1.getCategory() + "', '" + book1.getPublisher() + "', '"
                + book1.getReleaseYear() + "', '" + book1.getDescription() + "', " + book1.getIsUnavailable() + ");";

        assertEquals(query, book1.prepareInsertQuery());
    }

    @Test
    void shouldPrepareUpdateQuery() {
        book1.setId(21);
        book1.setISBN("9788366251182");
        book1.setTitle("Balladyna");
        book1.setCategory("Dramat");
        book1.setPublisher("Siedmiogród");
        book1.setReleaseYear("2019");
        book1.setDescription("Przedstawiamy ponadczasowy dramat Juliusza Słowackiego, który od pokoleń zaciekawia i intryguje widzów i " +
                "czytelników. Historia zaczyna się jak baśń, a kończy jak tragedia.");
        book1.setIsTaken(false);
        String query = "UPDATE Books SET ISBN = '" + book1.getISBN() + "', Title = '" + book1.getTitle() + "', Category = '" + book1.getCategory()
                + "', Publisher = '" + book1.getPublisher() + "', Year = '" + book1.getReleaseYear() + "', Description = '" + book1.getDescription()
                + "', is_taken = " + book1.getIsUnavailable() + " " + "WHERE Books_id = " + book1.getId() + "";

        assertEquals(query, book1.prepareUpdateQuery());
    }

    @Test
    void shouldPrepareDeleteQuery() {
        book1.setId(20);
        String query = "DELETE FROM Books WHERE ID = " + book1.getId() + "";
        assertEquals(query, book1.prepareDeleteQuery());
    }
}
