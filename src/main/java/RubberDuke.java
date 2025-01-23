import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RubberDuke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<Task> tasks = new ArrayList<>();
        greet();
        while (!(input = prompt(scanner)).equals("bye")) {
            if (input.equals("list")) {
                list(tasks);
            } else if (input.startsWith("mark ")) {
                mark(tasks, input.substring("mark ".length()));
            } else if (input.startsWith("unmark ")) {
                unmark(tasks, input.substring("unmark ".length()));
            } else {
                addTodo(input, tasks);
            }
        }
        farewell();
    }

    private static void mark(List<Task> tasks, String number) {
        try {
            Task task = tasks.get(Integer.parseInt(number.strip()) - 1);
            task.mark();
            System.out.printf("Quack! I've marked this task as done:\n%s%n", task);
        } catch (NumberFormatException e) {
            System.out.println("Oh quack! I can't read this number! Please specify the task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oh quack! I can't find this task! Please check the task number.");
        }
    }

    private static void unmark(List<Task> tasks, String number) {
        try {
            Task task = tasks.get(Integer.parseInt(number.strip()) - 1);
            task.unmark();
            System.out.printf("Quack, I've marked this task as not done yet:\n%s%n", task);
        } catch (NumberFormatException e) {
            System.out.println("Oh quack! I can't read this number! Please specify the task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oh quack! I can't find this task! Please check the task number.");
        }
    }

    private static void addTodo(String description, List<Task> tasks) {
        tasks.add(new Todo(description));
        System.out.printf("added: %s%n", description);
    }

    private static void list(List<Task> tasks) {
        System.out.println("Here are your tasks. Let's get quacking!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }

    private static void greet() {
        System.out.println("""
                Quack! I'm Rubber Duke, your friendly neighbourhood rubber duck, here to help you with your debugging sessions.
                What can I do for you?""");
    }

    private static void farewell() {
        System.out.println("Quack. Hope to see you again soon!");
    }

    private static String prompt(Scanner scanner) {
        System.out.print("> ");
        return scanner.nextLine();
    }
}
