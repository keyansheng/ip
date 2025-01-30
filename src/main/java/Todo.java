public class Todo extends Task {
    public Todo(String description) throws UserException {
        super(description);
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
