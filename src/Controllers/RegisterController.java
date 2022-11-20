package Controllers;

import Data.DAO.UserDAO;
import Models.CartModel;
import Models.PermissionEnum;
import Models.UserModel;
import Services.AlertService;
import Services.Helpers.PasswordHasherHelper;
import Services.LoginService;
import Services.SceneManagerService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za kontrolę widoku obsługującego rejestrację
 */
public class RegisterController implements Initializable {

    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField firstname;

    @FXML
    private JFXTextField surname;

    @FXML
    private JFXTextField address;

    @FXML
    private JFXTextField city;

    @FXML
    private JFXTextField postcode;
    private SceneManagerService sceneManager;
    private AlertService alertService;

    /**
     * Metoda umożliwiająca rejestrację
     * W przypadku błędnie wprowadzonych danych wyświetlany jest stosowny komunikat
     * W przypadku poprawnej rejestracji zostaje zwrócony komunikat potwierdzający oraz zostajemy przeniesieni do panelu logowania
     * @param event
     * @see SceneManagerService
     * @see PasswordHasherHelper
     * @see UserModel
     * @see UserDAO
     * @see AlertService
     */
    @FXML
    void registerButton(ActionEvent event) {
        if (!username.getText().isEmpty() && !password.getText().isEmpty() && !firstname.getText().isEmpty() && !surname.getText().isEmpty() && !city.getText().isEmpty()
                && !postcode.getText().isEmpty() && !address.getText().isEmpty()) {
            if (LoginService.getUserToAuthorize(username.getText()) == null) {
                UserModel userModel = new UserModel(username.getText(), PasswordHasherHelper.hashPassword(password.getText()), firstname.getText(), surname.getText(), PermissionEnum.User, city.getText() + " " + postcode.getText() + " " + address.getText());
                UserDAO.save(userModel);

                alertService = new AlertService();
                alertService.displaySuccesDialog("Pomyślnie zarejestrowano!");
                sceneManager = new SceneManagerService();
                sceneManager.changeScene("/Views/login.fxml");
            } else {
                alertService = new AlertService();
                alertService.displayWarinigDialog("Podany login jest zajęty!");
            }
        } else {
            alertService = new AlertService();
            alertService.displayWarinigDialog("Uzupełnij wszystkie pola!");
        }
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku umożliwiającego logowanie
     * @see LoginController
     * @see SceneManagerService
     * @param event
     */
    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        sceneManager = new SceneManagerService();
        sceneManager.changeScene("/Views/login.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }

}
