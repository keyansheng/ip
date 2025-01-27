import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RubberDuke {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static final String GREETING = """
            Quack! \
            I'm Rubber Duke, your friendly neighbourhood rubber duck, here to help you with your debugging sessions.
            What can I do for you?""";
    private static final String FAREWELL = "Quack. Hope to see you again soon!";
    private static final String PROMPT = "> ";

    public static void main(String[] args) {
        File file = new File(FILE_PATH);
        File directory = file.getParentFile();
        Scanner fileScanner;
        try {
            if (!directory.exists()) {
                directory.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.printf("Oh quack! I can't find the tasks file! Is there a file at %s?%n", file.getPath());
            return;
        } catch (IOException e) {
            System.out.printf("Oh quack! I can't create the tasks file! Is %s writable?%n", file.getParent());
            return;
        }
        List<Task> tasks = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            String input = fileScanner.nextLine();
            if (input.startsWith("mark ")) {
                mark(tasks, input.substring("mark ".length()));
            } else if (input.startsWith("todo ")) {
                addTodo(input.substring("todo ".length()), tasks);
            } else if (input.startsWith("deadline ")) {
                addDeadline(input.substring("deadline ".length()), tasks);
            } else if (input.startsWith("event ")) {
                addEvent(input.substring("event ".length()), tasks);
            }
        }
        System.out.println(GREETING);
        System.out.print(PROMPT);
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(list(tasks));
            } else if (input.startsWith("mark ")) {
                System.out.println(mark(tasks, input.substring("mark ".length())));
            } else if (input.startsWith("unmark ")) {
                System.out.println(unmark(tasks, input.substring("unmark ".length())));
            } else if (input.startsWith("delete ")) {
                System.out.println(delete(tasks, input.substring("delete ".length())));
            } else if (input.startsWith("todo ")) {
                System.out.println(addTodo(input.substring("todo ".length()), tasks));
            } else if (input.startsWith("deadline ")) {
                System.out.println(addDeadline(input.substring("deadline ".length()), tasks));
            } else if (input.startsWith("event ")) {
                System.out.println(addEvent(input.substring("event ".length()), tasks));
            } else {
                try {
                    throw new UnknownCommandException();
                } catch (UnknownCommandException e) {
                    System.out.println(e.getMessage());
                }
            }
            System.out.print(PROMPT);
        }
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output.append(task.getCreateCommand()).append("\n");
            if (task.isDone()) {
                output.append("mark ").append(i + 1).append("\n");
            }
        }
        try {
            FileWriter fileWriter = new FileWriter(FILE_PATH);
            fileWriter.write(output.toString());
            fileWriter.close();
        } catch (IOException e) {
            System.out.printf("""
                    Oh quack! I can't write to the tasks file! Is %s writable?
                    Please save the following commands and enter them next time.
                    """, file.getParent());
            System.out.print(output);
            return;
        }
        System.out.println(FAREWELL);
    }

    private static String mark(List<Task> tasks, String number) {
        StringBuilder output = new StringBuilder();
        try {
            Task task = tasks.get(Integer.parseInt(number.strip()) - 1);
            task.mark();
            output.append("Quack! I've marked this task as done:\n").append(task);
        } catch (NumberFormatException e) {
            output.append("Oh quack! I can't read this number! Please specify the task number.");
        } catch (IndexOutOfBoundsException e) {
            output.append("Oh quack! I can't find this task! Please check the task number.");
        }
        return output.toString();
    }

    private static String unmark(List<Task> tasks, String number) {
        StringBuilder output = new StringBuilder();
        try {
            Task task = tasks.get(Integer.parseInt(number.strip()) - 1);
            task.unmark();
            output.append("Quack, I've marked this task as not done yet:\n").append(task);
        } catch (NumberFormatException e) {
            output.append("Oh quack! I can't read this number! Please specify the task number.");
        } catch (IndexOutOfBoundsException e) {
            output.append("Oh quack! I can't find this task! Please check the task number.");
        }
        return output.toString();
    }

    private static String delete(List<Task> tasks, String number) {
        StringBuilder output = new StringBuilder();
        try {
            Task task = tasks.remove(Integer.parseInt(number.strip()) - 1);
            output
                    .append("Quack. I've removed this task:\n")
                    .append(task)
                    .append("\nNow you have ")
                    .append(tasks.size())
                    .append(" task")
                    .append(tasks.size() == 1 ? "" : "s")
                    .append(" in the list.");
        } catch (NumberFormatException e) {
            output.append("Oh quack! I can't read this number! Please specify the task number.");
        } catch (IndexOutOfBoundsException e) {
            output.append("Oh quack! I can't find this task! Please check the task number.");
        }
        return output.toString();
    }

    private static String addTodo(String description, List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        try {
            output.append(add(new Todo(description), tasks));
        } catch (EmptyArgumentException e) {
            output.append(e.getMessage());
        }
        return output.toString();
    }

    private static String addDeadline(String argString, List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        try {
            String[] args = argString.split("/by ", 2);
            output.append(add(new Deadline(args[0], args[1]), tasks));
        } catch (EmptyArgumentException e) {
            output.append(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            output
                    .append("Oh quack! I don't know the deadline!\n")
                    .append("Please specify /by followed by the deadline.");
        }
        return output.toString();
    }

    private static String addEvent(String argString, List<Task> tasks) {
        StringBuilder output = new StringBuilder();
        try {
            String[] argsFrom = argString.split("/from ", 2);
            String[] argsTo = argsFrom[1].split("/to ", 2);
            output.append(add(new Event(argsFrom[0], argsTo[0], argsTo[1]), tasks));
        } catch (EmptyArgumentException e) {
            output.append(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            output
                    .append("Oh quack! I don't know the start and/or end times!\n")
                    .append("Please specify /from followed by the start time, followed by /to and the end time.");
        }
        return output.toString();
    }

    private static String add(Task task, List<Task> tasks) {
        tasks.add(task);
        return new StringBuilder()
                .append("Quack. I've added this task:\n")
                .append(task)
                .append("\nNow you have ")
                .append(tasks.size())
                .append(" task")
                .append(tasks.size() == 1 ? "" : "s")
                .append(" in the list.")
                .toString();
    }

    private static String list(List<Task> tasks) {
        StringBuilder output = new StringBuilder().append("Here are your tasks. Let's get quacking!");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return output.toString();
    }
}
