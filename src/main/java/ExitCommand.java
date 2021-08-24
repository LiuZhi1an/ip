public class ExitCommand extends Command {
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye! Neko wishes to see you again soon!\n");
        ui.sc.close();
    };

    public boolean isExit() {
        return true;
    }
}
