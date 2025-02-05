package rubberduke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import rubberduke.UserException;

public class Event extends Task {
    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
    private String from;
    private String to;

    private Event(String description, String from, String to) throws UserException {
        super(description);
        if ((from = from.strip()).isEmpty()) {
            throw new UserException("Quack! I don't know when the event starts!");
        }
        if ((to = to.strip()).isEmpty()) {
            throw new UserException("Quack! I don't know when the event ends!");
        }
        this.from = from;
        this.to = to;
    }

    public static Event of(String command) throws UserException {
        try {
            String[] args = command.split("/from ", 2);
            String[] dates = args[1].split("/to ", 2);
            return new Event(args[0], dates[0], dates[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UserException(
                    "Oh quack! I don't know the start and/or end times!\n"
                    + "Please specify /from followed by the start time, followed by /to and the end time.");
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
        return "event %s /from %s /to %s".formatted(getDescription(), from, to);
    }

    @Override
    public String toString() {
        return "[E] %s (from: %s to: %s)".formatted(super.toString(), formatDateTime(from), formatDateTime(to));
    }
}
