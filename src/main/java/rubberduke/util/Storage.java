package rubberduke.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import rubberduke.UserException;

/**
 * Represents storage of tasks in a tasks file.
 */
public class Storage {
    public final Scanner scanner;
    private final String path;

    /**
     * Loads the tasks file into a scanner.
     *
     * @param path to a tasks file.
     * @throws UserException if the tasks file cannot be created or found.
     */
    public Storage(String path) throws UserException {
        this.path = path;
        File file = new File(path);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new UserException("Oh quack! I can't create the tasks file! Is %s writable?".formatted(path));
            }
        }
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new UserException("Oh quack! I can't find the tasks file! Is there a file at %s?".formatted(path));
        }
    }

    /**
     * Writes output to the tasks file.
     *
     * @param output to be written to the tasks file.
     * @throws UserException if the tasks file cannot be written to.
     */
    public void write(String output) throws UserException {
        try {
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(output);
            fileWriter.close();
        } catch (IOException e) {
            throw new UserException("""
                    Oh quack! I can't write to the tasks file! Is %s writable?
                    Please save the following commands and enter them next time.
                    %s""".formatted(path, output));
        }
    }
}
