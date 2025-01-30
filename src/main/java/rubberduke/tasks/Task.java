package rubberduke.tasks;

import rubberduke.UserException;

public abstract class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) throws UserException {
        if ((description = description.strip()).isEmpty()) {
            throw new UserException("Quack! I don't know what this task is about!");
        }
        this.description = description;
    }

    public abstract String getCreateCommand();

    public String getDescription() {
        return description;
    }

    public boolean isDone() {
        return isDone;
    }

    public void mark() {
        isDone = true;
    }

    public void unmark() {
        isDone = false;
    }

    @Override
    public String toString() {
        return "[%s] %s".formatted(isDone ? "X" : " ", description);
    }
}
