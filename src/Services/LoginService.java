package Services;

import Data.DAO.UserDAO;
import Models.UserModel;

/**
 * Klasa wspomagająca logowanie do aplikacji
 */
public class LoginService {

    private static UserDAO userDAO = new UserDAO();

    /**
     * Metoda zwracająca dane użytkownika o podanym loginie do dalszej autoryzacji.
     * @param login Wczytany login
     * @return userToAuthorize zwraca dane użytkownika o podanym loginie
     */
    public static UserModel getUserToAuthorize(String login) {
        UserModel userToAuthorize = userDAO.get("Select * from Users Where login = '" + login + "'");

        return userToAuthorize;
    }
}
