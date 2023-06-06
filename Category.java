import java.util.ArrayList;

class Category {
    private String name;
    private ArrayList<Task> taskList;

    public Category(String name) {
        this.name = name;
        taskList = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addTask(Task task) {
        taskList.add(task);
    }

    public void removeTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            taskList.remove(index);
        }
    }

    public ArrayList<Task> getTaskList() {
        return taskList;
    }
}

class Task {
    private String description;
    private int minutes;

    public Task(String description, int minutes) {
        this.description = description;
        this.minutes = minutes;
    }

    public String getDescription() {
        return description;
    }

    public int getMinutes() {
        return minutes;
    }

    public String getTimeString() {
        if (minutes >= 60) {
            int hours = minutes / 60;
            int remainingMinutes = minutes % 60;
            return hours + " hour(s) " + remainingMinutes + " minute(s)";
        } else {
            return minutes + " minute(s)";
        }
    }
}

