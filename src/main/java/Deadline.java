public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) throws EmptyArgumentException {
        super(description);
        if ((by = by.strip()).isEmpty()) {
            throw new EmptyArgumentException("Quack! I don't know when the deadline is!");
        }
        this.by = by;
    }

    @Override
    public String getCreateCommand() {
        return "deadline %s /by %s".formatted(super.getCreateCommand(), by);
    }

    @Override
    public String toString() {
        return "[D] %s (by: %s)".formatted(super.toString(), by);
    }
}
