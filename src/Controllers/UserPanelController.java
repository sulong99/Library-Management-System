package Controllers;

import Data.DAO.BookDAO;
import Data.DAO.BookRentalDAO;
import Data.DAO.BookRentalViewDAO;
import Models.BookModel;
import Models.BookRentalModel;
import Models.BookRentalViewModel;
import Models.CartModel;
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
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

/**
 * Klasa odpowiadająca za widok panelu użytkownika
 */
public class UserPanelController implements Initializable {

    private SceneManagerService sceneManager;
    private CartModel cart;

    @FXML
    private TextField searchField;

    @FXML
    private Button returnBookButton;

    @FXML
    private Button DetailsButton;

    @FXML
    private Button borrowBookButton;

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
    private TableColumn<?, ?> publishingHouseColumn;

    @FXML
    private TableColumn<?, ?> ISBNColumn;
    @FXML
    private TableColumn<?, ?> CategoryColumn;

    @FXML
    private TableColumn<?, ?> dateColumn;

    @FXML
    private Tab borrowedTab;

    @FXML
    private TableView<BookRentalViewModel> borrowedbooksTable;

    @FXML
    private TableColumn<?, ?> TitleColumn1;

    @FXML
    private TableColumn<?, ?> authorColumn1;

    @FXML
    private TableColumn<?, ?> publishingHouseColumn1;

    @FXML
    private TableColumn<?, ?> CategoryColumn1;

    @FXML
    private TableColumn<?, ?> ISBNColumn1;

    @FXML
    private TableColumn<?, ?> BorrowDateColumn;

    @FXML
    private Label cardLabel;

    @FXML
    private Button goToCardButton;

    @FXML
    private Button logoutButton;

    @FXML
    private Button addToCartButton;

    @FXML
    private Button sendAddRequestButton;

    @FXML
    private Button borrowHistoryButton;

    List<BookModel> books = new ArrayList<>();
    List<BookRentalViewModel> borrowedBooks = new ArrayList<>();
    private AlertService alertService;

    @FXML
    void borrowBook(ActionEvent event) {

    }

    /**
     * Metoda odpowiadająca za dodanie książku do koszyka.
     * W przypadku użycia wyświetli się stosowny komunikat.
     * @param event
     * @see CartModel
     * @see BookModel
     * @see AlertService
     */
    @FXML
    void addToCart(ActionEvent event) {
        try {
            cart.addBook(books.get(booksTable.getSelectionModel().getSelectedIndex()));
            alertService.displaySuccesDialog("Dodano do koszyka!");
            prepareBooksToDisplay(books);
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę do dodania!");
        }
    }

    /**
     * Metoda odpowiadająca ze zmianę widoku na historię wypożyczeń
     * @param event
     * @see BorrowedBooksController
     */
    @FXML
    void goToBorrowHistory(ActionEvent event) {
        sceneManager.changeScene("/Views/BorrowedBooksView.fxml");
    }


    /**
     * Metoda odpowiadająca ze zmianę widoku na widok koszyka
     * @param event
     * @see CartViewController
     */
    @FXML
    void goToCart(ActionEvent event) {
        sceneManager.changeScene("/Views/CartView.fxml");
    }

    /**
     * Metoda odpowiadająca za wylogowanie użytkownika, przejście do panelu logowania.
     * @param event
     * @see LoginController
     */
    @FXML
    void handleLogout(ActionEvent event) {
        sceneManager.changeScene("/Views/login.fxml");
    }

