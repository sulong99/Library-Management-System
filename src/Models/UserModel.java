package Models;

import com.sun.istack.internal.Nullable;
/**
 * Klasa model dla tabeli User(użytkownicy) zawierająca gettery i seetery oraz zapytania do wklejania, usuwania i edycji danych w tabeli.
 */
public class UserModel {
    private int userId;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Enum<PermissionEnum> permissions;
    private String permissionTranslation;
    private String address;
    @Nullable
    private int count;

    public UserModel() {
    }

    public UserModel(String login, String password, String firstName, String lastName, Enum<PermissionEnum> permissions, String address) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.permissions = permissions;
        this.address = address;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Enum<PermissionEnum> getPermissions() {

        return permissions;
    }

    public String getPermissionTranslation() {
        return permissionTranslation;
    }

    public void setPermissionTranslation(String permissionTranslation) {
        switch (permissionTranslation) {
            case "User":
                this.permissionTranslation = "Użytkownik";
                break;
            case "Worker":
                this.permissionTranslation = "Pracownik";
                break;
            case "Administrator":
                this.permissionTranslation = "Administrator";
                break;
        }
    }

    public void setPermissions(Enum<PermissionEnum> permissions) {

        this.permissions = permissions;
        setPermissionTranslation(permissions.toString());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Metoda służąca przygotowaniu zapytania na temat dodawania nowego użytkownika.
     * @return zapytanie dotyczące dodawania użytkownika o podanych parametrach.
     */
    public String prepareInsertQuery() {
        String query = "INSERT INTO Users (login,password,firstName,lastName,permission,address) " +
                "Values ('" + this.login + "', '" + this.password + "', '" + this.firstName + "', '" + this.lastName + "', '" + this.permissions.toString() + "', '" + this.address + "')";

        return query;
    }
    /**
     * Metoda służąca przygotowaniu zapytania na temat aktualizacji danych użytkownika.
     * @return zapytanie dotyczące aktualuzacji danych użytkownika o podanych parametrach po ID.
     */
    public String prepareUpdateQuery() {
        String query = "UPDATE Users SET login = '" + this.login + "', password = '" + this.password + "', firstName = '" + this.firstName + "', lastName = '" + this.lastName
                + "', permission = '" + this.permissions.toString() + "', address = '" + this.address + "' WHERE Users_id = " + this.userId + "";

        return query;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return String - będący połączeniem imienia i nazwiska oddzielonych spacją
     */
    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    /**
     * Metoda służąca przygotowaniu zapytania na temat usuwania danego użytkownika.
     * @return zapytanie dotyczące usuwania użytkownika o danym ID.
     */
    public String prepareDeleteQuery() {
        String query = "DELETE FROM Users WHERE ID = " + this.userId + "";

        return query;


    }
}

