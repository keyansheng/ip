package rubberduke.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rubberduke.RubberDuke;

/**
 * Represents the entry and exit point of the program.
 */
public class Main extends Application {
    private final RubberDuke rubberDuke = new RubberDuke();
    private MainWindow mainWindow;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            mainWindow = fxmlLoader.getController();
            mainWindow.setRubberDuke(rubberDuke);
            mainWindow.showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        mainWindow.showGoodbye();
        rubberDuke.saveTasks();
    }
}
