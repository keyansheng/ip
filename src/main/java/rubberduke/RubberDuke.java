package rubberduke;

import rubberduke.ui.Ui;
import rubberduke.util.Parser;
import rubberduke.util.Storage;
import rubberduke.util.TaskList;

/**
 * Represents a Rubber Duke instance attached to a tasks file.
 */
public class RubberDuke {
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(taskList);
    private final Ui ui = new Ui();
    private final Storage storage;

    public RubberDuke() {
        this("./data/tasks.txt");
    }

    /**
     * Represents Rubber Duke, the debugging rubber duck.
     *
     * @param filePath to the tasks file.
     */
    public RubberDuke(String filePath) {
        storage = initializeStorage(filePath);
        loadTasks();
    }

    private Storage initializeStorage(String filePath) {
        try {
            return new Storage(filePath);
        } catch (UserException e) {
            ui.showError(e.getMessage());
            System.exit(1);
            return null;
        }
    }

    private void loadTasks() {
        while (storage.scanner.hasNextLine()) {
            String input = storage.scanner.nextLine();
            processFileCommand(input);
        }
    }

    private void processFileCommand(String input) {
        try {
            parser.parse(input);
        } catch (UserException e) {
            ui.showError("Oh quack! This line of the tasks file is corrupted:\n" + input);
        }
    }

    /**
     * Saves tasks to the tasks file.
     */
    public void saveTasks() {
        try {
            storage.write(taskList.export());
        } catch (UserException e) {
            ui.showError(e.getMessage());
        }
    }

    private void handleInput() {
        while (true) {
            String command = ui.readCommand();
            if (command.equals("bye")) {
                break;
            }
            processCommand(command);
        }
    }

    private void processCommand(String command) {
        try {
            ui.show(getResponse(command));
        } catch (UserException e) {
            ui.showError(e.getMessage());
        }
    }

    public String getResponse(String input) throws UserException {
        return parser.parse(input);
    }

    private void run() {
        ui.showWelcome();
        handleInput();
        ui.showGoodbye();
        saveTasks();
    }

    public static void main(String[] args) {
        new RubberDuke().run();
    }
}
