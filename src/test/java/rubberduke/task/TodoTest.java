package rubberduke.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rubberduke.UserException;

public class TodoTest {
    @Test
    public void testOf() throws UserException {
        assertEquals("[T] [ ] testOf", Todo.of("testOf").toString());
    }

    @Test
    public void testGetCreateCommand() throws UserException {
        assertEquals("todo testGetCreateCommand", Todo.of("testGetCreateCommand").getCreateCommand());
    }
}
