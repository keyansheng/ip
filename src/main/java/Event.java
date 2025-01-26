public class Event extends Task {
    private String from;
    private String to;

    public Event(String description, String from, String to) throws EmptyArgumentException {
        super(description);
        if ((from = from.strip()).isEmpty()) {
            throw new EmptyArgumentException("Quack! I don't know when the deadline is!");
        }
        if ((to = to.strip()).isEmpty()) {
            throw new EmptyArgumentException("Quack! I don't know when the deadline is!");
        }
        this.from = from;
        this.to = to;
    }

    @Override
    public String getCreateCommand() {
        return "event %s /from %s /to %s".formatted(super.getCreateCommand(), from, to);
    }

    @Override
    public String toString() {
        return "[E] %s (from: %s to: %s)".formatted(super.toString(), from, to);
    }
}
