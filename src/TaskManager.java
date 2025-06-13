import java.util.HashMap;

public class TaskManager {
    HashMap <String, Task> tasks = new HashMap<>();

    void addTask(String name, String description, boolean epic){
        if (!epic){
            tasks.put(name, new Task(name, description));

        } else {
            HashMap <String, SubTask> subTasksList = new HashMap<>();
            tasks.put(name, new Task(name, description, subTasksList));
        }

    }





}