    /**
     * Metoda odpowiadająca za zwrot wypożyczonej książki.
     * Metoda zmienia status książki na dostępny oraz dodaje informacje do tabeli zawierającej wypożyczenia.
     * Po wykonaniu operacji wyświetla się stosowny komunikat.
     * @param event
     * @see BookRentalDAO
     * @see BookRentalViewModel
     * @see BookRentalModel
     * @see BookDAO
     * @see BookModel
     * @see AlertService
     */
    @FXML
    void returnBook(ActionEvent event) {
        try {
            BookRentalViewModel book = borrowedbooksTable.getSelectionModel().getSelectedItem();
            BookRentalModel rental = BookRentalDAO.get("SELECT * FROM `BookRentals` WHERE BookRentals_id = " + book.getBookRentalsId() + "");
            rental.setDateOfReturn(new Date());
            BookModel returnBook = BookDAO.get("SELECT * FROM `Books` WHERE Books_id = " + rental.getBookId() + "");
            returnBook.setIsTaken(false);
            BookDAO.update(returnBook);
            BookRentalDAO.update(rental);

            borrowedBooks.remove(book);
            displayInBorrowedbooksTable(borrowedBooks);

            alertService.displaySuccesDialog("Pomyślnie zwrócono!");
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę!");
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

    /**
     * Metoda umożliwiająca podgląd informacji o książce.
     * W przypadku nie wybrania książki otrzymamy komunikat o braku wyboru, natomiast w przypadku udanej operacji wyświetli się okienko z informacjami o danej książce.
     * @param event
     * @see BookModel
     * @see AlertService
     * @see SceneManagerService
     */
    @FXML
    void showBooksDetails(ActionEvent event) {
        try {
            BookModel book = booksTable.getSelectionModel().getSelectedItem();

            sceneManager.changeScene("/Views/BookDetailsView.fxml", book.getId());
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać książkę!");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        alertService = new AlertService();
        sceneManager = new SceneManagerService();
        //Tymczasowe do testów koszyka
        cart = new CartModel(1);
        books = BookDAO.getList("SELECT DISTINCT Books.Books_id, Books.ISBN, Books.Title, Books.Description, Books.`is_taken`, CONCAT(Authors.FirstName, ' ', Authors.SecondName) AS 'Authors', Books.Category, Books.Publisher, Books.Year FROM Books LEFT JOIN Authors_Books ON Authors_Books.Book_id = Books.Books_id LEFT JOIN Authors ON Authors.Author_id = Authors_Books.Author_id WHERE Books.`is_taken` = 0");
        borrowedBooks = BookRentalViewDAO.getList("SELECT DISTINCT br.BookRentals_id, b.Title, CONCAT(a.FirstName,' ',a.SecondName) AS 'Author', CONCAT(u.FirstName,' ', u.LastName) AS 'User', b.Category, b.Publisher, b.ISBN, br.DateOfBorrow, br.DateOfReturn " +
                "FROM BookRentals_Users bru " +
                "INNER JOIN Users u ON u.Users_id=bru.User_id " +
                "INNER JOIN BookRentals br ON br.BookRentals_id=bru.BookRentals_id " +
                "INNER JOIN Books b ON b.Books_id=br.Book_id " +
                "INNER JOIN Authors_Books ab ON ab.Book_id=b.Books_id " +
                "INNER JOIN Authors a ON a.Author_id=ab.Author_id " +
                "WHERE u.Users_id = " + SceneManagerService.loggedUser.getUserId() + " AND br.DateOfReturn IS NULL " +
                "ORDER BY br.DateOfBorrow");
        addToCartButton.setVisible(true);
        returnBookButton.setVisible(false);

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        if (tabPane.getSelectionModel().getSelectedIndex() != 0) {
                            addToCartButton.setVisible(false);
                            returnBookButton.setVisible(true);
                        } else {
                            addToCartButton.setVisible(true);
                            returnBookButton.setVisible(false);
                        }
                    }
                });
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                doSearch();
            }
        });

        prepareBooksToDisplay(books);
        displayInBorrowedbooksTable(borrowedBooks);
    }

    /**
     * Metoda umożliwiająca wyświetlanie w tabeli wszystkich dostępnych książek.
     * @param books
     */
    public void displayInBooksTable(List<BookModel> books) {
        TitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishingHouseColumn.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        ISBNColumn.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        booksTable.setItems(FXCollections.observableArrayList(books));
    }

    /**
     * Metoda umożliwiająca wyświetlanie w tabeli wypożyczonych książek
     * @param books
     */
    public void displayInBorrowedbooksTable(List<BookRentalViewModel> books) {
        TitleColumn1.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn1.setCellValueFactory(new PropertyValueFactory<>("author"));
        publishingHouseColumn1.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        CategoryColumn1.setCellValueFactory(new PropertyValueFactory<>("category"));
        ISBNColumn1.setCellValueFactory(new PropertyValueFactory<>("ISBN"));
        BorrowDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedDateOfBorrow"));

        borrowedbooksTable.setItems(FXCollections.observableArrayList(books));
    }


    private void prepareBooksToDisplay(List<BookModel> books) {
        if (CartModel.books.isEmpty())
            displayInBooksTable(books);
        else {
            List<BookModel> booksWithoutThoseInCart = books;

            for (BookModel b : CartModel.books) {
                booksWithoutThoseInCart.removeIf(n -> (n.getId() == b.getId()));
            }
            displayInBooksTable(booksWithoutThoseInCart);
        }
    }

    /**
     * Metoda umożliwiająca przeszukiwanie książek
     */
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
            prepareBooksToDisplay(books);
        }
    }
}
