package Models;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestModelTest {
    RequestModel request = new RequestModel();

    @Test
    void shouldPrepareInsertQuery() {
        request.setRequestId(15);
        request.setBookTitle("Zemsta");
        request.setBookAuthor("Aleksander Fredro");
        request.setCategory("Komedia");
        request.setDescription("Zniszczona Książka");
        request.setAddRequest(false);
        request.setUserId(4);
        String query = "INSERT INTO Requests (BookTitle, BookAuthor, Category, Description, isAddRequest, UserId) " +
                "VALUES ('" + request.getBookTitle() + "', '" + request.getBookAuthor() + "', '" + request.getCategory() + "', '" + request.getDescription()
                + "', " + request.isAddRequest() + ", '" + request.getUserId() + "');";

        assertEquals(query, request.prepareInsertQuery());
    }

    @Test
    void shouldPrepareUpdateQuery() {
        request.setRequestId(15);
        request.setBookTitle("Zemsta");
        request.setBookAuthor("Aleksander Fredro");
        request.setCategory("Komedia");
        request.setDescription("Zniszczona Książka");
        request.setAddRequest(false);
        request.setUserId(4);
        String query = "UPDATE Requests SET BookTitle = '" + request.getBookTitle() + "', BookAuthor = '" + request.getBookAuthor()
                + "', Category = '" + request.getCategory() + "', Description = '" + request.getDescription() + "', isAddRequest = " + request.isAddRequest()
                + ", `UserId` = '" + request.getUserId() + "' WHERE Request_id = '" + request.getRequestId() + "'";

        assertEquals(query, request.prepareUpdateQuery());
    }

    @Test
    void shouldPrepareDeleteQuery() {
        request.setRequestId(15);
        String query = "DELETE FROM Requests WHERE Request_id = " + request.getRequestId() + "";

        assertEquals(query, request.prepareDeleteQuery());
    }
}
