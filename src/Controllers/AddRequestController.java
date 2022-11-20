package Controllers;

import Data.DAO.RequestDAO;
import Models.RequestModel;
import Services.AlertService;
import Services.SceneManagerService;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za kontrolę widoku obsługującego wysyłanie zapytania o dodanie książki z poziomu czytelnika
 */
public class AddRequestController implements Initializable {

    private SceneManagerService sceneManager;

    @FXML
    private Button sendRequestButton;

    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField author;

    @FXML
    private JFXTextField category;

    @FXML
    private JFXTextField description;


    @FXML
    private Label cardLabel;

    @FXML
    private Button goToCartButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button goToPanelButton1;

    private AlertService alertService;

    /**
     * Metoda odpowiadająca za przejscie do widoku koszyka
     * @see CartViewController
     * @param event
     */
    @FXML
    void goToCart(ActionEvent event) {
        sceneManager.changeScene("/Views/CartView.fxml");
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku panelu głównego użytkownika
     * @see UserPanelController
     * @param event
     */
    @FXML
    void goToPanel(ActionEvent event) {
        sceneManager.changeScene("/Views/UserPanelView.fxml");
    }

    /**
     * Metoda odpowiadająca za wylogowanie czytelnika oraz przejscie do widoku logowania
     * @see LoginController
     * @param event
     */
    @FXML
    void handleLogout(ActionEvent event) {
        sceneManager.changeScene("/Views/login.fxml");
    }

    /**
     * Metoda wysyłająca zapytanie o dodanie książki z poziomu czytelnika
     * W przypadku nieuzupełnienia wszystkich danych wyświetlany jest stosowny komunikat
     * W przypadku poprawnie wysłanego zapytania zwaracany jest komunikat oraz zostajemy przeniesieni do widoku panelu głównego użytkownika
     * @see UserPanelController
     * @see RequestModel
     * @see RequestDAO
     * @see AlertService
     * @see SceneManagerService
     * @param event
     */
    @FXML
    void sendRequest(ActionEvent event) {
        try {
            if (!title.getText().isEmpty() && !author.getText().isEmpty() && !category.getText().isEmpty() && !description.getText().isEmpty()) {
                RequestModel requestModel = new RequestModel();
                requestModel.setBookTitle(title.getText());
                requestModel.setBookAuthor(author.getText());
                requestModel.setCategory(category.getText());
                requestModel.setDescription(category.getText());
                requestModel.setAddRequest(true);
                requestModel.setUserId(SceneManagerService.loggedUser.getUserId());
                RequestDAO.save(requestModel);
                alertService.displaySuccesDialog("Wysłano zgłoszenie!");
                sceneManager.changeScene("/Views/UserPanelView.fxml");
            } else
                alertService.displayWarinigDialog("Uzupełnij wszystkie pola!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();
    }
}
