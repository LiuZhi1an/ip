package main.java.duke.commands;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;

public class ExitCommand extends Command {

    /**
     * Constructs a new exit command with the given index.
     */
    public ExitCommand() {

    }

    /**
     * Executes the exit command.
     * @param tasks given list of tasks
     * @param ui given ui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye! Neko wishes to see you again soon!\n");
        ui.getScanner().close();
    };

    public boolean isExit() {
        return true;
    }
}
