import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

class ReminderTask extends TimerTask {
    private Task task;

    public ReminderTask(Task task) {
        this.task = task;
    }


    @Override
    public void run() {
        System.out.println("Reminder: Task '" + task.getDescription() + "' is due in 5 minutes!");
    }
}

public class Todo {
    private ArrayList<Category> categories;

    public Todo() {
        categories = new ArrayList<>();
    }

    public void addCategory(String categoryName) {
        Category category = new Category(categoryName);
        categories.add(category);
    }

    public void addTaskToCategory(Task task, Category category) {
        category.addTask(task);
        setupReminderTimer(task);
    }

    public void removeTaskFromCategory(int taskIndex, Category category) {
        category.removeTask(taskIndex);
    }

    public void setupReminderTimer(Task task) {
        int minutes = task.getMinutes();
        Calendar dueTime = Calendar.getInstance();
        dueTime.add(Calendar.MINUTE, minutes);

        Calendar reminderTime = (Calendar) dueTime.clone();
        reminderTime.add(Calendar.MINUTE, -5);

        Timer timer = new Timer();
        timer.schedule(new ReminderTask(task), reminderTime.getTime());
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Todo todo = new Todo();

        boolean addCategory = true;
        while (addCategory) {
            System.out.print("Enter a category name: ");
            String categoryName = scanner.nextLine();

            todo.addCategory(categoryName);

            System.out.print("How many tasks do you want to add? ");
            int numTasks = Integer.parseInt(scanner.nextLine());

            for (int i = 0; i < numTasks; i++) {
                System.out.print("Task " + i + ":\n");
                String taskDescription = scanner.nextLine();

                if (!taskDescription.isEmpty()) {
                    System.out.print("Enter the time (in minutes) this task is due: ");
                    int minutes = Integer.parseInt(scanner.nextLine());

                    Task task = new Task(taskDescription, minutes);
                    todo.addTaskToCategory(task, todo.categories.get(todo.categories.size() - 1));
                }
            }

            System.out.print("Do you want to add another category? (yes/no) ");
            String addAnotherCategory = scanner.nextLine();
            addCategory = addAnotherCategory.equalsIgnoreCase("yes");
        }

        System.out.println("\nYour Tasks:\n");
        for (Category category : todo.categories) {
            System.out.println("Category: " + category.getName());
            ArrayList<Task> taskList = category.getTaskList();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                System.out.println((i + 1) + ". " + task.getDescription() + " - " + task.getTimeString());

            }
            System.out.println();
        }

        scanner.close();
    }
}
