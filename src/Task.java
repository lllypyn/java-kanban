import java.util.HashMap;

public class Task {
    String name;
    Status status;
    String description;
    boolean epic;
    HashMap <String, SubTask> subTasksList;
    boolean completed = false;

    Task(String name, String description){
        this.name = name;
        this.description = description;
    }

    public Task(String name, String description, HashMap<String, SubTask> subTasksList) {
        this.name = name;
        this.description = description;
        this.subTasksList = subTasksList;
    }
    void addSubTask(String name, String description){
        SubTask subTask = new SubTask(name,description);
        subTasksList.put(name, subTask);
    }





}
