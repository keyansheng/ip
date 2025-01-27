import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private String by;

    public Deadline(String description, String by) throws UserException {
        super(description);
        if ((by = by.strip()).isEmpty()) {
            throw new UserException("Quack! I don't know when the deadline is!");
        }
        this.by = by;
    }

    @Override
    public String getCreateCommand() {
        return "deadline %s /by %s".formatted(super.getCreateCommand(), by);
    }

    @Override
    public String toString() {
        String by;
        try {
            by = LocalDateTime.parse(this.by).format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM));
        } catch (DateTimeParseException e) {
            by = this.by;
        }
        return "[D] %s (by: %s)".formatted(super.toString(), by);
    }
}
