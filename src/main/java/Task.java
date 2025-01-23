public class Task {
    private String description;
    private boolean isDone = false;

    public Task(String description) {
        this.description = description;
    }

    public String toString() {
        return "[%s] %s".formatted(isDone ? "X" : " ", description);
    }
}
