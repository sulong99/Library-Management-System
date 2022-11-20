package Controllers;

import Data.DAO.BookDAO;
import Models.BookModel;
import Models.CartModel;
import Services.AlertService;
import Services.SceneManagerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za kontrolę widoku z poziomu użytkownika zawierającego szczegółowe informacje dotyczące książek
 */
public class BookDetailsController implements Initializable, Controller {

    private SceneManagerService sceneManager;

    @FXML
    private Text Title;

    @FXML
    private Text Author;

    @FXML
    private Text Publisher;

    @FXML
    private Text Category;

    @FXML
    private Text ISBN;

    @FXML
    private Text ReleseYear;

    @FXML
    private Text Desctription;

    @FXML
    private Button addToCartButton;

    @FXML
    private Label cardLabel;

    @FXML
    private Button goToCartButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button goToPanelButton1;

    @FXML
    private Button sendAddRequestButton;

    @FXML
    private Button borrowHistoryButton;

    private CartModel cart;
    private AlertService alertService;
    private BookModel book;

    /**
     * Metoda umożliwiająca dodanie książki do koszyka
     * W przypadku pomyślnego dodania, jak i w sytuacji błędu wyświetlane są stosowne komunikaty
     * @see CartModel
     * @see CartViewController
     * @see AlertService
     * @param event
     */
    @FXML
    void addToCart(ActionEvent event) {
        try {
            cart.addBook(book);
            alertService.displaySuccesDialog("Dodano do koszyka!");
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Coś poszło nie tak!");
        }
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku wypożyczonych książek
     * @see BorrowedBooksController
     * @param event
     */
    @FXML
    void goToBorrowHistory(ActionEvent event) {
        sceneManager.changeScene("/Views/BorrowedBooksView.fxml");
    }

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
     * Metoda odpowiadająca za przejscie do widoku głównego panelu użytkownika
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
     * Metoda odpowiadająca za przejscie do widoku formularza o dodanie książki
     * @see AddRequestController
     * @param event
     */
    @FXML
    void sendAddRequest(ActionEvent event) {
        sceneManager.changeScene("/Views/AddRequestView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        cart = new CartModel(SceneManagerService.loggedUser.getUserId());
        alertService = new AlertService();

    }

    @Override
    public void handleParam(int param) {
        try {
            book = BookDAO.get("SELECT DISTINCT Books.Books_id, Books.ISBN, Books.Title, Books.Description, CONCAT(Authors.FirstName, ' ', Authors.SecondName) AS 'Authors', Books.Category, Books.Publisher, Books.Year, Books.`is_taken` FROM Books LEFT JOIN Authors_Books ON Authors_Books.Book_id = Books.Books_id LEFT JOIN Authors ON Authors.Author_id = Authors_Books.Author_id WHERE Books.Books_id = " + param + "");
            Title.setText(Title.getText() + " " + book.getTitle());
            Author.setText(Author.getText() + " " + book.getAuthor());
            Publisher.setText(Publisher.getText() + " " + book.getPublisher());
            Category.setText(Category.getText() + " " + book.getCategory());
            ISBN.setText(ISBN.getText() + " " + book.getISBN());
            ReleseYear.setText(ReleseYear.getText() + " " + book.getReleaseYear());
            Desctription.setText(Desctription.getText() + " " + book.getDescription());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
