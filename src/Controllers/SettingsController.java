package Controllers;

import Data.DAO.SettingsDAO;
import Data.DAO.UserDAO;
import Models.SettingsModel;
import Services.AlertService;
import Services.Helpers.PasswordHasherHelper;
import Services.ReportService;
import Services.SceneManagerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.math.BigDecimal;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za kontrolę widoku obsługującego ustawienia
 */
public class SettingsController implements Initializable {

    private SceneManagerService sceneManager;
    private AlertService alertService;
    @FXML
    private TextField oldPass;
    @FXML
    private TextField newPass;
    @FXML
    private TextField newPassRep;
    @FXML
    private TabPane tabPane;

    @FXML
    private Tab PasswordTab;

    @FXML
    private Button ChangePasswordButton;

    @FXML
    private Tab EditionTab;

    @FXML
    private Button goToLibrarianPanelButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button changePenaltyButton;

    @FXML
    private TextField daysForReturn;

    @FXML
    private TextField penalty;

    @FXML
    private TextField penaltyForDayOfDelay;

    /**
     * Metoda umożliwiająca zmianę hasła
     * W przypadku błędu związanego ze zmianą hasła wyświetlany jest stosowny komunikat
     * W przypadku poprawnej zmiany hasła zwracany jest komunikat potwierdzający
     * @see PasswordHasherHelper
     * @see SceneManagerService
     * @see AlertService
     * @param event
     */
    @FXML
    void ChangePassword(ActionEvent event) {
        try {
            boolean isPasswordMatch = PasswordHasherHelper.isPasswordMatch(oldPass.getText(), SceneManagerService.loggedUser.getPassword());
            if (isPasswordMatch && newPass.getText().equals(newPassRep.getText())) {

                SceneManagerService.loggedUser.setPassword(PasswordHasherHelper.hashPassword(newPass.getText()));
                UserDAO.update(SceneManagerService.loggedUser);

                alertService.displaySuccesDialog("Hasło zostało zmienione!");
            } else {
                alertService.displayWarinigDialog("Nie udało się zmienić hasła! sprawdź wpisane wartości ");
            }
        } catch (Exception ex) {
            alertService.displayExceptionAlertDialog("Wystąpił błąd!");
            ex.printStackTrace();
        }
    }

    /**
     * Metoda umożliwiająca zmianę wyskości kar
     * W przypadku błędu związanego ze zmianą kar wyświetlany jest stosowny komunikat
     * W przypadku poprawnej zmiany kar zwracany jest komunikat potwierdzający
     * @see SettingsModel
     * @see SettingsDAO
     * @see AlertService
     * @param event
     */
    @FXML
    void ChangePenalty(ActionEvent event) {
        if (daysForReturn.getText().isEmpty() || penalty.getText().isEmpty() || penaltyForDayOfDelay.getText().isEmpty()) {
            alertService.displayExceptionAlertDialog("Proszę uzupełnić wszystkie pola!");
        } else {
            try {
                SettingsDAO.save(new SettingsModel(Integer.valueOf(daysForReturn.getText()), BigDecimal.valueOf(Double.valueOf(penalty.getText())), BigDecimal.valueOf(Double.valueOf(penaltyForDayOfDelay.getText()))));
                alertService.displaySuccesDialog("Pomyślnie zaktualizowano!");
            } catch (Exception ex) {
                alertService.displayExceptionAlertDialog("Podano błędne wartości!");
            }
        }
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku panelu głównego bibliotekarza
     * @see LibrarianPanelController
     * @param event
     */
    @FXML
    void goToLibrarianPanel(ActionEvent event) {
        sceneManager.changeScene("/Views/LibrarianPanelView.fxml");
    }

    /**
     * Metoda umożliwiająca wylogowanie oraz przejście do widoku logowania
     * @see LoginController
     * @param event
     */
    @FXML
    void handleLogout(ActionEvent event) {
        sceneManager.changeScene("/Views/login.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();
    }

}
