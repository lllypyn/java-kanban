import java.util.HashMap;

public class TaskManager {
    HashMap<Integer, Task> tasksList = new HashMap<>();
    HashMap<Integer, SubTask> subTasksList = new HashMap<>();
    HashMap<Integer, EpicTask> epicTasksList = new HashMap<>();
    private int id = 0;

    void createTask(String name, String description, Status status){
        Task task = new Task(id,name,description,status);
        addTask(task);
    }

    void  createEpicTask(String name, String description){
        EpicTask epicTask = new EpicTask(id,name,description);
        addEpicTask(epicTask);
    }

    void  createSubTask(String name, String description, int epicId, Status status) {
        SubTask subTask = new SubTask(id,name,description,epicId,status);
        epicTasksList.get(epicId).subIdList.add(subTask);
        setEpicStatus(epicTasksList.get(epicId));
        addSubTask(subTask);
    }

    void addTask(Task task) {
        tasksList.put(id, task);
        id += 1;
    }

    void addEpicTask(EpicTask epicTask) {
        setEpicStatus(epicTask);
        epicTasksList.put(id, epicTask);
        id += 1;

    }

    void addSubTask(SubTask subTask) {
        subTasksList.put(id, subTask);
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
        Object object = null;
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

    void deleteTask(int id){
        if(tasksList.containsKey(id)){
            tasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    void deleteSubTask(int id){
        if(subTasksList.containsKey(id)){
            subTasksList.remove(id);
            setEpicStatus(epicTasksList.get(subTasksList.get(id).epicId));
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
            setEpicStatus(epicTasksList.get(id));
        }
    }

    void deleteAll(){
        epicTasksList.clear();
        tasksList.clear();
        subTasksList.clear();
    }

    void deleteAllSubTasks(){
        subTasksList.clear();
        for(EpicTask epicTask : epicTasksList.values()){
            setEpicStatus(epicTask);
        }
    }

    void deleteAllTasks(){
        tasksList.clear();
    }

    void deleteAllEpicTasks(){
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
