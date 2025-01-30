package rubberduke;

public class Parser {
    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    public String parse(String command) throws UserException {
        String[] args = command.split(" +", 2);
        try {
            switch (args[0]) {
            case "todo":
                return taskList.add(Todo.of(args[1]));
            case "deadline":
                return taskList.add(Deadline.of(args[1]));
            case "event":
                return taskList.add(Event.of(args[1]));
            case "list":
                return taskList.list();
            case "delete":
                return taskList.delete(args[1]);
            case "mark":
                return taskList.mark(args[1]);
            case "unmark":
                return taskList.unmark(args[1]);
            default:
                throw new UserException("Quack! I don't know what you're talking about!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new UserException("Oh quack! Missing command or argument!");
        }
    }
}
