import Services.RunMySQLScript;
import Services.SceneManagerService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import Services.AlertService;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.Charset;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Views/login.fxml"));
            Scene scene = new Scene(root);
            SceneManagerService.stage = primaryStage;
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (java.lang.NullPointerException e) {
            AlertService alertService = new AlertService();
            alertService.displayExceptionAlertDialog("Nie można wczytać widoku!");
        }
    }


    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
        RunMySQLScript runMySQLScript = new RunMySQLScript();
        String path = new File("freedbtech_Biblioteka.sql").getAbsolutePath();
        runMySQLScript.runMysqlScript(path);

        launch(args);
    }
}
