package Controllers;

import Data.DAO.UserDAO;
import Models.UserModel;
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
import java.util.List;
import java.util.ResourceBundle;

/**
 * Klasa odpowiadająca za obsługę widoku użytkowników w panelu administratora
 */
public class UsersDetailsPanelController implements Initializable {

    private SceneManagerService sceneManager;
    private AlertService alertService;
    private List<UserModel> employees;
    private List<UserModel> users;
    @FXML
    private TextField searchField;

    @FXML
    private Button AddReaderButton1;

    @FXML
    private Button deleteReaderButton;

    @FXML
    private Button DetailsReaderButton;

    @FXML
    private TabPane tabPane;

    @FXML
    private Tab EmployeesTab;

    @FXML
    private TableView<UserModel> EmployeesTable;

    @FXML
    private TableColumn<?, ?> NameColumn;

    @FXML
    private TableColumn<?, ?> SurnameColumn;

    @FXML
    private TableColumn<UserModel, Integer> EmployeeIdColumn;

    @FXML
    private TableColumn<?, ?> AddressColumn;

    @FXML
    private TableColumn<?, ?> PermissionsColumn;

    @FXML
    private Tab ReadersTab;

    @FXML
    private TableView<UserModel> ReadersTable;

    @FXML
    private TableColumn<?, ?> NameColumn1;

    @FXML
    private TableColumn<?, ?> SurnameColumn1;

    @FXML
    private TableColumn<UserModel, Integer> ReaderIdColumn1;

    @FXML
    private TableColumn<?, ?> AddressColumn1;

    @FXML
    private TableColumn<?, ?> NumberBorrowedBookColumn;

    @FXML
    private Button DeleteEmployeeButton;

    @FXML
    private Button AddEmployeeButton1;

    @FXML
    private Button DetailsEmployeeButton1;

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
     * Metoda odpowiadająca za przejście do widoku dodawania użytkownika
     * @param event
     * @see AddUserController
     */
    @FXML
    void addEmployeeDetails(ActionEvent event) {
        sceneManager.changeScene("/Views/AddUserView.fxml", 0);
    }

    /**
     * Metoda odpowiadająca za przejście do widoku dodawania użytkownika
     * @param event
     */
    @FXML
    void addReaderDetails(ActionEvent event) {
        sceneManager.changeScene("/Views/AddUserView.fxml", 0);
    }

    /**
     * Metoda odpowiadająca za usuwanie pracownika.
     * W przypadku użycia metody po zakończeniu wyświetli się odpowiedni komunikat.
     * @param event
     * @see AlertService
     * @see UserDAO
     */
    @FXML
    void deleteEmployee(ActionEvent event) {
        try {
            UserDAO.delete(employees.get(EmployeesTable.getSelectionModel().getSelectedIndex()));
            employees.remove(EmployeesTable.getSelectionModel().getSelectedIndex());
            alertService.displaySuccesDialog("Usunięto konto pracownika!");
            displayInEmployeesTable(employees);
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać pracownika!");
        }
    }
    /**
     * Metoda odpowiadająca za usuwanie czytelnika.
     * W przypadku użycia metody po zakończeniu wyświetli się odpowiedni komunikat.
     * @param event
     * @see AlertService
     * @see UserDAO
     */
    @FXML
    void deleteReader(ActionEvent event) {
        try {
            alertService.displayWarinigDialog("Użytkownik zostanie usunięty tylko jeśli nie wypożyczył nigdy żadnej książki");
            UserDAO.delete(users.get(ReadersTable.getSelectionModel().getSelectedIndex()));
            users.remove(ReadersTable.getSelectionModel().getSelectedIndex());
            alertService.displaySuccesDialog("Usunięto konto użytkownika");
            displayInReadersTable(users);
        } catch (Exception ex) {
            alertService.displayWarinigDialog("Proszę wybrać użytkownika!");
        }
    }

    /**
     * Metoda odpowiadająca za przejście do widoku zawierającego zapytania.
     * @param event
     * @see InquiriesPanelController
     */
    @FXML
    void goToInquiries(ActionEvent event) {
        sceneManager.changeScene("/Views/InquiriesPanelView.fxml");
    }

    /**
     * Metoda odpowiadająca za przejście do widoku głównego administratora zawierającego zbiór biblioteczny
     * @param event
     * @see AdminPanelController
     */
    @FXML
    void goToLibraryCollection(ActionEvent event) { sceneManager.changeScene("/Views/AdminPanelView.fxml"); }

    /**
     * Metoda odpowiadająca za przejście do widoku raportów
     * @param event
     * @see ReportsPanelController
     */
    @FXML
    void goToRaports(ActionEvent event) {
        sceneManager.changeScene("/Views/ReportsPanelView.fxml");
    }

