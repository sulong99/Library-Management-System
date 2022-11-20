package Services;

import Controllers.Controller;
import Models.UserModel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Klasa odpowiadająca za zmianę widoków w aplikacji
 */
public class SceneManagerService {
    public static Stage stage;
    public static UserModel loggedUser = new UserModel();


    public void changeScene(String path) {
        try {
            Parent parent = FXMLLoader.load(getClass().getResource(path));
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

        } catch (Exception ex) {
            AlertService alertService = new AlertService();
            alertService.displayExceptionAlertDialog("Nie można wczytać widoku!");
            ex.printStackTrace();
        }
    }

    public void changeScene(String path, int param) {
        try {
            FXMLLoader load = new FXMLLoader();
            load.setLocation(getClass().getResource(path));
            load.load();
            Controller main = load.getController();
            main.handleParam(param);
            Parent parent = load.getRoot();
            Scene scene = new Scene(parent);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        } catch (Exception ex) {
            AlertService alertService = new AlertService();
            alertService.displayExceptionAlertDialog("Nie można wczytać widoku!");
            ex.printStackTrace();
        }
    }
}
