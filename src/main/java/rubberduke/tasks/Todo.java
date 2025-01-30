package rubberduke.tasks;

import rubberduke.UserException;

public class Todo extends Task {
    private Todo(String description) throws UserException {
        super(description);
    }

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
