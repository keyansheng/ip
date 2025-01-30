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
        while (storage.scanner.hasNextLine()) {
            String input = storage.scanner.nextLine();
            try {
                if (input.startsWith("mark ")) {
                    taskList.mark(input.substring("mark ".length()));
                } else if (input.startsWith("todo ")) {
                    taskList.add(Todo.of(input.substring("todo ".length())));
                } else if (input.startsWith("deadline ")) {
                    taskList.add(Deadline.of(input.substring("deadline ".length())));
                } else if (input.startsWith("event ")) {
                    taskList.add(Event.of(input.substring("event ".length())));
                }
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println(GREETING);
        System.out.print(PROMPT);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    System.out.println(taskList.list());
                } else if (input.startsWith("mark ")) {
                    System.out.println(taskList.mark(input.substring("mark ".length())));
                } else if (input.startsWith("unmark ")) {
                    System.out.println(taskList.unmark(input.substring("unmark ".length())));
                } else if (input.startsWith("delete ")) {
                    System.out.println(taskList.delete(input.substring("delete ".length())));
                } else if (input.startsWith("todo ")) {
                    System.out.println(taskList.add(Todo.of(input.substring("todo ".length()))));
                } else if (input.startsWith("deadline ")) {
                    System.out.println(taskList.add(Deadline.of(input.substring("deadline ".length()))));
                } else if (input.startsWith("event ")) {
                    System.out.println(taskList.add(Event.of(input.substring("event ".length()))));
                } else {
                    throw new UserException("Quack! I don't know what you're talking about!");
                }
            } catch (UserException e) {
                System.out.println(e.getMessage());
            }
            System.out.print(PROMPT);
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
