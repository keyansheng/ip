import java.util.Scanner;

public class RubberDuke {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static final String GREETING = """
            Quack! \
            I'm Rubber Duke, your friendly neighbourhood rubber duck, here to help you with your debugging sessions.
            What can I do for you?""";
    private static final String FAREWELL = "Quack. Hope to see you again soon!";
    private static final String PROMPT = "> ";
    private TaskList taskList = new TaskList();

    private RubberDuke() {
        Storage storage;
        try {
            storage = new Storage(FILE_PATH);
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
        System.out.println(GREETING);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            System.out.print(PROMPT);
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
        System.out.println(FAREWELL);
    }

    public static void main(String[] args) {
        new RubberDuke();
    }
}
