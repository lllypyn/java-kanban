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



    public void addTask(Task task) {
        tasksList.put(id, task);
        id += 1;
    }

    public void addEpicTask(EpicTask epicTask) {
        setEpicStatus(epicTask);
        epicTasksList.put(id, epicTask);
        id += 1;
    }

    public void addSubTask(SubTask subTask) {
        epicTasksList.get(subTask.getEpicId()).getSubIdList().add(subTask.getId());
        subTasksList.put(id, subTask);
        setEpicStatus(epicTasksList.get(subTask.getEpicId()));

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
            return searchByIdInEpicTasks(searchId);
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

    public void updateTask(Task task) {
        if (task != null) {
            tasksList.put(task.getId(), task);
        }
    }

    public void updateEpicTask(EpicTask epicTask) {
        if (epicTask != null) {
            epicTasksList.put(epicTask.getId(), epicTask);
        }
    }

    public void updateSubTask(SubTask subTask) {
        if (subTask != null) {
            epicTasksList.get(subTask.getEpicId()).getSubIdList().add(subTask.getId());
            setEpicStatus(epicTasksList.get(subTask.getEpicId()));
            subTasksList.put(subTask.getId(), subTask);
        }
    }

    public void deleteEpicTask(int id) {
        if (epicTasksList.containsKey(id)) {
            deleteAllSubTasksInEpic(id);
            epicTasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    public void deleteTask(int id) {
        if (tasksList.containsKey(id)) {
            tasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    public void deleteSubTask(int id) {
        if (subTasksList.containsKey(id)) {
            epicTasksList.get(subTasksList.get(id).getEpicId()).getSubIdList().remove((Integer)id);

            setEpicStatus(epicTasksList.get(subTasksList.get(id).getEpicId()));
            subTasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    public void deleteAllSubTasksInEpic(int id) {
        searchById(id);
        if (searchById(id).getClass().equals(EpicTask.class) && searchById(id) != null) {
            int epicIdDelete = epicTasksList.get(id).getId();
            for (SubTask subTask : subTasksList.values()) {
                subTasksList.remove(epicIdDelete, subTask);
            }
            epicTasksList.get(id).getSubIdList().clear();
            setEpicStatus(epicTasksList.get(id));
        } else {
            System.out.println("подзадач нет");
        }
    }

    public void deleteAll() {
        epicTasksList.clear();
        tasksList.clear();
        subTasksList.clear();
        System.out.println("все задачи, эпики и подзадачи удалены");
    }

    public void deleteAllSubTasks() {
        subTasksList.clear();
        for (EpicTask epicTask : epicTasksList.values()) {
            epicTask.getSubIdList().clear();
            setEpicStatus(epicTask);
        }
    }

    public void deleteAllTasks() {
        tasksList.clear();
    }

    public void deleteAllEpicTasks() {
        epicTasksList.clear();
        subTasksList.clear();
    }

    void setEpicStatus(EpicTask epicTask) {
        int newCounter = 0;
        int doneCounter = 0;
        int inProgressCounter = 0;
        for (int id : epicTask.getSubIdList()) {
            if (Status.DONE.equals(subTasksList.get(id).getStatus())) {
                doneCounter += 1;
            } else if (Status.IN_PROGRESS.equals(subTasksList.get(id).getStatus())) {
                inProgressCounter += 1;
            } else {
                newCounter += 1;
            }
        }
        if (doneCounter > 0 && newCounter == 0 && inProgressCounter == 0)
            epicTask.setStatus(Status.DONE);
        else if (inProgressCounter > 0)
            epicTask.setStatus(Status.IN_PROGRESS);
        else epicTask.setStatus(Status.NEW);
    }
}
