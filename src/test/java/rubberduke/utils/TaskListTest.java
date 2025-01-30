package rubberduke.utils;

import org.junit.jupiter.api.Test;
import rubberduke.UserException;
import rubberduke.tasks.Todo;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {
    @Test
    public void addTest() throws UserException {
        assertEquals("""
                Quack. I've added this task:
                [T] [ ] add test
                Now you have 1 task in the list.""", new TaskList().add(Todo.of("add test")));
    }
}
