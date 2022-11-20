package Controllers;

import Models.CartModel;
import Models.UserModel;
import Services.AlertService;
import Services.CartService;
import Services.Helpers.PasswordHasherHelper;
import Services.LoginService;
import Services.SceneManagerService;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za kontrolę widoku obsługującego logowanie
 */
public class LoginController implements Initializable {
    @FXML
    private JFXTextField username;
    @FXML
    private JFXPasswordField password;
    private AlertService alertService;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        alertService = new AlertService();
    }

    /**
     * Metoda umożliwiająca poprawne logowanie użytkownika oraz przejście do panelu zgodnego z uprawnieniami użytkownika
     * W przypadku błędnie wprowadzonych danych wyświetlany jest stosowny komunikat
     * W przypadku poprawnie podanych danych logowania, zostajemy przeniesieni do odpowiedniego panelu
     * @param event
     * @throws SQLException
     * @throws ClassNotFoundException
     * @see LoginService
     * @see SceneManagerService
     * @see PasswordHasherHelper
     * @see UserModel
     * @see CartModel
     */
    @FXML
    private void handleLoginButtonAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String login = username.getText();
        String pass = password.getText();

        UserModel userModel = LoginService.getUserToAuthorize(login);
        SceneManagerService sceneManager = new SceneManagerService();

        boolean isPasswordMatch = PasswordHasherHelper.isPasswordMatch(pass, userModel.getPassword());
        if (userModel != null && isPasswordMatch) {
            switch (userModel.getPermissions().toString()) {
                case "User":
                    SceneManagerService.loggedUser = userModel;
                    CartModel.books = CartService.fillCart();
                    sceneManager.changeScene("/Views/UserPanelView.fxml");
                    break;
                case "Worker":
                    SceneManagerService.loggedUser = userModel;
                    sceneManager.changeScene("/Views/LibrarianPanelView.fxml");
                    break;
                case "Administrator":
                    SceneManagerService.loggedUser = userModel;
                    sceneManager.changeScene("/Views/AdminPanelView.fxml");
                    break;
            }
        } else
            alertService.displayWarinigDialog("Podane dane są nieprawiłowe!");
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku umożliwiającego rejestrajcę
     * @see RegisterController
     * @see SceneManagerService
     * @param event
     */
    @FXML
    private void handleRegisterButtonAction(ActionEvent event) throws ClassNotFoundException {
        SceneManagerService sceneManager = new SceneManagerService();
        sceneManager.changeScene("/Views/register.fxml");

    }

    @FXML
    private void handleCancelButtonAction(ActionEvent event) {
        System.exit(0);
    }

    private void closeStage() {
        ((Stage) username.getScene().getWindow()).close();
    }

}
