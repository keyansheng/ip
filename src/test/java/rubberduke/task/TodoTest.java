package rubberduke.task;

import org.junit.jupiter.api.Test;
import rubberduke.UserException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void ofToStringTest() throws UserException {
        assertEquals("[T] [ ] of toString test", Todo.of("of toString test").toString());
    }

    @Test
    public void ofGetCreateCommandTest() throws UserException {
        assertEquals("todo of getCreateCommand test", Todo.of("of getCreateCommand test").getCreateCommand());
    }
}
