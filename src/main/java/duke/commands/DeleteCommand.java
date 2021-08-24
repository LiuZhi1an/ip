package duke.commands;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.Task;
import duke.TaskList;

public class DeleteCommand extends Command {
    private int taskNum;

    public DeleteCommand(int taskNum) {
        this.taskNum = taskNum;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, DukeException {
        if (tasks.getTaskList().size() < this.taskNum) {
            throw new DukeException("You cannot complete a task that does not exist!");
        } else {
            Scanner newSc = new Scanner(new File(storage.getFilePath()));
            storage.deleteTaskFromFile(taskNum, newSc, tasks);
            ArrayList<Task> taskList = tasks.getTaskList();
            Task taskToDelete = taskList.get(taskNum - 1);
            taskList.remove(taskToDelete);
            System.out.println("Noted. I've removed this task: ");
            taskToDelete.showThisTask();
            String taskform;
            if (taskList.size() == 1 || taskList.size() == 0) {
                taskform = " task";
            } else {
                taskform = " tasks";
            }
            System.out.println("Now you have " + taskList.size() + taskform + " in the list.");
        }
    };

    public boolean isExit() {
        return false;
    }
}
