package rubberduke.tasks;

import rubberduke.UserException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
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
            throw new UserException("Oh quack! I don't know the start and/or end times!\n" +
                    "Please specify /from followed by the start time, followed by /to and the end time.");
        }
    }

    @Override
    public String getCreateCommand() {
        return "event %s /from %s /to %s".formatted(getDescription(), from, to);
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
