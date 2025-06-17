import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasksList = new HashMap<>();
    HashMap<Integer, SubTask> subTasksList = new HashMap<>();
    HashMap<Integer, EpicTask> epicTasksList = new HashMap<>();
    private int id = 0;
    HashMap <Integer, Object > tasksList = new HashMap<>();



    void addTask( String name, String description, boolean epic){
        if (!epic){
            Task task = new Task(id, name, description);
            tasksList.put(id, task);

        } else {
            HashMap<String, SubTask> subTasksList = new HashMap<>();
            EpicTask epicTask = new EpicTask(id, name, description, subTasksList);
            tasksList.put(id, epicTask);
        }
        id +=1;

    }
   /* void addSubTask(EpicTask epicTask,String name, String description) {
        int epicInt = epicTask.getId();
        SubTask subTask = new SubTask(id, name, description, epicInt);
            epicTask.subTasksList.put(name, subTask);
        id +=1;
    }
*/

    public void addSubTask(Object object,String name, String description) {
        if (object.getClass().equals(EpicTask.class)){
            int epicInt = ((EpicTask) object).getId();
            SubTask subTask = new SubTask(id, name, description, epicInt);
            ((EpicTask) object).subTasksList.put(name, subTask);
        }
        id +=1;
    }
}
