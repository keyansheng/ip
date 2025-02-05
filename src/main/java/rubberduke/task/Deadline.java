package rubberduke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import rubberduke.UserException;

/**
 * Represents a deadline, which has an end date.
 */
public class Deadline extends Task {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    private String by;

    private Deadline(String description, String by) throws UserException {
        super(description);
        if ((by = by.strip()).isEmpty()) {
            throw new UserException("Quack! I don't know when the deadline is!");
        }
        this.by = by;
    }

    /**
     * Parses a command and creates a deadline task.
     *
     * @param command containing a description and end date.
     * @return a Deadline object.
     * @throws UserException if description or end date is invalid.
     */
    public static Deadline of(String command) throws UserException {
        try {
            String[] args = command.split("/by ", 2);
            return new Deadline(args[0], args[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UserException("Oh quack! I don't know the deadline!\n"
                                    + "Please specify /by followed by the deadline.");
        }
    }

    private String formatDateTime(String dateTime) {
        try {
            return LocalDateTime.parse(dateTime).format(FORMATTER);
        } catch (DateTimeParseException e) {
            return dateTime;
        }
    }

    @Override
    public String getCreateCommand() {
        return "deadline %s /by %s".formatted(getDescription(), by);
    }

    @Override
    public String toString() {
        return "[D] %s (by: %s)".formatted(super.toString(), formatDateTime(by));
    }
}
