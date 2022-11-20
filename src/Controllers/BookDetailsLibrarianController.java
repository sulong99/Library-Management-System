/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Data.DAO.BookDAO;
import Data.DAO.RequestDAO;
import Models.BookModel;
import Models.RequestModel;
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
 * Klasa odpowiada za kontrolę widoku z poziomu bibliotekarza zawierającego szczegółowe informacje dotyczące książek
 */
public class BookDetailsLibrarianController implements Initializable, Controller {

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
    private Button sendDeleteRequestButton;
    @FXML
    private Label cardLabel;
    @FXML
    private Button logoutButton;
    @FXML
    private Button goToLibrarianPanelButton;
    @FXML
    private Button sendAddRequestButton;
    @FXML
    private Button borrowHistoryButton;
    private SceneManagerService sceneManager;
    private AlertService alertService;
    private BookModel book;


    /**
     * Metoda umożliwiająca bibliotekarzowi wysłanie prośby o usunięcie książki do administratora
     * Skuteczne wysłanie skutku wyświetleniem stosownego komunikatu
     * Wciśnięcie guzika bez uprzedniego wybrania książki skutkuje wyświetleniem komunikatu błędu
     * @see RequestDAO
     * @see RequestModel
     * @see AlertService
     * @param event
     */
    @FXML
    private void sendDeleteRequest(ActionEvent event) {
        try {
            RequestModel requestModel = new RequestModel(book.getTitle(), book.getAuthor(), book.getCategory(), "Zniszczenie", false, SceneManagerService.loggedUser.getUserId());
            RequestDAO.save(requestModel);
            alertService.displaySuccesDialog("Wysłano prośbę do administratora!");
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę!");
        }
    }

    /**
     * Metoda odpowiadająca za wylogowanie oraz przejście do panelu logowania
     * @see LoginController
     * @param event
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        sceneManager.changeScene("/Views/login.fxml");
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku panelu głównego bibliotekarza
     * @see LibrarianPanelController
     * @param event
     */
    @FXML
    private void goToLibrarianPanel(ActionEvent event) {
        sceneManager.changeScene("/Views/LibrarianPanelView.fxml");
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku formularza o dodanie książki
     * @see AddRequestLibrarianController
     * @param event
     */
    @FXML
    private void sendAddRequest(ActionEvent event) {
        sceneManager.changeScene("/Views/AddRequestLibrarianView.fxml");
    }

    /**
     * Metoda odpowiadająca za przejscie do widoku wypożyczonych książek
     * @see BorrowedBooksController
     * @param event
     */
    @FXML
    private void goToBorrowHistory(ActionEvent event) {
        sceneManager.changeScene("/Views/BorrowedBooksView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
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
