public class RubberDuke {
    public static void main(String[] args) {
        greet();
        farewell();
    }

    private static void greet() {
        System.out.println("""
                Quack! I'm Rubber Duke, your friendly neighbourhood rubber duck, here to help you with your debugging sessions.
                What can I do for you?
                """);
    }

    private static void farewell() {
        System.out.println("Quack. Hope to see you again soon!");
    }
}
