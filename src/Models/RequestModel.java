package Models;
/**
 * Klasa model dla tabeli Request (zapytania) zawierająca gettery i seetery oraz zapytania do wklejania, usuwania i edycji danych w tabeli.
 */
public class RequestModel {
    private int requestId;
    private String bookTitle;
    private String bookAuthor;
    private String category;
    private String description;
    private boolean isAddRequest;
    private int userId;
    private UserModel user;
    private String isAddRequesttxt;

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public RequestModel(String bookTitle, String bookAuthor, String category, String description, boolean isAddRequest, int userId) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.category = category;
        this.description = description;
        this.isAddRequest = isAddRequest;
        this.userId = userId;
    }

    public RequestModel() {
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAddRequest() {
        return isAddRequest;
    }

    public void setAddRequest(boolean addRequest) {
        isAddRequest = addRequest;
        this.setIsAddRequesttxt(addRequest);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getIsAddRequesttxt() {
        return isAddRequesttxt;
    }

    /**
     * Metoda zamieniająca typ boolean na string dotyczący rodzaju prośby.
     * @param isAddRequest informacja oznaczająca rodzaja wypożyczenia w formacie 0 i 1
     */
    public void setIsAddRequesttxt(boolean isAddRequest) {
        if (isAddRequest)
            this.isAddRequesttxt = "Dodanie";
        else
            this.isAddRequesttxt = "Usunięcie";
    }

    /**
     * Metoda służąca przygotowaniu zapytania na temat dodawania nowego zapytania.
     * @return zapytanie dotyczące dodawania zapytania o podanych parametrach.
     */
    public String prepareInsertQuery() {
        String query = "INSERT INTO Requests (BookTitle, BookAuthor, Category, Description, isAddRequest, UserId) " +
                "VALUES ('" + this.bookTitle + "', '" + this.bookAuthor + "', '" + this.category + "', '" + this.description + "', " + this.isAddRequest + ", '" + this.userId + "');";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat aktualizacji zapytania o podanym ID.
     * @return zapytanie dotyczące aktualizacji zapytania o danym ID.
     */
    public String prepareUpdateQuery() {
        String query = "UPDATE Requests SET BookTitle = '" + this.bookTitle + "', BookAuthor = '" + this.bookAuthor
                + "', Category = '" + this.category + "', Description = '" + this.description + "', isAddRequest = " + this.isAddRequest
                + ", `UserId` = '" + this.userId + "' WHERE Request_id = '" + this.requestId + "'";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat usuwania zapytania o podanym ID.
     * @return zapytanie dotyczące usuwania zapytania o danym ID.
     */
    public String prepareDeleteQuery() {
        String query = "DELETE FROM Requests WHERE Request_id = " + this.requestId + "";

        return query;
    }
}