    @FXML
    void goToUsersDetails(ActionEvent event) { }

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
     * Metoda odpowiadająca wyświetlenie informacji na temat pracownika.
     * Po wybraniu odpowiedniej osoby przechodzimy do panelu zawierającego szczegółowe informacje,
     * w przypadku nie wybrania pojawi się komunikat z błędem.
     * @param event
     * @see AddUserController
     * @see UserModel
     */
    @FXML
    void showEmployeeDetails(ActionEvent event) {
        UserModel user = employees.get(EmployeesTable.getSelectionModel().getSelectedIndex());
        if (user == null) {
            alertService.displayWarinigDialog("Proszę wybrać pracownika!");
        } else {
            sceneManager.changeScene("/Views/AddUserView.fxml", user.getUserId());
        }
    }
    /**
     * Metoda odpowiadająca wyświetlenie informacji na temat użytkownika.
     * Po wybraniu odpowiedniej osoby przechodzimy do panelu zawierającego szczegółowe informacje,
     * w przypadku nie wybrania pojawi się komunikat z błędem.
     * @param event
     * @see AddUserController
     * @see UserModel
     */
    @FXML
    void showReaderDetails(ActionEvent event) {
        UserModel user = users.get(ReadersTable.getSelectionModel().getSelectedIndex());
        if (user == null) {
            alertService.displayWarinigDialog("Proszę wybrać pracownika!");
        } else {
            sceneManager.changeScene("/Views/AddUserView.fxml", user.getUserId());
        }
    }

    /**
     * Metoda inicjalizacji klasy. Odpowiada za wyświetlanie odpowiednich przycisków oraz wczytanie danych do odpowiednich tabel.
     * @param location
     * @param resources
     * @see SceneManagerService
     * @see AlertService
     * @see UserDAO
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sceneManager = new SceneManagerService();
        alertService = new AlertService();

        AddEmployeeButton1.setVisible(true);
        DeleteEmployeeButton.setVisible(true);
        DetailsEmployeeButton1.setVisible(true);

        AddReaderButton1.setVisible(false);
        deleteReaderButton.setVisible(false);
        DetailsReaderButton.setVisible(false);

        tabPane.getSelectionModel().selectedItemProperty().addListener(
                new ChangeListener<Tab>() {
                    /**
                     * Metoda zmieniająca widoczność przycisków w zależności od tabeli, w której się znajdujemy.
                     * @param ov
                     * @param t
                     * @param t1
                     */
                    @Override
                    public void changed(ObservableValue<? extends Tab> ov, Tab t, Tab t1) {
                        if (tabPane.getSelectionModel().getSelectedIndex() != 0) {
                            AddEmployeeButton1.setVisible(false);
                            DeleteEmployeeButton.setVisible(false);
                            DetailsEmployeeButton1.setVisible(false);

                            AddReaderButton1.setVisible(true);
                            deleteReaderButton.setVisible(true);
                            DetailsReaderButton.setVisible(true);
                        } else {
                            AddEmployeeButton1.setVisible(true);
                            DeleteEmployeeButton.setVisible(true);
                            DetailsEmployeeButton1.setVisible(true);

                            AddReaderButton1.setVisible(false);
                            deleteReaderButton.setVisible(false);
                            DetailsReaderButton.setVisible(false);
                        }
                    }
                });

        employees = UserDAO.getList("SELECT * FROM `Users` WHERE Permission = 'Administrator' OR Permission = 'Worker'");
        users = UserDAO.getList("SELECT u.* " +
                ",COUNT(b.User_id) AS 'countBr' " +
                "FROM Users u " +
                "INNER JOIN BookRentals_Users b " +
                "ON u.Users_id=b.User_id " +
                "GROUP BY b.User_id " +
                "ORDER BY 'countBr' DESC");

        displayInEmployeesTable(employees);
        displayInReadersTable(users);
    }

    /**
     * Metoda odpowiadająca za wyświetlanie danych w tabeli pracownicy.
     * @param users Lista użytkowników
     * @see UserModel
     */
    public void displayInEmployeesTable(List<UserModel> users) {
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        SurnameColumn.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        EmployeeIdColumn.setCellValueFactory(new PropertyValueFactory<UserModel, Integer>("userId"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("Address"));
        PermissionsColumn.setCellValueFactory(new PropertyValueFactory<>("permissionTranslation"));

        EmployeesTable.setItems(FXCollections.observableArrayList(users));
    }

    /**
     * Metoda odpowiadająca za wyświetlanie danych w tabeli czytelnicy.
     * @param users Lista użytkowników
     * @see UserModel
     */
    public void displayInReadersTable(List<UserModel> users) {
        NameColumn1.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
        SurnameColumn1.setCellValueFactory(new PropertyValueFactory<>("LastName"));
        ReaderIdColumn1.setCellValueFactory(new PropertyValueFactory<UserModel, Integer>("userId"));
        AddressColumn1.setCellValueFactory(new PropertyValueFactory<>("Address"));
        NumberBorrowedBookColumn.setCellValueFactory(new PropertyValueFactory<>("count"));

        ReadersTable.setItems(FXCollections.observableArrayList(users));
    }
}
