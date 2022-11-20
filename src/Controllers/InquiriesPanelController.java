package Controllers;

import Data.DAO.BookDAO;
import Data.DAO.RequestDAO;
import Models.BookModel;
import Models.RequestModel;
import Services.AlertService;
import Services.SceneManagerService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Klasa odpowiadająca za widok i obsługę zapytań do administratora
 */
public class InquiriesPanelController implements Initializable {

    private SceneManagerService sceneManager;
    private AlertService alertService;
    private List<RequestModel> requests;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab RequestBookTab;

    @FXML
    private TableView<RequestModel> addRequestTable;

    @FXML
    private TableColumn<?, ?> TitleColumn;

    @FXML
    private TableColumn<?, ?> AuthorColumn;

    @FXML
    private TableColumn<?, ?> CategoryColumn;

    @FXML
    private TableColumn<?, ?> DescriptionColumn;

    @FXML
    private TableColumn<?, ?> UserColumn;

    @FXML
    private TableColumn<?, ?> TypeRequest;

    @FXML
    private Button rejectRequestButton;

    @FXML
    private Button AcceptRequestButton1;

    @FXML
    private Button inquiriesButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button UsersButton;

    @FXML
    private Button libraryCollectionButton;

    @FXML
    private Button goToRaportsButton1;

    /**
     * Metoda odpowiadająca za możliwość zaakceptowania zapytania przez administratora
     * W przypadku błędu związanego z zaakceptowaniem wyświetlany jest stosowny komunikat
     * W przypadku poprawnie zaakceptowanego zapytania zwracany jest komunikat potwierdzający
     * @see RequestDAO
     * @see RequestModel
     * @see SceneManagerService
     * @see AlertService
     * @see BookDAO
     * @see BookModel
     * @param event
     */
    @FXML
    void acceptRequest(ActionEvent event) {
        try {
            RequestModel book = requests.get(addRequestTable.getSelectionModel().getSelectedIndex());
            if (book.isAddRequest()) {
                RequestDAO.delete(book);
                sceneManager.changeScene("/Views/AddBookView.fxml");
            } else {

                BookModel bookModel = BookDAO.get("SELECT * FROM Books WHERE Books_id = " + Integer.parseInt(book.getDescription()) + "");
                if(bookModel.getIsUnavailable()){
                    alertService.displayWarinigDialog("Nie można wykonać dopóki książka jest niedostępna!");
                }else{
                    bookModel.setIsTaken(true);
                    BookDAO.update(bookModel);
                    alertService.displaySuccesDialog("Zaakceptowano zapytanie!");
                }
            }
            RequestDAO.delete(book);
            requests.remove(book);
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę!");
        }
    }

    /**
     * Metoda umożliwiająca przejście do formularza dodania książki
     * @see AddBookController
     * @param event
     */
    @FXML
    void addBook(ActionEvent event) {
        sceneManager.changeScene("/Views/AddBookView.fxml");
    }

    @FXML
    void goToInquiries(ActionEvent event) {

    }

    /**
     * Metoda umożliwiająca przejście do widoku administratora
     * @see AdminPanelController
     * @param event
     */
    @FXML
    void goToLibraryCollection(ActionEvent event) {

        sceneManager.changeScene("/Views/AdminPanelView.fxml");
    }

    /**
     * Metoda umożliwiająca przejście do widoku raportów
     * @see ReportsPanelController
     * @param event
     */
    @FXML
    void goToRaports(ActionEvent event) {
        sceneManager.changeScene("/Views/ReportsPanelView.fxml");
    }

    /**
     * Metoda umożliwiająca przejście do widoku panelu głównego, zakładki czytelników
     * @see UsersDetailsPanelController
     * @param event
     */
    @FXML
    void goToUsersDetails(ActionEvent event) {
        sceneManager.changeScene("/Views/UsersDetailsPanelView.fxml");
    }

    /**
     * Metoda odpowiadająca za wylogowanie oraz przejście do panelu logowania
     * @see LoginController
     * @param event
     */
    @FXML
    void handleLogout(ActionEvent event) {
        sceneManager.changeScene("/Views/login.fxml");
    }

    /**
     * Metoda odpowiadająca za możliwość odrzucenia zapytania przez administratora
     * W przypadku błędu związanego z odrzuceniem wyświetlany jest stosowny komunikat
     * W przypadku poprawnie odrzuconego zapytania zwracany jest komunikat potwierdzający
     * @see RequestDAO
     * @see RequestModel
     * @see AlertService
     * @param event
     */
    @FXML
    void rejectRequest(ActionEvent event) {
        try {
            RequestModel book = requests.get(addRequestTable.getSelectionModel().getSelectedIndex());
            RequestDAO.delete(book);
            requests.remove(book);
            displayInAddRequestTable(requests);
            alertService.displaySuccesDialog("Odrzucono zapytanie!");
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();

        requests = RequestDAO.getList("SELECT * FROM `Requests`");

        displayInAddRequestTable(requests);
    }

    public void displayInAddRequestTable(List<RequestModel> requests) {
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("bookTitle"));
        AuthorColumn.setCellValueFactory(new PropertyValueFactory<>("bookAuthor"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        UserColumn.setCellValueFactory(new PropertyValueFactory<>("user"));
        TypeRequest.setCellValueFactory(new PropertyValueFactory<>("isAddRequesttxt"));

        addRequestTable.setItems(FXCollections.observableArrayList(requests));
    }


}
