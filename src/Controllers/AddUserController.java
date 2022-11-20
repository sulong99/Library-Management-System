package Controllers;

import Data.DAO.UserDAO;
import Models.PermissionEnum;
import Models.UserModel;
import Services.AlertService;
import Services.Helpers.PasswordHasherHelper;
import Services.Helpers.PermissionsTranslationHelper;
import Services.LoginService;
import Services.SceneManagerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Klasa odpowiada za kontrolę widoku obsługującego dodawanie użytkowników
 */
public class AddUserController implements Initializable, Controller {

    private SceneManagerService sceneManager;
    private AlertService alertService;
    private int param;

    @FXML
    private Button addUserButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField username;

    @FXML
    private TextField firstname;

    @FXML
    private TextField surname;

    @FXML
    private TextField address;

    @FXML
    private ChoiceBox<String> permission;

    @FXML
    private PasswordField password;

    /**
     * Metoda dodająca użytkownika z nadaniem odpowiednich uprawnień
     * W przypadku niepoprawnego uzupełnienia zwracany jest stosowny komunikat
     * @see UserModel
     * @see UserDAO
     * @see AlertService
     * @param event
     */
    @FXML
    void addUser(ActionEvent event) {
        if (!username.getText().isEmpty() && !password.getText().isEmpty() && !firstname.getText().isEmpty() && !surname.getText().isEmpty()
                && !address.getText().isEmpty()) {
            UserModel userModel = new UserModel(username.getText(), PasswordHasherHelper.hashPassword(password.getText()), firstname.getText(), surname.getText(), PermissionsTranslationHelper.translatePermissionToEn(permission.getValue()), address.getText());
            if (param == 0 && LoginService.getUserToAuthorize(username.getText()) == null) {
                UserDAO.save(userModel);
            } else if (param != 0) {
                userModel.setUserId(param);
                UserDAO.update(userModel);
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
     * Metoda odpowiada za przejście do widoku szczegółowych danych na temat użytkownika
     * @see UsersDetailsPanelController
     * @param event
     */
    @FXML
    void handleReturn(ActionEvent event) {
        sceneManager.changeScene("/Views/UsersDetailsPanelView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();

        permission.getItems().setAll(getPermissionsToDisplay());
    }

    /**
     * Metoda sprawdza czy dany użytkownik posiada uprawnienia administratora konieczne do wykonywania operacji
     * @see PermissionsTranslationHelper
     * @return
     */
    private List<String> getPermissionsToDisplay() {
        List<String> translations = new ArrayList<>();
        for (PermissionEnum p : PermissionEnum.values()) {
            translations.add(PermissionsTranslationHelper.translatePermission(p.toString()));
        }
        return translations;
    }


    /**
     * Metoda odpowiada za aktualizację danych użytkownika
     * Metoda pobiera informacje z bazy danych, a następnie umożliwia ich modyfikację
     * @see UserModel
     * @see UserDAO
     * @param param
     */
    @Override
    public void handleParam(int param) {
        if (param != 0) {
            this.param = param;
            addUserButton.setText("Aktualizuj");
            UserModel userModel = UserDAO.get("SELECT * FROM `Users` WHERE Users_id = " + param);
            username.setText(userModel.getLogin());
            firstname.setText(userModel.getFirstName());
            surname.setText(userModel.getLastName());
            address.setText(userModel.getAddress());
            permission.setValue(userModel.getPermissionTranslation());
        }
    }
}

