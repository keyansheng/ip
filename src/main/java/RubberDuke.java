public class RubberDuke {
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(taskList);
    private Storage storage;
    private Ui ui;

    private RubberDuke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
        } catch (UserException e) {
            System.out.println(e.getMessage());
            return;
        }
        while (storage.scanner.hasNextLine()) {
            String input = storage.scanner.nextLine();
            try {
                parser.parse(input);
            } catch (UserException e) {
                System.out.printf("Oh quack! This line of the tasks file is corrupted:\n%s%n", input);
            }
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
