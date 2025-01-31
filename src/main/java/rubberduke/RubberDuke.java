package rubberduke;

import rubberduke.utils.Parser;
import rubberduke.utils.Storage;
import rubberduke.utils.TaskList;
import rubberduke.utils.Ui;

public class RubberDuke {
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(taskList);
    private final Ui ui = new Ui();
    private Storage storage;

    private RubberDuke(String filePath) {
        storage = initializeStorage(filePath);
        while (storage.scanner.hasNextLine()) {
            String input = storage.scanner.nextLine();
            try {
                parser.parse(input);
            } catch (UserException e) {
                System.out.printf("Oh quack! This line of the tasks file is corrupted:\n%s%n", input);
            }
        }
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

    private void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                if (fullCommand.equals("bye")) {
                    ui.showGoodbye();
                    isExit = true;
                } else {
                    System.out.println(parser.parse(fullCommand));
                }
            } catch (UserException e) {
                ui.showError(e.getMessage());
            }
        }
        String output = taskList.dump();
        try {
            storage.write(output);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new RubberDuke("./data/tasks.txt").run();
    }
}
