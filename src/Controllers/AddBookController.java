package Controllers;

import Data.DAO.AuthorDAO;
import Data.DAO.BookDAO;
import Data.DAO.HelperDAO;
import Models.AuthorModel;
import Models.BookModel;
import Services.AlertService;
import Services.SceneManagerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za kontrolę widoku dodawania książki.
 */
public class AddBookController implements Initializable {

    private SceneManagerService sceneManager;
    private AlertService alertService;

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
    private Button addBookButton;

    @FXML
    private Button returnButton;

    @FXML
    private TextField title;

    @FXML
    private TextField publisher;

    @FXML
    private TextField category;

    @FXML
    private TextField isbn;

    @FXML
    private Text Description;

    @FXML
    private TextField description;

    @FXML
    private TextField releseYear;

    @FXML
    private TextField author;

    /**
     * Metoda odpowiadająca za dodawanie książki.
     * Aby metoda zadziałała poprawnie wymagane jest uzupełnienie wszystkich pól.
     * @param event
     */
    @FXML
    void addBook(ActionEvent event) {
        if (title.getText().isEmpty() || publisher.getText().isEmpty() || category.getText().isEmpty()
                || description.getText().isEmpty() || releseYear.getText().isEmpty() || isbn.getText().isEmpty()) {
            alertService.displayExceptionAlertDialog("Uzupełnij wszystkie pola!");
        } else {
            BookModel bookModel = new BookModel(title.getText(), publisher.getText(), category.getText(), isbn.getText(), description.getText(), releseYear.getText(), false);
            BookDAO.save(bookModel);
            insertAuthors();
            alertService.displaySuccesDialog("Pomyślnie dodano książkę!");
        }
    }

    /**
     * Metoda odpowiadająca za dodawanie autora.
     */
    private void insertAuthors() {
        int bookId = BookDAO.get("SELECT * FROM Books ORDER BY Books_ID DESC LIMIT 1").getId();

        if (author.getText().contains(",")) {
            String[] authors = author.getText().split(",");
            for (String i : authors) {
                String[] author = splitBySpace(i);
                AuthorModel authorModel = getAuthorByName(author[0], author[1]);
                if (authorModel == null) {
                    AuthorDAO.save(new AuthorModel(author[0], author[1]));
                    authorModel = AuthorDAO.get("SELECT * FROM Authors ORDER BY Author_id DESC LIMIT 1");
                }
                HelperDAO.executeQuery("INSERT INTO `Authors_Books`(`Author_id`, `Book_id`) VALUES (" + authorModel.getAuthorId() + "," + bookId + ")");
            }
        } else {
            String[] authorTab = splitBySpace(author.getText());
            AuthorModel authorModel = new AuthorModel();
            if (authorTab.length > 1){
                authorModel = getAuthorByName(authorTab[0], authorTab[1]);
            }else {
                authorModel = getAuthorByName(authorTab[0], "");
            }

            if (authorModel == null) {
                if (authorTab.length > 1){
                    AuthorDAO.save(new AuthorModel(authorTab[0], authorTab[1]));
                }else {
                    AuthorDAO.save(new AuthorModel(authorTab[0], ""));
                }
                authorModel = AuthorDAO.get("SELECT * FROM Authors ORDER BY Author_id DESC LIMIT 1");
            }
            HelperDAO.executeQuery("INSERT INTO `Authors_Books`(`Author_id`, `Book_id`) VALUES (" + authorModel.getAuthorId() + "," + bookId + ")");
        }

    }

    private String[] splitBySpace(String toSplit) {
        return toSplit.trim().split(" ");
    }

    private AuthorModel getAuthorByName(String firstName, String lastName) {
        return AuthorDAO.get("SELECT * FROM Authors WHERE FirstName = '" + firstName.trim() + "' AND SecondName = '" + lastName.trim() + "'");
    }

    @FXML
    void handleReturn(ActionEvent event) {
        sceneManager.changeScene("/Views/AdminPanelView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();
    }
}

