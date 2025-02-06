package rubberduke.ui;

import java.util.Scanner;

/**
 * Represents the user interface and handles input from and output to the user.
 */
public class Ui {
    protected static final String WELCOME_MESSAGE = """
            Quack! \
            I'm Rubber Duke, your friendly neighbourhood rubber duck, here to help you with your debugging sessions.
            What can I do for you?""";
    protected static final String GOODBYE_MESSAGE = "Quack. Hope to see you again soon!";
    protected static final String PROMPT = "> ";
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println(WELCOME_MESSAGE);
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.println(GOODBYE_MESSAGE);
    }

    /**
     * Reads user input.
     *
     * @return input from the user.
     */
    public String readCommand() {
        System.out.print(PROMPT);
        return scanner.hasNextLine() ? scanner.nextLine() : "bye";
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
