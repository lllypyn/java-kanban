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

    void printAllTasks() {
        for (int id : epicTasksList.keySet()) {
            System.out.println(epicTasksList.get(id));
        }
        for (int id : tasksList.keySet()) {
            System.out.println(tasksList.get(id));
        }
    }

    Object searchById(int searchId) {
        if (epicTasksList.containsKey(searchId)) {
            return epicTasksList.get(searchId);
        } else if (tasksList.containsKey(searchId)) {
            return tasksList.get(searchId);
        } else {
            System.out.println("Такой задачи не найдено");
            return subTasksList.getOrDefault(searchId, null);
        }
    }

    void updateTask(int id, String newName, String newDescription) {
        if (searchById(id).getClass().equals(EpicTask.class) && searchById(id) != null) {
            EpicTask epicTask = (EpicTask) searchById(id);
            epicTask.name = newName;
            epicTask.description = newDescription;
            epicTasksList.put(id, epicTask);
        } else if (searchById(id).getClass().equals(Task.class) && searchById(id) != null) {
            Task task = (Task) searchById(id);
            task.name = newName;
            task.description = newDescription;
            tasksList.put(id, task);
        } else if (searchById(id).getClass().equals(SubTask.class) && searchById(id) != null) {
            SubTask subTask = (SubTask) searchById(id);
            subTask.name = newName;
            subTask.description = newDescription;
            subTasksList.put(id, subTask);
        }
    }

    void deleteAllSubTasksInEpic(int id) {
        searchById(id);
        if (searchById(id).getClass().equals(EpicTask.class) && searchById(id) != null) {
            EpicTask epicTask = (EpicTask) searchById(id);
            for (SubTask subTask : epicTask.subIdList) {
                int delete = subTask.id;
                subTasksList.remove(delete, subTask);
            }
            epicTask.subIdList.clear();
            epicTasksList.put(id, epicTask);
        }
    }
}
