package rubberduke.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import rubberduke.RubberDuke;
import rubberduke.UserException;

/**
 * Represents the main window, which handles interaction with the user.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private RubberDuke rubberDuke;

    private final Image userImage = new Image(this.getClass().getResourceAsStream("/images/user.png"));
    private final Image rubberDukeImage = new Image(this.getClass().getResourceAsStream("/images/rubberduke.jpg"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    public void setRubberDuke(RubberDuke rubberDuke) {
        this.rubberDuke = rubberDuke;
    }

    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response;
        try {
            response = rubberDuke.getResponse(input);
        } catch (UserException e) {
            response = e.getMessage();
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getRubberDukeDialog(response, rubberDukeImage)
        );
        userInput.clear();
    }

    public void showWelcome() {
        dialogContainer.getChildren().add(DialogBox.getRubberDukeDialog(Ui.WELCOME_MESSAGE, rubberDukeImage));
    }

    public void showGoodbye() {
        dialogContainer.getChildren().add(DialogBox.getRubberDukeDialog(Ui.GOODBYE_MESSAGE, rubberDukeImage));
    }
}
