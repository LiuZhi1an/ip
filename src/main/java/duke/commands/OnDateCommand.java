package main.java.duke.commands;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import main.java.duke.DukeException;
import main.java.duke.Storage;
import main.java.duke.TaskList;
import main.java.duke.Ui;
import main.java.duke.tasks.Deadline;
import main.java.duke.tasks.Event;
import main.java.duke.tasks.Task;

public class OnDateCommand extends Command {
    private String dateString;

    /**
     * Constructs a new find tasks on a date command with the given date.
     * @param dateString the string of the date
     */
    public OnDateCommand(String dateString) {
        this.dateString = dateString;
    }

    /**
     * Executes the find tasks on a date command.
     * @param tasks given list of tasks
     * @param ui given ui object
     * @param storage given storage object
     * @throws IOException
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isFormattedDate = true;
        try {
            LocalDate.parse(this.dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException e) {
            isFormattedDate = false;
        }
        if (isFormattedDate) {
            identifyTasksByDate(dateString, tasks);
        }
    };

    private void identifyTasksByDate(String dateString, TaskList tasks) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDateFormatted() != null) {
                    if (deadline.getDateFormatted().equals(date)) {
                        tasksOnDate.add(deadline);
                    }
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getTimeFormatted() != null) {
                    LocalDate eventDate = event.getTimeFormatted().toLocalDate();
                    if (eventDate.equals(date)) {
                        tasksOnDate.add(event);
                    }
                }
            }
        }
        System.out.println("On this day you have the following task(s):");
        for (Task task : tasksOnDate) {
            task.showThisTask();
        }
    }

    public boolean isExit() {
        return false;
    }
}
