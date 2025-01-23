import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RubberDuke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        List<String> tasks = new ArrayList<>();
        greet();
        while (!(input = prompt(scanner)).equals("bye")) {
            if (input.equals("list")) {
                list(tasks);
            } else {
                add(input, tasks);
            }
        }
        farewell();
    }

    private static void add(String input, List<String> tasks) {
        tasks.add(input);
        System.out.printf("added: %s%n", input);
    }

    private static void list(List<String> tasks) {
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
