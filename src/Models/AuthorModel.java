package Models;

/**
 * Klasa model dla tabeli autor zawierająca gettery i seetery oraz zapytania do wklejania, usuwania i edycji danych w tabeli.
 */
public class AuthorModel {
    private int authorId;
    private String firstName;
    private String secondName;

    public AuthorModel(int authorId, String firstName, String secondName) {
        this.authorId = authorId;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public AuthorModel() {
    }

    public AuthorModel(String firstName, String secondName) {
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    /**
     * Metoda służąca przygotowaniu zapytania usuwania danych z tabeli
     * @return query - zapytanie usunięcia autora o danym ID
     */
    public String prepareDeleteQuery() {
        String query = "DELETE FROM Authors WHERE ID = " + this.authorId + "";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania edycji danych z tabeli
     * @return query - zapytanie aktualizacji danych autora o danym ID
     */
    public String prepareUpdateQuery() {
        String query = "UPDATE Authors SET FirstName = '" + this.firstName + "', `SecondName` = '" + this.secondName + "' WHERE Author_id = " + this.authorId + "";

        return query;
    }

    /**
     * Metoda służąca przygotowaniu zapytania dodawania danych do tabeli
     * @return query - zapytanie dodania Autora o danych wartościach imienia i nazwiska
     */
    public String prepareInsertQuery() {
        String query = "INSERT INTO Authors (FirstName, SecondName) VALUES ('" + this.firstName + "', '" + this.secondName + "')";

        return query;
    }

    /**
     * Metoda służąca do łączenia imienia i nazwiska w jeden ciąg znaków oddzielony spacją.
     * @return String będący połączeniem imienia i nazwiska oddzielonego spacją.
     */
    @Override
    public String toString() {
        return  firstName + " " + secondName;
    }
}
