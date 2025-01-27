public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getCreateCommand() {
        return "todo %s".formatted(super.getCreateCommand());
    }

    @Override
    public String toString() {
        return "[T] %s".formatted(super.toString());
    }
}
