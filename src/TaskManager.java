import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasksList = new HashMap<>();
    HashMap<Integer, SubTask> subTasksList = new HashMap<>();
    HashMap<Integer, EpicTask> epicTasksList = new HashMap<>();
    private int id = 0;

    void addTask(String name, String description,Status status) {
        Task task = new Task(id, name, description, status);
        tasksList.put(id, task);
        id += 1;
    }

    void addEpicTask(String name, String description) {
        EpicTask epicTask = new EpicTask(id, name, description);
        epicTasksList.put(id, epicTask);
        id += 1;
        epicTask.setStatus();
    }

    void addSubTask(String name, String description, int epicId, Status status) {
        SubTask subTask = new SubTask(id, name, description, epicId, status);
        subTasksList.put(id, subTask);
        epicTasksList.get(epicId).subIdList.add(subTask);
        epicTasksList.get(epicId).setStatus();
        id += 1;
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

    EpicTask searchByIdInEpicTasks(int searchId) {
        EpicTask epicTask = null;
        if (epicTasksList.containsKey(searchId)) {
            epicTask = epicTasksList.get(searchId);
        }
        return epicTask;
    }

    SubTask searchByIdInSubTasks(int searchId) {
        SubTask subTask = null;
        if (subTasksList.containsKey(searchId)) {
            subTask = subTasksList.get(searchId);
        }
        return subTask;
    }

    void updateTask(Task task, String name,String description, Status status) {
        if (task != null) {
            Task updateTask = new Task(task.id, name, description, status);
            tasksList.put(task.id, updateTask);
        }
    }

    void updateEpicTask(EpicTask epicTask, String name,String description) {
        if (epicTask != null) {
            EpicTask updateEpicTask = new EpicTask(epicTask.id, name, description);
            epicTasksList.put(epicTask.id, updateEpicTask);
        }
    }

    void updateSubTask(SubTask subTask, String name,String description, Status status) {
        if (subTask != null) {
            SubTask updateSubTask = new SubTask(subTask.id, name, description,subTask.epicId, status);
            subTasksList.put(subTask.id, updateSubTask);
        }
    }

    void deleteEpicTask(int id){
        if (epicTasksList.containsKey(id)){
            deleteAllSubTasksInEpic(id);
            epicTasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
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
            epicTask.setStatus();
        }
    }
    void deleteAll(){
        epicTasksList.clear();
        tasksList.clear();
        subTasksList.clear();
    }
    void setEpicStatus(EpicTask epicTask) {
        int newCounter = 0;
        int doneCounter = 0;
        int inProgressCounter = 0;
        for (SubTask subTask : epicTask.subIdList){
            if(Status.DONE.equals(subTask.status)){
                doneCounter += 1;
            }else if (subTask.status.equals(Status.IN_PROGRESS)){
                inProgressCounter += 1;
            } else {
                newCounter +=1;
            }
        }
        if (doneCounter > 0 && newCounter == 0 && inProgressCounter == 0)
            epicTask.status = Status.DONE;
        else if (inProgressCounter > 0)
            epicTask.status = Status.IN_PROGRESS;
        else epicTask.status = Status.NEW;

    }

}
