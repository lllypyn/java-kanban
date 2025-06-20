package manager;

import task.EpicTask;
import task.Status;
import task.SubTask;
import task.Task;

import java.util.HashMap;

public class TaskManager {
    public HashMap<Integer, Task> tasksList = new HashMap<>();
    public HashMap<Integer, SubTask> subTasksList = new HashMap<>();
    public HashMap<Integer, EpicTask> epicTasksList = new HashMap<>();
    public int id = 0;

    public void createTask(String name, String description, Status status){
        Task task = new Task(id,name,description,status);
        addTask(task);
    }

    public void   createEpicTask(String name, String description){
        EpicTask epicTask = new EpicTask(id,name,description);
        setEpicStatus(epicTask);
        addEpicTask(epicTask);
    }

    public void  createSubTask(String name, String description, int epicId, Status status) {
        SubTask subTask = new SubTask(id,name,description,epicId,status);
        epicTasksList.get(epicId).subIdList.add(subTask);
        setEpicStatus(epicTasksList.get(epicId));
        addSubTask(subTask);
    }

    public void addTask(Task task) {
        tasksList.put(id, task);
        id += 1;
    }

    void addEpicTask(EpicTask epicTask) {
        epicTasksList.put(id, epicTask);
        id += 1;

    }

    public void addSubTask(SubTask subTask) {
        subTasksList.put(id, subTask);
        id += 1;
    }

    public void printAllTasks() {
        for (int id : epicTasksList.keySet()) {
            System.out.println(epicTasksList.get(id));
        }
        for (int id : tasksList.keySet()) {
            System.out.println(tasksList.get(id));
        }
    }

    public Object searchById(int searchId) {
        if (searchByIdInTasks(searchId) != null) {
            return searchByIdInTasks(searchId);
        } else if (searchByIdInEpicTasks(searchId) != null) {
            return searchByIdInEpicTasks (searchId);
        } else if (searchByIdInSubTasks(searchId) != null) {
            return searchByIdInSubTasks(searchId);
        } else {
            System.out.println("Такой задачи не найдено");
            return null;
        }
    }

    Task searchByIdInTasks(int searchId) {
        Task task = null;
        if (tasksList.containsKey(searchId)) {
            task = tasksList.get(searchId);

        }
        return task;
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

    public void updateTask(Task task, String name,String description, Status status) {
        if (task != null) {
            Task updateTask = new Task(task.id, name, description, status);
            tasksList.put(task.id, updateTask);
        }
    }

    public void updateEpicTask(EpicTask epicTask, String name,String description) {
        if (epicTask != null) {
            EpicTask updateEpicTask = new EpicTask(epicTask.id, name, description);
            epicTasksList.put(epicTask.id, updateEpicTask);
        }
    }

    public void updateSubTask(SubTask subTask, String name,String description, Status status) {
        if (subTask != null) {
            SubTask updateSubTask = new SubTask(subTask.id, name, description,subTask.epicId, status);
            subTasksList.put(subTask.id, updateSubTask);
        }
    }

    public void deleteEpicTask(int id){
        if (epicTasksList.containsKey(id)){
            deleteAllSubTasksInEpic(id);
            epicTasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    public void deleteTask(int id){
        if(tasksList.containsKey(id)){
            tasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    public void deleteSubTask(int id){
        if(subTasksList.containsKey(id)){
            subTasksList.remove(id);
            setEpicStatus(epicTasksList.get(subTasksList.get(id).epicId));
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    public void deleteAllSubTasksInEpic(int id) {
        searchById(id);
        if (searchById(id).getClass().equals(EpicTask.class) && searchById(id) != null) {
            int epicIdDelete = epicTasksList.get(id).id;
            for (SubTask subTask : subTasksList.values()) {
                subTasksList.remove(epicIdDelete, subTask);
            }
            epicTasksList.get(id).subIdList.clear();
            setEpicStatus(epicTasksList.get(id));
        } else {
            System.out.println("подзадач нет");
        }
    }

    public void deleteAll(){
        epicTasksList.clear();
        tasksList.clear();
        subTasksList.clear();
    }

    public void deleteAllSubTasks(){
        subTasksList.clear();
        for(EpicTask epicTask : epicTasksList.values()){
            setEpicStatus(epicTask);
        }
    }

    public void deleteAllTasks(){
        tasksList.clear();
    }

    public void deleteAllEpicTasks(){
        epicTasksList.clear();
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
