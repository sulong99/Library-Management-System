package Services;

import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;

/**
 * Klasa odpowiada za wyświetlanie alertów.
 */
public class AlertService {

    /**
     * Metoda odpowiada za wyświetlanie alertu sukcesu.
     * @param message Wiadomość wyświetlana w alercie
     */
    public void displaySuccesDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Views/style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        dialogPane.getStyleClass().add("dialog-pane");

        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }


    /**
     * Metoda odpowiada za wyświetlanie alertu błędu.
     * @param message Wiadomość wyświetlana w alercie
     */
    public void displayWarinigDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Views/style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        dialogPane.getStyleClass().add("dialog-pane");
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }

    /**
     * Metoda odpowiada za wyświetlanie wystąpienia wyjątku w formie alertu.
     * @param message Wiadomość wyświetlana w alercie
     */
    public void displayExceptionAlertDialog(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.getStylesheets().add(getClass().getResource("/Views/style.css").toExternalForm());
        dialogPane.getStyleClass().add("dialog");
        dialogPane.getStyleClass().add("dialog-pane");
        alert.setTitle("");
        alert.setHeaderText(null);
        alert.setContentText(message);

        alert.showAndWait();
    }
}
