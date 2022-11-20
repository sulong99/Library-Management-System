/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Data.DAO.BookDAO;
import Data.DAO.BookRentalViewDAO;
import Data.DAO.RequestDAO;
import Models.BookModel;
import Models.BookRentalViewModel;
import Models.RequestModel;
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
 * Klasa odpowiada za kontrolę widoku obsługującego panel bibliotekarza
 */
public class LibrarianPanelController implements Initializable {

    @FXML
    private TextField searchField;
    @FXML
    private Button returnBookButton;
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
    private TableColumn<?, ?> userColumn;
    @FXML
    private TableColumn<?, ?> BorrowDateColumn;
    @FXML
    private Button sendDeleteRequestButton;
    @FXML
    private Label cardLabel;
    @FXML
    private Button goToRaportsButton;
    @FXML
    private Button SettingsButton;
    @FXML
    private Button logoutButton;
    @FXML
    private Button sendAddRequestButton;
    @FXML
    private Button borrowHistoryButton;
    private SceneManagerService sceneManager;
    private AlertService alertService;
    private List<BookModel> books;
    private List<BookRentalViewModel> rentals;


    @FXML
    private void returnBook(ActionEvent event) {
    }

    /**
     * Metoda umożliwiająca podgląd informacji o książce.
     * W przypadku nie wybrania książki otrzymamy komunikat o braku wyboru, natomiast w przypadku udanej operacji wyświetli się okienko z informacjami o danej książce.
     * @param event
     * @see BookModel
     * @see AlertService
     * @see SceneManagerService
     */
    @FXML
    private void showBooksDetails(ActionEvent event) {
        try {
            BookModel book = booksTable.getSelectionModel().getSelectedItem();

            sceneManager.changeScene("/Views/BookDetailsLibrarianView.fxml", book.getId());
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę!");
        }
    }

    /**
     * Metoda umożliwiająca wysłanie prośby o usunięcie książki
     * W przypadku nie wybrania książki otrzymamy komunikat o braku wyboru, natomiast w przypadku udanej operacji wyświetli komunikat potwierdzający wysłanie zapytania
     * @param event
     * @see RequestModel
     * @see RequestDAO
     * @see BookModel
     * @see AlertService
     */
    @FXML
    private void sendDeleteRequest(ActionEvent event) {
        try {
            BookModel book = books.get(booksTable.getSelectionModel().getSelectedIndex());
            RequestModel requestModel = new RequestModel(book.getTitle(), book.getAuthor(), book.getCategory(), Integer.valueOf(book.getId()).toString(), false, SceneManagerService.loggedUser.getUserId());
            RequestDAO.save(requestModel);
            alertService.displaySuccesDialog("Wysłano prośbę do administratora!");
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę!");
        }
    }

    /**
     * Metoda umożliwiająca przejście do widoku ustawień
     * @see SettingsController
     * @param event
     */
    @FXML
    void goToSettings(ActionEvent event) {
        sceneManager.changeScene("/Views/Settings.fxml");
    }

    /**
     * Metoda umożliwiająca przejście do widoku raportów
     * @see ReportsPanelController
     * @param event
     */
    @FXML
    private void goToRaports(ActionEvent event) {
        sceneManager.changeScene("/Views/ReportsPanelView.fxml");
    }

    /**
     * Metoda umożliwiająca wylogowanie oraz przejście do widoku logowania
     * @see LoginController
     * @param event
     */
    @FXML
    private void handleLogout(ActionEvent event) {
        sceneManager.changeScene("/Views/login.fxml");
    }

    /**
     * Metoda odpowiadająca za zmianę widoku na widok umożliwiający wysłanie zapytania o dodanie nowej książki do zbioru.
     * @param event
     * @see AddRequestController
     */
    @FXML
    private void sendAddRequest(ActionEvent event) {
        sceneManager.changeScene("/Views/AddRequestLibrarianView.fxml");
    }

    /**
     * Metoda odpowiadająca ze zmianę widoku na historię wypożyczeń
     * @param event
     * @see BorrowedBooksController
     */
    @FXML
    private void goToBorrowHistory(ActionEvent event) {
        sceneManager.changeScene("/Views/BorrowedBooksView.fxml");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alertService = new AlertService();
        sceneManager = new SceneManagerService();

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
                        if (tabPane.getSelectionModel().getSelectedIndex() == 0) {
                            DetailsButton.setVisible(true);
                            sendDeleteRequestButton.setVisible(true);
                        } else {
                            DetailsButton.setVisible(false);
                            sendDeleteRequestButton.setVisible(false);
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
        userColumn.setCellValueFactory(new PropertyValueFactory<>("user"));

        borrowedbooksTable.setItems(FXCollections.observableArrayList(books));
    }

    public void doSearch() {
        {
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
    }
}
