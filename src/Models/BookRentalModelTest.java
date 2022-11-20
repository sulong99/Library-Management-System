package Models;

import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BookRentalModelTest {

    String dateInString1 = "2021-04-25";
    String dateInString2 = "2021-05-25";

    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date dateborrow = format.parse(dateInString1);
    Date datereturn = format.parse(dateInString2);

    BookRentalModel bookrental1 = new BookRentalModel();

    @Test
    void shouldPrepareInsertQuery() {
        bookrental1.setBookRentalId(10);
        bookrental1.setBookId(12);
        bookrental1.setDateOfBorrow(dateborrow);
        bookrental1.setDateOfReturn(datereturn);
        bookrental1.setSettingsId(2);
        String query = "INSERT INTO BookRentals (Book_id, DateOfBorrow, DateOfReturn, Settings_id) " +
                "VALUES (" + bookrental1.getBookId() + ", DATE ('" + bookrental1.getFormattedDateOfBorrow() + "'), " + bookrental1.getDateOfReturn()
                + ", " + bookrental1.getSettingsId() + ")";

        assertEquals(query, bookrental1.prepareInsertQuery());
    }

    @Test
    void shouldPrepareUpdateQuery() {
        bookrental1.setBookRentalId(10);
        bookrental1.setBookId(12);
        bookrental1.setDateOfBorrow(dateborrow);
        bookrental1.setDateOfReturn(datereturn);
        bookrental1.setSettingsId(2);
        String query = "UPDATE BookRentals SET Book_id = '" + bookrental1.getBookId() + "', DateOfBorrow = '" + bookrental1.getFormattedDateOfBorrow()
                + "', `DateOfReturn` = '" + bookrental1.getFormattedDateOfReturn() + "', Settings_id = '" + bookrental1.getSettingsId()
                + "' WHERE BookRentals_id = '" + bookrental1.getBookRentalId() + "';";

        assertEquals(query, bookrental1.prepareUpdateQuery());
    }

    @Test
    void shouldPrepareDeleteQuery() {
        bookrental1.setBookRentalId(10);
        String query = "DELETE FROM BookRentals WHERE BookRentals_id = " + bookrental1.getBookRentalId() + "";
        assertEquals(query, bookrental1.prepareDeleteQuery());
    }

    public BookRentalModelTest() throws ParseException {
    }
}
