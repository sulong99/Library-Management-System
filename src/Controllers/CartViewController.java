package Controllers;

import Data.DAO.CartDAO;
import Data.DAO.SettingsDAO;
import Models.BookModel;
import Models.CartModel;
import Models.SettingsModel;
import Services.AlertService;
import Services.CartService;
import Services.SceneManagerService;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Klasa odpowiadająca za widok i obsługę koszyka
 */
public class CartViewController implements Initializable {

    private SceneManagerService sceneManager;
    private List<BookModel> books;
    private AlertService alertService;

    @FXML
    private Button borrowBookButton;

    @FXML
    private TableView<BookModel> booksTable;

    @FXML
    private TableColumn<?, ?> TitleColumn;

    @FXML
    private TableColumn<?, ?> authorColumn;

    @FXML
    private TableColumn<?, ?> CategoryColumn;

    @FXML
    private TableColumn<?, ?> publishingHouseColumn;

    @FXML
    private TableColumn<?, ?> ISBNColumn;

    @FXML
    private Button removeFromCartButton;

    @FXML
    private Button clearCartButton;

    @FXML
    private Label cardLabel;

    @FXML
    private Button goToPanelButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button sendAddRequestButton;

    @FXML
    private Button borrowHistoryButton;

    /**
     * Metoda odpowiadająca za wypożyczenie książęk
     * W przypadku błędu związanego z wypożeczniem wyświetlany jest stosowny komunikat
     * W przypadku poprawnie wypożyczonej książki zwracany jest komunikat potwierdzający
     * @see AlertService
     * @see CartModel
     * @see CartService
     * @see SettingsDAO
     * @see SettingsModel
     * @param event
     */
    @FXML
    void borrowBook(ActionEvent event) {
        try {
            SettingsModel settingsModel = SettingsDAO.get("SELECT * FROM `Settings` ORDER BY Settings_id DESC LIMIT 1");
            CartService.borrowBooks(CartModel.books, settingsModel.getSettingsId());

            CartModel.clearCart();
            displayInBooksTable(CartModel.books);
            alertService.displaySuccesDialog("Pomyślnie wypożyczono!");
        } catch (Exception ex) {
            alertService.displayExceptionAlertDialog("Nie udało się wypożyczyć!");
            ex.printStackTrace();
        }
    }

    /**
     * Metoda odpowiadająca za czyszczenie koszyka
     * @see CartModel
     * @param event
     */
    @FXML
    void clearCart(ActionEvent event) {

        CartModel.clearCart();
        displayInBooksTable(CartModel.books);
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku wypożyczonych książęk
     * @see BorrowedBooksController
     * @param event
     */
    @FXML
    void goToBorrowHistory(ActionEvent event) {
        sceneManager.changeScene("/Views/BorrowedBooksView.fxml");
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
     * Metoda odpowiadająca za wylogowanie oraz przejście do panelu logowania
     * @see LoginController
     * @param event
     */
    @FXML
    void handleLogout(ActionEvent event) {
        sceneManager.changeScene("/Views/login.fxml");
    }

    /**
     * Metoda odpowiadająca za usunięcie z koszyka dodanej i wybranej wczesniej ksiazki
     * W przypadku błędu związanego z usunięciem wyświetlany jest stosowny komunikat
     * W przypadku poprawnie usuniętej z koszyka książki zwracany jest komunikat potwierdzający
     * @see CartModel
     * @see CartDAO
     * @see AlertService
     * @param event
     */
    @FXML
    void removeFromCart(ActionEvent event) {
        try {
            CartModel.removeBook(booksTable.getSelectionModel().getSelectedIndex());
            CartDAO.delete(booksTable.getSelectionModel().getSelectedItem().getId());
            displayInBooksTable(CartModel.books);
            alertService.displaySuccesDialog("Usunięto z koszyka!");
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę do usuniecia!");
        }
    }

    /**
     * Metoda odpowiadająca za zmianę widoku na widok umożliwiający wysłanie zapytania o dodanie nowej książki do zbioru.
     * @param event
     * @see AddRequestController
     */
    @FXML
    void sendAddRequest(ActionEvent event) {
        sceneManager.changeScene("/Views/AddRequestView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sceneManager = new SceneManagerService();
        alertService = new AlertService();
        CartModel.books = CartService.fillCart();

        displayInBooksTable(CartModel.books);
    }

    public void displayInBooksTable(List<BookModel> books) {
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishingHouseColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        books = CartModel.books;
        booksTable.setItems(FXCollections.observableArrayList(books));
    }


}
