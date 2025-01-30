import java.util.Scanner;

public class RubberDuke {
    private final TaskList taskList = new TaskList();
    private final Parser parser = new Parser(taskList);
    private Storage storage;

    private RubberDuke(String filePath) {
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
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            Ui.showPrompt();
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else {
                    System.out.println(parser.parse(input));
                }
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
        }
        String output = taskList.dump();
        try {
            storage.write(output);
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
        Ui.showGoodbye();
    }

    public static void main(String[] args) {
        new RubberDuke("./data/tasks.txt").run();
    }
}
