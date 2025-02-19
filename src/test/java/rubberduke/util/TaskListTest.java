package rubberduke.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import rubberduke.UserException;
import rubberduke.task.Todo;

public class TaskListTest {
    @Test
    public void testAdd() throws UserException {
        assertEquals("""
                Quack. I've added this task:
                [T] [ ] testAdd
                Now you have 1 task in the list.""", new TaskList().add(Todo.of("testAdd")));
    }
}
