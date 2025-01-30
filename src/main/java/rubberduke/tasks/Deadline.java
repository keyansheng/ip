package rubberduke.tasks;

import rubberduke.UserException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Deadline extends Task {
    private String by;

    private Deadline(String description, String by) throws UserException {
        super(description);
        if ((by = by.strip()).isEmpty()) {
            throw new UserException("Quack! I don't know when the deadline is!");
        }
        this.by = by;
    }

    public static Deadline of(String command) throws UserException {
        try {
            String[] args = command.split("/by ", 2);
            return new Deadline(args[0], args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UserException("Oh quack! I don't know the deadline!\n"
                                    + "Please specify /by followed by the deadline.");
        }
    }

    @Override
    public String getCreateCommand() {
        return "deadline %s /by %s".formatted(getDescription(), by);
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
