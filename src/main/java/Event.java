import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

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
        String from;
        try {
            from = LocalDateTime.parse(this.from).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        } catch (DateTimeParseException e) {
            from = this.from;
        }
        String to;
        try {
            to = LocalDateTime.parse(this.to).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        } catch (DateTimeParseException e) {
            to = this.to;
        }
        return "[E] %s (from: %s to: %s)".formatted(super.toString(), from, to);
    }
}
