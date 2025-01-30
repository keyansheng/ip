import java.util.Scanner;

public class RubberDuke {
    private TaskList taskList = new TaskList();

    private RubberDuke() {
        Storage storage;
        try {
            storage = new Storage("./data/tasks.txt");
        } catch (UserException e) {
            System.out.println(e.getMessage());
            return;
        }
        Parser parser = new Parser(taskList);
        while (storage.scanner.hasNextLine()) {
            String input = storage.scanner.nextLine();
            try {
                parser.parse(input);
            } catch (UserException e) {
                System.out.printf("Oh quack! This line of the tasks file is corrupted:\n%s%n", input);
            }
        }
        Ui.showWelcome();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            System.out.print("> ");
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
        new RubberDuke();
    }
}
