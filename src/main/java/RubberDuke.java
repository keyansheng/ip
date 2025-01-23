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
            } else if (input.startsWith("todo ")) {
                addTodo(input.substring("todo ".length()), tasks);
            } else if (input.startsWith("deadline ")) {
                addDeadline(input.substring("deadline ".length()), tasks);
            } else if (input.startsWith("event ")) {
                addEvent(input.substring("event ".length()), tasks);
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
        add(new Todo(description), tasks);
    }

    private static void addDeadline(String argString, List<Task> tasks) {
        try {
            String[] args = argString.split("/by ", 2);
            add(new Deadline(args[0], args[1]), tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("""
                    Oh quack! I don't know the deadline!
                    Please specify /by followed by the deadline.""");
        }
    }

    private static void addEvent(String argString, List<Task> tasks) {
        try {
            String[] argsFrom = argString.split("/from ", 2);
            String[] argsTo = argsFrom[1].split("/to ", 2);
            add(new Event(argsFrom[0], argsTo[0], argsTo[1]), tasks);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("""
                    Oh quack! I don't know the start and/or end times!
                    Please specify /from followed by the start time, followed by /to and the end time.""");
        }
    }

    private static void add(Task task, List<Task> tasks) {
        tasks.add(task);
        System.out.printf("""
                Quack. I've added this task:
                %s
                Now you have %d tasks in the list.
                """, task, tasks.size());
    }

    private static void list(List<Task> tasks) {
        System.out.println("Here are your tasks. Let's get quacking!");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, tasks.get(i));
        }
    }

    private static void greet() {
        System.out.println("""
                Quack! \
                I'm Rubber Duke, your friendly neighbourhood rubber duck, \
                here to help you with your debugging sessions.
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
