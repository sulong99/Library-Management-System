package Services.Helpers;

import org.junit.jupiter.api.Test;

class PasswordHasherHelperTest {

    @Test
    void hashPassword() {
        byte[] salt = new byte[]{'a', 'w', 'q', '5', '1', 'd', 'x', 'u', '6', '4'};
        String actual = PasswordHasherHelper.get_SHA_1_SecurePassword("Worker", salt);

        String expected = "201b5ed129be6724d54afe8e54151e542a451af8";

    }
}