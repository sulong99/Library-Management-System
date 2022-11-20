package Models;

import org.junit.jupiter.api.Test;

import static Models.PermissionEnum.Worker;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UserModelTest {
    UserModel user1 = new UserModel("user1", "user1", "Marek", "Nowak", Worker, "Rzeszów");

    @Test
    void shouldPrepareInsertQuery() {
        String query = "INSERT INTO Users (login,password,firstName,lastName,permission,address) " +
                "Values ('" + user1.getLogin() + "', '" + user1.getPassword() + "', '" + user1.getFirstName() + "', '" + user1.getLastName() + "', '" + user1.getPermissions().toString() + "', '" + user1.getAddress() + "')";
        assertEquals(query, user1.prepareInsertQuery());
    }

    @Test
    void shouldPrepareUpdateQuery() {
        user1.setUserId(20);
        String query = "UPDATE Users SET login = '" + user1.getLogin() + "', password = '" + user1.getPassword() + "', firstName = '" + user1.getFirstName() + "', lastName = '" + user1.getLastName()
                + "', permission = '" + user1.getPermissions().toString() + "', address = '" + user1.getAddress() + "' WHERE Users_id = " + user1.getUserId() + "";
        assertEquals(query, user1.prepareUpdateQuery());
    }

    @Test
    void shouldPrepareDeleteQuery() {
        user1.setUserId(20);
        String query = "DELETE FROM Users WHERE ID = " + user1.getUserId() + "";
        assertEquals(query, user1.prepareDeleteQuery());
    }
}
