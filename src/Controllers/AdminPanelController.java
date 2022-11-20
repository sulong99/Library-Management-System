package Controllers;

import Data.DAO.BookDAO;
import Data.DAO.BookRentalViewDAO;
import Models.BookModel;
import Models.BookRentalViewModel;
import Services.AlertService;
import Services.SceneManagerService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


/**
 * Klasa odpowiada za kontrolę widoku obsługującego panel administratora
 */
public class AdminPanelController implements Initializable {

    private SceneManagerService sceneManager;
    private AlertService alertService;
    private List<BookModel> books;
    private List<BookRentalViewModel> rentals;

    @FXML
    private TextField searchField;

    @FXML
    private Button DetailsButton;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab BooksTab;

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
    private TableColumn<?, ?> availableColumn;

    @FXML
    private Tab borrowedTab;

    @FXML
    private TableView<BookRentalViewModel> borrowedbooksTable;

    @FXML
    private TableColumn<?, ?> TitleColumn1;

    @FXML
    private TableColumn<?, ?> authorColumn1;

    @FXML
    private TableColumn<?, ?> CategoryColumn1;

    @FXML
    private TableColumn<?, ?> publishingHouseColumn1;

    @FXML
    private TableColumn<?, ?> ISBNColumn1;

    @FXML
    private TableColumn<?, ?> BorrowDateColumn;

    @FXML
    private TableColumn<?, ?> BorrowerColumn;

    @FXML
    private Button DeleteBookButton;

    @FXML
    private Button AddBookButton1;

    @FXML
    private Label cardLabel;

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
     * Metoda umożliwiająca przejście do formularza dodania książki
     * @see SceneManagerService
     * @see AddBookController
     * @param event
     */
    @FXML
    void addBooksDetails(ActionEvent event) {

        sceneManager.changeScene("/Views/AddBookView.fxml");
    }

    /**
     * Metoda umożliwiająca usunięcie książki
     * Pomyślne usunięcie książki skutkuje wyświetleniem komunikatu potwierdzającego
     * Wciśnięcie przycisku bez uprzedniego wyboru książki stosuje wypisaniem stosownego komunikatu
     * @see BookModel
     * @see BookDAO
     * @see AlertService
     * @param event
     */
    @FXML
    void deleteBook(ActionEvent event) {
        try {
            BookModel book = booksTable.getSelectionModel().getSelectedItem();
            book.setIsTaken(true);
            BookDAO.update(book);
            books = BookDAO.getList("SELECT DISTINCT Books.Books_id, Books.ISBN, Books.Title, Books.Description, CONCAT(Authors.FirstName, ' ', Authors.SecondName) AS 'Authors', Books.Category, Books.Publisher, Books.Year, Books.`is_taken` FROM Books LEFT JOIN Authors_Books ON Authors_Books.Book_id = Books.Books_id LEFT JOIN Authors ON Authors.Author_id = Authors_Books.Author_id");
            displayInBooksTable(books);
            alertService.displaySuccesDialog("Pomyślnie usunięto książkę ze zbioru!");
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę!");
        }
    }

    /**
     *Metoda umożliwiająca przejście do widoku zapytań
     * @see InquiriesPanelController
     * @param event
     */
    @FXML
    void goToInquiries(ActionEvent event) {
        sceneManager.changeScene("/Views/InquiriesPanelView.fxml");
    }

    @FXML
    void goToLibraryCollection(ActionEvent event) {

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
     * Metoda umożliwiająca wylogowanie oraz przejście do widoku logowania
     * @see LoginController
     * @param event
     */
    @FXML
    void handleLogout(ActionEvent event) {

        sceneManager.changeScene("/Views/login.fxml");
    }

    @FXML
    void showBooksDetails(ActionEvent event) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();
        books = BookDAO.getList("SELECT DISTINCT Books.Books_id, Books.ISBN, Books.Title, Books.Description, CONCAT(Authors.FirstName, ' ', Authors.SecondName) AS 'Authors', Books.Category, Books.Publisher, Books.Year, Books.`is_taken` FROM Books LEFT JOIN Authors_Books ON Authors_Books.Book_id = Books.Books_id LEFT JOIN Authors ON Authors.Author_id = Authors_Books.Author_id");
        rentals = BookRentalViewDAO.getList("SELECT DISTINCT br.BookRentals_id, b.Title, b.`is_taken`, CONCAT(a.FirstName,' ',a.SecondName) AS 'Author', CONCAT(u.FirstName,' ', u.LastName) AS 'User', b.Category, b.Publisher, b.ISBN, br.DateOfBorrow, br.DateOfReturn " +
                "FROM BookRentals_Users bru " +
                "INNER JOIN Users u ON u.Users_id=bru.User_id " +
                "INNER JOIN BookRentals br ON br.BookRentals_id=bru.BookRentals_id " +
                "INNER JOIN Books b ON b.Books_id=br.Book_id " +
                "INNER JOIN Authors_Books ab ON ab.Book_id=b.Books_id " +
                "INNER JOIN Authors a ON a.Author_id=ab.Author_id " +
                "WHERE br.DateOfReturn IS NULL ORDER BY br.DateOfBorrow");
        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        if (tabPane.getSelectionModel().getSelectedIndex() != 0) {
                            AddBookButton1.setVisible(false);
                            DeleteBookButton.setVisible(false);
                        } else {
                            AddBookButton1.setVisible(true);
                            DeleteBookButton.setVisible(true);
                        }
                    }
                });

        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                doSearch();
            }
        });

        displayInBooksTable(books);
        displayInBorrowedbooksTable(rentals);
    }

    public void doSearch() {
        String toFind = searchField.getText();
        List<BookModel> books = new ArrayList<>();
        for (BookModel c : this.books) {
            if (c.getTitle().toLowerCase().contains(toFind.toLowerCase()) || c.getCategory().toLowerCase().contains(toFind.toLowerCase())
                    || c.getPublisher().toLowerCase().contains(toFind.toLowerCase())) {
                books.add(c);
            }
        }
        displayInBooksTable(books);
    }

    public void displayInBooksTable(List<BookModel> books) {
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishingHouseColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        availableColumn.setCellValueFactory(new PropertyValueFactory<>("isAvailableStr"));

        booksTable.setItems(FXCollections.observableArrayList(books));
    }

    public void displayInBorrowedbooksTable(List<BookRentalViewModel> books) {
        TitleColumn1.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn1.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishingHouseColumn1.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        CategoryColumn1.setCellValueFactory(new PropertyValueFactory<>("category"));
        ISBNColumn1.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        BorrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDateOfBorrow"));
        BorrowerColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        borrowedbooksTable.setItems(FXCollections.observableArrayList(books));
    }
}
