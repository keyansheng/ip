package rubberduke.util;

import rubberduke.UserException;
import rubberduke.task.Deadline;
import rubberduke.task.Event;
import rubberduke.task.Todo;

/**
 * Represents a parser with a task list.
 */
public class Parser {
    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Parses a command string and executes the corresponding action.
     *
     * @param command entered by the user.
     * @return output string returned by the action.
     * @throws UserException if command is invalid.
     */
    public String parse(String command) throws UserException {
        String[] args = command.split(" +", 2);
        try {
            switch (args[0]) {
            case "t":
            case "todo":
                return taskList.add(Todo.of(args[1]));
            case "d":
            case "deadline":
                return taskList.add(Deadline.of(args[1]));
            case "e":
            case "event":
                return taskList.add(Event.of(args[1]));
            case "ls":
            case "list":
                return taskList.list();
            case "rm":
            case "remove":
            case "delete":
                return taskList.delete(args[1]);
            case "mk":
            case "mark":
                return taskList.mark(args[1]);
            case "um":
            case "unmark":
                return taskList.unmark(args[1]);
            case "fd":
            case "find":
                return taskList.find(args[1]);
            default:
                throw new UserException("Quack! I don't know what you're talking about!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UserException("Oh quack! Missing command or argument!");
        }
    }
}
