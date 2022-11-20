package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AuthorModelTest {
    AuthorModel author1 = new AuthorModel();

    @Test
    void shouldPrepareInsertQuery() {
        author1.setAuthorId(24);
        author1.setFirstName("Julian");
        author1.setSecondName("Tuwim");
        String query = "INSERT INTO Authors (FirstName, SecondName) VALUES ('" + author1.getFirstName() + "', '" + author1.getSecondName() + "')";

        assertEquals(query, author1.prepareInsertQuery());
    }

    @Test
    void shouldPrepareUpdateQuery() {
        author1.setAuthorId(24);
        author1.setFirstName("Julian");
        author1.setSecondName("Tuwim");
        String query = "UPDATE Authors SET FirstName = '" + author1.getFirstName() + "', `SecondName` = '" + author1.getSecondName() + "' WHERE Author_id = " + author1.getAuthorId() + "";

        assertEquals(query, author1.prepareUpdateQuery());
    }

    @Test
    void shouldPrepareDeleteQuery() {
        author1.setAuthorId(24);
        String query = "DELETE FROM Authors WHERE ID = " + author1.getAuthorId() + "";

        assertEquals(query, author1.prepareDeleteQuery());
    }
}
