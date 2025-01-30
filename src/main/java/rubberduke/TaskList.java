package rubberduke;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    public String add(Task task) {
        tasks.add(task);
        return "Quack. I've added this task:\n"
               + task + "\n"
               + "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
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
        try {
            Task task = tasks.remove(Integer.parseInt(number.strip()) - 1);
            return "Quack. I've removed this task:\n"
                   + task + "\n" +
                   "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
        } catch (NumberFormatException e) {
            return "Oh quack! I can't read this number! Please specify the task number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oh quack! I can't find this task! Please check the task number.";
        }
    }

    public String mark(String number) {
        try {
            Task task = tasks.get(Integer.parseInt(number.strip()) - 1);
            task.mark();
            return "Quack! I've marked this task as done:\n" + task;
        } catch (NumberFormatException e) {
            return "Oh quack! I can't read this number! Please specify the task number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oh quack! I can't find this task! Please check the task number.";
        }
    }

    public String unmark(String number) {
        try {
            Task task = tasks.get(Integer.parseInt(number.strip()) - 1);
            task.unmark();
            return "Quack, I've marked this task as not done yet:\n" + task;
        } catch (NumberFormatException e) {
            return "Oh quack! I can't read this number! Please specify the task number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oh quack! I can't find this task! Please check the task number.";
        }
    }
}