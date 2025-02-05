package rubberduke.task;

import rubberduke.UserException;

/**
 * Represents a task, which has a description and can be done or not done.
 */
public abstract class Task {
    private String description;
    private boolean isDone = false;

    /**
     * Constructs a task.
     *
     * @param description of the task.
     * @throws UserException if the description is empty.
     */
    public Task(String description) throws UserException {
        if ((description = description.strip()).isEmpty()) {
            throw new UserException("Quack! I don't know what this task is about!");
        }
        this.description = description;
    }

    /**
     * Gets the command for creating this task.
     * To be implemented by child classes.
     *
     * @return the creation command as a string.
     */
    public abstract String getCreateCommand();

    /**
     * Gets the task description.
     *
     * @return the task description as a string.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Checks if the task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done.
     */
    public void mark() {
        isDone = true;
    }

    /**
     * Unmarks the task as done.
     */
    public void unmark() {
        isDone = false;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return the task represented as a string.
     */
    @Override
    public String toString() {
        return "[%s] %s".formatted(isDone ? "X" : " ", description);
    }
}
