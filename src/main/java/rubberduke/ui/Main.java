package rubberduke.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rubberduke.RubberDuke;

public class Main extends Application {
    private final RubberDuke rubberDuke = new RubberDuke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRubberDuke(rubberDuke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
