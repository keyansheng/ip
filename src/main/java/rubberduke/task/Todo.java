package rubberduke.task;

import rubberduke.UserException;

/**
 * Represents a todo task.
 */
public class Todo extends Task {
    private Todo(String description) throws UserException {
        super(description);
    }

    /**
     * Parses a command and creates a todo task.
     *
     * @param command containing a description.
     * @return a Todo object.
     * @throws UserException if description is invalid.
     */
    public static Todo of(String command) throws UserException {
        return new Todo(command);
    }

    @Override
    public String getCreateCommand() {
        return "todo %s".formatted(getDescription());
    }

    @Override
    public String toString() {
        return "[T] %s".formatted(super.toString());
    }
}
