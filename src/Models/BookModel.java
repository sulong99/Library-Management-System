package Models;
/**
 * Klasa model dla tabeli Book zawierająca gettery i seetery oraz zapytania do wklejania, usuwania i edycji danych w tabeli.
 */
public class BookModel {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private String category;
    private String ISBN;
    private String description;
    private String releaseYear;
    private boolean isTaken;
    private String isAvailableStr;

    public BookModel(int id, String title, String author, String publisher, String category, String ISBN, String description, String releaseYear, boolean isTaken) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.ISBN = ISBN;
        this.description = description;
        this.releaseYear = releaseYear;
        this.isTaken = isTaken;
        this.isAvailableStr = String.valueOf(!isTaken);
    }

    public BookModel(String title, String publisher, String category, String ISBN, String description, String releaseYear, boolean isTaken) {
        this.title = title;
        this.publisher = publisher;
        this.category = category;
        this.ISBN = ISBN;
        this.description = description;
        this.releaseYear = releaseYear;
        this.isTaken = isTaken;
        this.isAvailableStr = String.valueOf(!isTaken);
    }

    public BookModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsAvailableStr() {
        return isAvailableStr;
    }

    public void setIsAvailableStr(String isAvailableStr) {
        if (isAvailableStr.equals("0")) {
            this.isAvailableStr = "Dostępny";
            this.isTaken = false;
        }
        else{
            this.isAvailableStr = "Niedostępny";
            this.isTaken = true;
        }
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(String releaseYear) {
        this.releaseYear = releaseYear;
    }

    public boolean getIsUnavailable() {
        return isTaken;
    }

    public void setIsTaken(boolean available) {
        isTaken = available;
    }

    /**
     * Metoda służąca przygotowaniu zapytania na temat dodawania książki o podanych danych.
     * @return zapytanie dotyczące dodawania książki o podanych danych.
     */
    public String prepareInsertQuery() {
        String query = "INSERT INTO Books (ISBN, Title, Category, Publisher, Year, Description, is_taken) " +
                "VALUES ('" + this.ISBN + "', '" + this.title + "', '" + this.category + "', '" + this.publisher + "', '"
                + this.releaseYear + "', '" + this.description + "', " + this.isTaken + ");";

        return query;
    }

    /**
     * Metoda służąca przygotowaniu zapytania na temat aktualizacji książki o podanych danych.
     * @return zapytanie dotyczące aktualizacji danych książki o podanym id.
     */
    public String prepareUpdateQuery() {
        String query = "UPDATE Books SET ISBN = '" + this.ISBN + "', Title = '" + this.title + "', Category = '" + this.category + "', Publisher = '" + this.publisher + "', Year = '" + this.releaseYear + "', Description = '" + this.description + "', is_taken = " + this.isTaken + " " +
                "WHERE Books_id = " + this.id + "";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat usuwania książki o podanym ID.
     * @return zapytanie dotyczące usuwania książki z danym ID.
     */
    public String prepareDeleteQuery() {
        String query = "DELETE FROM Books WHERE ID = " + this.id + "";

        return query;
    }
}
