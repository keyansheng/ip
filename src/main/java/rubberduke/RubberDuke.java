package rubberduke;

import rubberduke.util.Parser;
import rubberduke.util.Storage;
import rubberduke.util.TaskList;
import rubberduke.util.Ui;

/**
 * Represents a Rubber Duke instance attached to a tasks file.
 */
public class RubberDuke {
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(taskList);
    private final Ui ui = new Ui();
    private final Storage storage;

    private RubberDuke(String filePath) {
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

    private void saveTasks() {
        try {
            storage.write(taskList.dump());
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
            ui.show(parser.parse(command));
        } catch (UserException e) {
            ui.showError(e.getMessage());
        }
    }

    private void run() {
        ui.showWelcome();
        handleInput();
        ui.showGoodbye();
        saveTasks();
    }

    public static void main(String[] args) {
        new RubberDuke("./data/tasks.txt").run();
    }
}
