public class UnknownCommandException extends IllegalArgumentException {
    public UnknownCommandException() {
        super("Quack! I don't know what you're talking about!");
    }
}
