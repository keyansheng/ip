package rubberduke.ui;

import java.util.Scanner;

/**
 * Represents the user interface and handles input from and output to the user.
 */
public class Ui {
    private Scanner scanner = new Scanner(System.in);

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("""
                Quack! \
                I'm Rubber Duke, your friendly neighbourhood rubber duck, here to help you with your debugging sessions.
                What can I do for you?""");
    }

    /**
     * Displays a goodbye message.
     */
    public void showGoodbye() {
        System.out.println("Quack. Hope to see you again soon!");
    }

    /**
     * Reads user input.
     *
     * @return input from the user.
     */
    public String readCommand() {
        System.out.print("> ");
        return scanner.hasNextLine() ? scanner.nextLine() : "bye";
    }

    public void show(String message) {
        System.out.println(message);
    }

    public void showError(String message) {
        System.out.println(message);
    }
}
