package Models;

import com.sun.istack.internal.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * Klasa model dla tabeli BookRental (wypożyczenia) zawierająca gettery i seetery oraz zapytania do wklejania, usuwania i edycji danych w tabeli.
 */
public class BookRentalModel {
    private int bookRentalId;
    private int bookId;
    private Date dateOfBorrow;
    @Nullable
    private Date dateOfReturn;
    private int settingsId;

    public BookRentalModel(int bookRentalId, int bookId, Date dateOfBorrow, int settingsId) {
        this.bookRentalId = bookRentalId;
        this.bookId = bookId;
        this.dateOfBorrow = dateOfBorrow;
        this.settingsId = settingsId;
    }

    public BookRentalModel(int bookRentalId, int bookId, Date dateOfBorrow, Date dateOfReturn, int settingsId) {
        this.bookRentalId = bookRentalId;
        this.bookId = bookId;
        this.dateOfBorrow = dateOfBorrow;
        this.dateOfReturn = dateOfReturn;
        this.settingsId = settingsId;
    }

    public BookRentalModel(int bookId, Date dateOfBorrow, int settingsId) {
        this.bookId = bookId;
        this.dateOfBorrow = dateOfBorrow;
        this.settingsId = settingsId;
    }

    public BookRentalModel() {
    }

    public int getBookRentalId() {
        return bookRentalId;
    }

    public void setBookRentalId(int bookRentalId) {
        this.bookRentalId = bookRentalId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getDateOfBorrow() {
        return dateOfBorrow;
    }

    public void setDateOfBorrow(Date dateOfBorrow) {
        this.dateOfBorrow = dateOfBorrow;
    }

    public Date getDateOfReturn() {
        return dateOfReturn;
    }

    public void setDateOfReturn(Date dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
    }

    public int getSettingsId() {
        return settingsId;
    }

    public void setSettingsId(int settingsId) {
        this.settingsId = settingsId;
    }

    /**
     * Metoda służąca przygotowaniu zapytania na temat dodawania wypożyczenia.
     * @return zapytanie dotyczące dodawania książki o podanych danych.
     */
    public String prepareInsertQuery() {
        String query = "INSERT INTO BookRentals (Book_id, DateOfBorrow, DateOfReturn, Settings_id) " +
                "VALUES (" + this.bookId + ", DATE ('" + this.getFormattedDateOfBorrow() + "'), " + this.dateOfReturn + ", " + this.settingsId + ")";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat aktualizacji danych wypożyczenia.
     * @return zapytanie dotyczące aktualuzachi danych wypożyczenia o podanym ID.
     */
    public String prepareUpdateQuery() {
        String query = "UPDATE BookRentals SET Book_id = '" + this.bookId + "', DateOfBorrow = '" + this.getFormattedDateOfBorrow() + "', `DateOfReturn` = '" + this.getFormattedDateOfReturn() + "', Settings_id = '" + this.settingsId + "' WHERE BookRentals_id = '" + this.bookRentalId + "';";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat usuwania wypożyczenia.
     * @return zapytanie dotyczące usuwania wypożyczenia o danym ID.
     */
    public String prepareDeleteQuery() {
        String qurey = "DELETE FROM BookRentals WHERE BookRentals_id = " + this.bookRentalId + "";

        return qurey;
    }

    /**
     * Metoda służąca formatowaniu daty zwrotu do danego formatu
     * @return date - data w formacie rok-miesiąc-dzień
     */
    public String getFormattedDateOfReturn() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(dateOfReturn);
        return date;
    }
    /**
     * Metoda służąca formatowaniu daty wypożyczenia do danego formatu
     * @return date - data w formacie rok-miesiąc-dzień
     */
    public String getFormattedDateOfBorrow() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String date = format.format(dateOfBorrow);
        return date;
    }
}
