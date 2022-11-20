package Controllers;

import Models.PermissionEnum;
import Services.AlertService;
import Services.ReportService;
import Services.SceneManagerService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**
 * Klasa odpowiada za kontrolę widoku obsługującego raporty
 */
public class ReportsPanelController implements Initializable {

    private SceneManagerService sceneManager;
    private AlertService alertService;

    @FXML
    private Button GenerateRaportButton;

    @FXML
    private ComboBox<String> ComboTypeOfReport;

    @FXML
    private DatePicker DatePickerStart;

    @FXML
    private DatePicker DatePickerFinish;

    @FXML
    private Button logoutButton;

    @FXML
    private Button goToPanelButton1;

    /**
     * Metoda umożliwiająca wygenerowanie raportu zgodnie z dokonanym przez nas wyborem rodzaju raportu oraz przedziału czasowego
     * W przypadku błędu związanego z wygenerowaniem raportu wyświetlany jest stosowny komunikat
     * W przypadku poprawnie wygenerowanego raportu zwracany jest komunikat potwierdzający
     * @see DatePicker
     * @see ReportService
     * @see BorrowedBooksController
     * @param event
     */
    @FXML
    void generateReport(ActionEvent event) {
        try{
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String selected = ComboTypeOfReport.getValue();
            switch (selected) {
                case "Raport stanu zbioru bibliotecznego":
                    ReportService.generateLibraryCollectionReport();
                    break;
                case "Raport wypożyczeń":
                    if (DatePickerStart.getValue() != null && DatePickerFinish.getValue() != null){
                        ReportService.generateBorrowedBooksReport(formatter.format(Date.valueOf(DatePickerStart.getValue())),formatter.format(Date.valueOf(DatePickerFinish.getValue())));
                    }else if(DatePickerStart.getValue() != null && DatePickerFinish.getValue() == null){
                        ReportService.generateBorrowedBooksReport(formatter.format(Date.valueOf(DatePickerStart.getValue())), "");
                    }else if (DatePickerStart.getValue() == null && DatePickerFinish.getValue() != null){
                        ReportService.generateBorrowedBooksReport("", formatter.format(Date.valueOf(DatePickerFinish.getValue())));
                    }else {
                        ReportService.generateBorrowedBooksReport("", "");
                    }
                    break;
                case "Raport zwrotów":
                    if (DatePickerStart.getValue() != null && DatePickerFinish.getValue() != null){
                        ReportService.generateReturnedBooksReport(formatter.format(Date.valueOf(DatePickerStart.getValue())),formatter.format(Date.valueOf(DatePickerFinish.getValue())));
                    }else if(DatePickerStart.getValue() != null && DatePickerFinish.getValue() == null){
                        ReportService.generateReturnedBooksReport(formatter.format(Date.valueOf(DatePickerStart.getValue())), "");
                    }else if (DatePickerStart.getValue() == null && DatePickerFinish.getValue() != null){
                        ReportService.generateReturnedBooksReport("", formatter.format(Date.valueOf(DatePickerFinish.getValue())));
                    }else {
                        ReportService.generateReturnedBooksReport("", "");
                    }
                    break;
                default:
                    alertService.displayWarinigDialog("Proszę wybrać typ raportu do wygenerowania!");
                    break;
            }
        }catch (Exception ex){
            alertService.displayExceptionAlertDialog("Wystąpił błąd!");
        }
    }

    /**
     * Metoda umożliwiająca przejście do widoku administratora lub bibliotekarza zgodnie posiadanymi uprawnieniami
     * @see SceneManagerService
     * @see AdminPanelController
     * @see LibrarianPanelController
     * @param event
     */
    @FXML
    void goToPanel(ActionEvent event) {
        if (SceneManagerService.loggedUser.getPermissions() == PermissionEnum.Administrator) {
            sceneManager.changeScene("/Views/AdminPanelView.fxml");
        } else {
            sceneManager.changeScene("/Views/LibrarianPanelView.fxml");
        }
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


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        sceneManager = new SceneManagerService();
        alertService = new AlertService();

        ComboTypeOfReport.getItems().addAll("Raport stanu zbioru bibliotecznego", "Raport wypożyczeń", "Raport zwrotów");
    }
}

