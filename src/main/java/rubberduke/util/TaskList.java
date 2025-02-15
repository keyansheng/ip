package rubberduke.util;

import java.util.ArrayList;
import java.util.List;

import rubberduke.task.Task;

/**
 * Represents a task list.
 */
public class TaskList {
    private List<Task> tasks = new ArrayList<>();

    /**
     * Adds a task to the task list.
     *
     * @param task to be added.
     * @return confirmation message to be displayed to the user.
     */
    public String add(Task task) {
        tasks.add(task);
        return "Quack. I've added this task:\n"
               + task + "\n"
               + "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
    }

    /**
     * Lists the tasks in the task list.
     *
     * @return list of tasks to be displayed to the user.
     */
    public String list() {
        StringBuilder output = new StringBuilder().append("Here are your tasks. Let's get quacking!");
        for (int i = 0; i < tasks.size(); i++) {
            output.append("\n").append(i + 1).append(". ").append(tasks.get(i));
        }
        return output.toString();
    }

    /**
     * Finds tasks matching a query and displays them.
     *
     * @param query containing words in the description.
     * @return list of tasks to be displayed to the user.
     */
    public String find(String query) {
        StringBuilder output = new StringBuilder().append("Quack. Here are the matching tasks in your list:");
        int i = 1;
        for (Task task : tasks) {
            if (task.getDescription().contains(query)) {
                output.append("\n").append(i++).append(". ").append(task);
            }
        }
        return output.toString();
    }

    /**
     * Returns the current state of the task list as a string.
     *
     * @return string containing commands to recreate the current state of the task list.
     */
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

    /**
     * Deletes a task from the task list.
     *
     * @param number of the task.
     * @return confirmation message to be displayed to the user.
     */
    public String delete(String number) {
        try {
            Task task = tasks.remove(Integer.parseInt(number.strip()) - 1);
            return "Quack. I've removed this task:\n"
                    + task + "\n"
                    + "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list.";
        } catch (NumberFormatException e) {
            return "Oh quack! I can't read this number! Please specify the task number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oh quack! I can't find this task! Please check the task number.";
        }
    }

    /**
     * Marks a task as done.
     *
     * @param number of the task.
     * @return confirmation message to be displayed to the user.
     */
    public String mark(String number) {
        try {
            Task task = tasks.get(Integer.parseInt(number.strip()) - 1);
            task.mark();
            assert task.isDone();
            return "Quack! I've marked this task as done:\n" + task;
        } catch (NumberFormatException e) {
            return "Oh quack! I can't read this number! Please specify the task number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oh quack! I can't find this task! Please check the task number.";
        }
    }

    /**
     * Unmarks a task as done.
     *
     * @param number of the task.
     * @return confirmation message to be displayed to the user.
     */
    public String unmark(String number) {
        try {
            Task task = tasks.get(Integer.parseInt(number.strip()) - 1);
            task.unmark();
            assert !task.isDone();
            return "Quack, I've marked this task as not done yet:\n" + task;
        } catch (NumberFormatException e) {
            return "Oh quack! I can't read this number! Please specify the task number.";
        } catch (IndexOutOfBoundsException e) {
            return "Oh quack! I can't find this task! Please check the task number.";
        }
    }
}
