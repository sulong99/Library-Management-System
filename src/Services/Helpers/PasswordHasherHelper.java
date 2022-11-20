package Services.Helpers;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class PasswordHasherHelper {

    /**
     * Metoda służąca kodowaniu hasła
     * @param password - string zawierający hasło
     * @return securePassword - string będący zakodowanym hasłem
     */
    public static String hashPassword(String password) {

        byte[] salt = getSalt();

        String securePassword = get_SHA_1_SecurePassword(password, salt);
        return securePassword;
    }

    /**
     * Metoda sprawdzająca czy hasło zgadza się z hasłem z bazy danych
     * @param password - wprowadzone hasło, do zakodowania
     * @param passwordFromDb - hasło z bazy danych
     * @return wartość true jeżeli hasła się zgadzają lub false gdy hasła są różne
     */
    public static boolean isPasswordMatch(String password, String passwordFromDb) {
        String encryptedPassword = hashPassword(password);

        return encryptedPassword.equals(passwordFromDb);
    }

    public static String get_SHA_1_SecurePassword(String passwordToHash, byte[] salt) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    private static byte[] getSalt() {
        try {
            SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            byte[] salt = new byte[]{'a', 'w', 'q', '5', '1', 'd', 'x', 'u', '6', '4'};
            return salt;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
