import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public String add(Task task) {
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

    public String list() {
        StringBuilder output = new StringBuilder().append("Here are your tasks. Let's get quacking!");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return output.toString();
    }

    public String dump() {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            output.append(task.getCreateCommand()).append("\n");
            if (task.isDone()) {
                output.append("mark ").append(i + 1).append("\n");
            }
        }
        return output.toString();
    }

    public String delete(String number) {
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

    public String mark(String number) {
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

    public String unmark(String number) {
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
}