package rubberduke.util;

import java.util.Scanner;

public class Ui {
    private Scanner scanner = new Scanner(System.in);

    public void showWelcome() {
        System.out.println("""
                Quack! \
                I'm Rubber Duke, your friendly neighbourhood rubber duck, here to help you with your debugging sessions.
                What can I do for you?""");
    }

    public void showGoodbye() {
        System.out.println("Quack. Hope to see you again soon!");
    }

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
