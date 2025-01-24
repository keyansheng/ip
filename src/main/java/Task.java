public abstract class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) throws EmptyArgumentException {
        if ((description = description.strip()).isEmpty()) {
            throw new EmptyArgumentException("Quack! I don't know what this task is about!");
        }
        this.description = description;
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
