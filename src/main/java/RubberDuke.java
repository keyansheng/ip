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
                mark(tasks, input);
            } else if (input.startsWith("unmark ")) {
                unmark(tasks, input);
            } else {
                add(input, tasks);
            }
        }
        farewell();
    }

    private static void mark(List<Task> tasks, String input) {
        try {
            Task task = tasks.get(Integer.parseInt(input.split(" +")[1]) - 1);
            task.mark();
            System.out.printf("Quack! I've marked this task as done:\n%s%n", task);
        } catch (NumberFormatException e) {
            System.out.println("Oh quack! I can't read this number! Please specify the task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oh quack! I can't find this task! Please check the task number.");
        }
    }

    private static void unmark(List<Task> tasks, String input) {
        try {
            Task task = tasks.get(Integer.parseInt(input.split(" +")[1]) - 1);
            task.unmark();
            System.out.printf("Quack, I've marked this task as not done yet:\n%s%n", task);
        } catch (NumberFormatException e) {
            System.out.println("Oh quack! I can't read this number! Please specify the task number.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Oh quack! I can't find this task! Please check the task number.");
        }
    }

    private static void add(String input, List<Task> tasks) {
        tasks.add(new Task(input));
        System.out.printf("added: %s%n", input);
    }

    private static void list(List<Task> tasks) {
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
