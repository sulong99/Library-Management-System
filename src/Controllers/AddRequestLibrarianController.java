/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * Klasa odpowiada za kontrolę widoku obsługującego wysyłanie zapytania o dodanie książki z poziomu bibliotekarza
 */
public class AddRequestLibrarianController implements Initializable {

    @FXML
    private Button sendRequestButton;
    @FXML
    private Label cardLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button goToLibrarianPanelButton;
    @FXML
    private JFXTextField title;

    @FXML
    private JFXTextField author;

    @FXML
    private JFXTextField category;

    @FXML
    private JFXTextField description;
    private SceneManagerService sceneManager;
    private AlertService alertService;



    /**
     * Metoda wysyłająca zapytanie o dodanie książki z poziomu bibliotekarza
     * W przypadku nieuzupełnienia wszystkich danych wyświetlany jest stosowny komunikat
     * W przypadku poprawnie wysłanego zapytania zwaracany jest komunikat
     * @see UserPanelController
     * @see RequestModel
     * @see RequestDAO
     * @see AlertService
     * @see SceneManagerService
     * @param event
     */
    @FXML
    private void sendRequest(ActionEvent event) {
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
            } else
                alertService.displayWarinigDialog("Uzupełnij wszystkie pola!");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Metoda odpowiadająca za wylogowanie bibliotekarza oraz przejscie do widoku logowania
     * @see LoginController
     * @param event
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        sceneManager.changeScene("/Views/login.fxml");
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku panelu głównego użytkownika
     * @see LibrarianPanelController
     * @param event
     */
    @FXML
    private void goToLibrarianPanel(ActionEvent event) {
        sceneManager.changeScene("/Views/LibrarianPanelView.fxml");
    }

    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();
    }
}
