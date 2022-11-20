package Controllers;

import Data.DAO.BookRentalViewDAO;
import Models.BookRentalViewModel;
import Models.PermissionEnum;
import Services.AlertService;
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
 * Klasa odpowiada za kontrolę widoku z wypożyczonymi książkami
 */
public class BorrowedBooksController implements Initializable {

    private SceneManagerService sceneManager;
    private AlertService alertService;

    @FXML
    private TableView<BookRentalViewModel> booksTable;

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
    private TableColumn<?, ?> borrowDateColumn;

    @FXML
    private TableColumn<?, ?> returnDateColumn;

    @FXML
    private Label cardLabel;

    @FXML
    private Button goToCartButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button sendAddRequestButton;

    @FXML
    private Button goToPanelButton1;
    private List<BookRentalViewModel> borrowedBooks;

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
     * @param event
     */
    @FXML
    void goToPanel(ActionEvent event) {
        if (SceneManagerService.loggedUser.getPermissions() == PermissionEnum.User) {
            sceneManager.changeScene("/Views/UserPanelView.fxml");
        } else if (SceneManagerService.loggedUser.getPermissions() == PermissionEnum.Worker) {
            sceneManager.changeScene("/Views/LibrarianPanelView.fxml");
        } else {
            sceneManager.changeScene("/Views/AdminPanelView.fxml");
        }
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
     * Metoda odpowiadająca za przejscie do widoku formularza o dodanie książki
     * @see AddRequestController
     * @param event
     */
    @FXML
    void sendAddRequest(ActionEvent event) {
        if (SceneManagerService.loggedUser.getPermissions() == PermissionEnum.User) {
            sceneManager.changeScene("/Views/AddRequestView.fxml");
        } else if (SceneManagerService.loggedUser.getPermissions() == PermissionEnum.Worker) {
            sceneManager.changeScene("/Views/AddRequestLibrarianView.fxml");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();

        if (SceneManagerService.loggedUser.getPermissions() != PermissionEnum.User) {
            goToCartButton.setVisible(false);
            borrowedBooks = BookRentalViewDAO.getList("SELECT DISTINCT br.BookRentals_id, b.Title, CONCAT(a.FirstName,' ',a.SecondName) AS 'Author', CONCAT(u.FirstName,' ', u.LastName) AS 'User', b.Category, b.Publisher, b.ISBN, br.DateOfBorrow, br.DateOfReturn " +
                    "FROM BookRentals_Users bru " +
                    "INNER JOIN Users u ON u.Users_id=bru.User_id " +
                    "INNER JOIN BookRentals br ON br.BookRentals_id=bru.BookRentals_id " +
                    "INNER JOIN Books b ON b.Books_id=br.Book_id " +
                    "INNER JOIN Authors_Books ab ON ab.Book_id=b.Books_id " +
                    "INNER JOIN Authors a ON a.Author_id=ab.Author_id " +
                    "WHERE br.DateOfReturn IS NOT NULL " +
                    "ORDER BY br.DateOfBorrow");
        } else {
            borrowedBooks = BookRentalViewDAO.getList("SELECT DISTINCT br.BookRentals_id, b.Title, CONCAT(a.FirstName,' ',a.SecondName) AS 'Author', CONCAT(u.FirstName,' ', u.LastName) AS 'User', b.Category, b.Publisher, b.ISBN, br.DateOfBorrow, br.DateOfReturn " +
                    "FROM BookRentals_Users bru " +
                    "INNER JOIN Users u ON u.Users_id=bru.User_id " +
                    "INNER JOIN BookRentals br ON br.BookRentals_id=bru.BookRentals_id " +
                    "INNER JOIN Books b ON b.Books_id=br.Book_id " +
                    "INNER JOIN Authors_Books ab ON ab.Book_id=b.Books_id " +
                    "INNER JOIN Authors a ON a.Author_id=ab.Author_id " +
                    "WHERE u.Users_id = " + SceneManagerService.loggedUser.getUserId() + " AND br.DateOfReturn IS NOT NULL " +
                    "ORDER BY br.DateOfBorrow");
        }

        displayInBorrowedbooksTable(borrowedBooks);
    }

    public void displayInBorrowedbooksTable(List<BookRentalViewModel> books) {
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishingHouseColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        borrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDateOfBorrow"));
        returnDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDateOfReturn"));

        booksTable.setItems(FXCollections.observableArrayList(books));
    }
}
