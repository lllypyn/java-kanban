package manager;

import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import java.util.HashMap;

public class InMemoryTaskManager implements TaskManager {
    public final HashMap<Integer, Task> tasksList = new HashMap<>();
    public final HashMap<Integer, SubTask> subTasksList = new HashMap<>();
    public final HashMap<Integer, Epic> epicTasksList = new HashMap<>();

    public int id = 0;

    @Override
    public HistoryManager getHistory() {
        return Managers.getDefaultHistory();
    }

    @Override
    public void addTask(Task task) {
        tasksList.put(task.getId(), task);
        id += 1;
    }

    @Override
    public void addEpicTask(Epic epic) {
        setEpicStatus(epic);
        epicTasksList.put(epic.getId(), epic);
        id += 1;
    }

    @Override
    public void addSubTask(SubTask subTask) {
        epicTasksList.get(subTask.getEpicId()).setSubIdList(subTask.getId());
        subTasksList.put(subTask.getId(), subTask);
        setEpicStatus(epicTasksList.get(subTask.getEpicId()));
        id += 1;
    }

    @Override
    public void printAllTasks() {
        for (int id : epicTasksList.keySet()) {
            System.out.println(epicTasksList.get(id));
        }
        for (int id : tasksList.keySet()) {
            System.out.println(tasksList.get(id));
        }
    }

    @Override //  если не критично пока оставлю, переделаю чуть позже
    public Object searchById(int searchId) {
        if (searchByIdInTasks(searchId) != null) {
            getHistory().add(tasksList.get(searchId));
            return searchByIdInTasks(searchId);
        } else if (searchByIdInEpicTasks(searchId) != null) {
            getHistory().add(epicTasksList.get(searchId));
            return searchByIdInEpicTasks(searchId);
        } else if (searchByIdInSubTasks(searchId) != null) {
            getHistory().add(subTasksList.get(searchId));
            return searchByIdInSubTasks(searchId);
        } else {
            System.out.println("Такой задачи не найдено");
            return null;
        }
    }

    @Override
    public Task searchByIdInTasks(int searchId) {
        Task task = null;
        if (tasksList.containsKey(searchId)) {
            task = tasksList.get(searchId);
        }
        return task;
    }

    @Override
    public Epic searchByIdInEpicTasks(int searchId) {
        Epic epic = null;
        if (epicTasksList.containsKey(searchId)) {
            epic = epicTasksList.get(searchId);
        }
        return epic;
    }

    @Override
    public SubTask searchByIdInSubTasks(int searchId) {
        SubTask subTask = null;
        if (subTasksList.containsKey(searchId)) {
            subTask = subTasksList.get(searchId);
        }
        return subTask;
    }

    @Override
    public void updateTask(Task task) {
        if (task != null) {
            tasksList.put(task.getId(), task);
        }
    }

    @Override
    public void updateEpicTask(Epic epic) {
        if (epic != null) {
            epicTasksList.put(epic.getId(), epic);
        }
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        if (subTask != null) {
            epicTasksList.get(subTask.getEpicId()).getSubIdList().add(subTask.getId());
            setEpicStatus(epicTasksList.get(subTask.getEpicId()));
            subTasksList.put(subTask.getId(), subTask);
        }
    }

    @Override
    public void deleteEpicTask(int id) {
        if (epicTasksList.containsKey(id)) {
            deleteAllSubTasksInEpic(id);
            epicTasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    @Override
    public void deleteTask(int id) {
        if (tasksList.containsKey(id)) {
            tasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    @Override
    public void deleteSubTask(int id) {
        if (subTasksList.containsKey(id)) {
            epicTasksList.get(subTasksList.get(id).getEpicId()).getSubIdList().remove((Integer) id);

            setEpicStatus(epicTasksList.get(subTasksList.get(id).getEpicId()));
            subTasksList.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    @Override
    public void deleteAllSubTasksInEpic(int id) {
        searchById(id);
        if (searchById(id).getClass().equals(Epic.class) && searchById(id) != null) {
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

    @Override
    public void deleteAll() {
        epicTasksList.clear();
        tasksList.clear();
        subTasksList.clear();
        System.out.println("все задачи, эпики и подзадачи удалены");
    }

    @Override
    public void deleteAllSubTasks() {
        subTasksList.clear();
        for (Epic epic : epicTasksList.values()) {
            epic.getSubIdList().clear();
            setEpicStatus(epic);
        }
    }

    @Override
    public void deleteAllTasks() {
        tasksList.clear();
    }

    @Override
    public void deleteAllEpicTasks() {
        epicTasksList.clear();
        subTasksList.clear();
    }

    @Override
    public void setEpicStatus(Epic epic) {
        int newCounter = 0;
        int doneCounter = 0;
        int inProgressCounter = 0;
        for (int id : epic.getSubIdList()) {
            if (Status.DONE.equals(subTasksList.get(id).getStatus())) {
                doneCounter += 1;
            } else if (Status.IN_PROGRESS.equals(subTasksList.get(id).getStatus())) {
                inProgressCounter += 1;
            } else {
                newCounter += 1;
            }
        }
        if (doneCounter > 0 && newCounter == 0 && inProgressCounter == 0) epic.setStatus(Status.DONE);
        else if (inProgressCounter > 0) epic.setStatus(Status.IN_PROGRESS);
        else epic.setStatus(Status.NEW);
    }
}
