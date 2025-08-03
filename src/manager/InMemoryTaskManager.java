package manager;

import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {
    public final HashMap<Integer, Task> tasksList = new HashMap<>();
    public final HashMap<Integer, SubTask> subTasksList = new HashMap<>();
    public final HashMap<Integer, Epic> epicList = new HashMap<>();
    public int id = 0;
    HistoryManager historyManager = Managers.getDefaultHistory();

    @Override
    public int getId() {
        return id;
    }

    void addToHistory(Task task) {
        historyManager.add(task);
    }

    @Override
    public void clearHistory() {
        historyManager.clear();
    }

    @Override
    public List<Task> getHistory() {
        return historyManager.getHistory();
    }

    @Override
    public void addTask(Task task) {
        tasksList.put(task.getId(), task);
        id += 1;
    }

    @Override
    public void addEpicTask(Epic epic) {
        setEpicStatus(epic);
        epicList.put(epic.getId(), epic);
        id += 1;
    }

    @Override
    public void addSubTask(SubTask subTask) {
        if (epicList.containsKey(subTask.getEpicId())) {
            epicList.get(subTask.getEpicId()).setSubIdList(subTask.getId());
            subTasksList.put(subTask.getId(), subTask);
            setEpicStatus(epicList.get(subTask.getEpicId()));
            id += 1;
        } else {
            System.out.println("Невозможно добавить подзадачу к " + subTask.getEpicId() + " эпику, так как его нет");
        }
    }

    @Override
    public void printAllTasks() {
        for (int id : epicList.keySet()) {
            System.out.println(epicList.get(id));
        }
        for (int id : tasksList.keySet()) {
            System.out.println(tasksList.get(id));
        }
    }

    @Override
    public Task searchByIdInTasks(int searchId) {
        Task task = null;
        if (tasksList.containsKey(searchId)) {
            task = tasksList.get(searchId);
            addToHistory(task);
        }
        return task;
    }

    @Override
    public Epic searchByIdInEpicTasks(int searchId) {
        Epic epic = null;
        if (epicList.containsKey(searchId)) {
            epic = epicList.get(searchId);
            addToHistory(epic);
        }
        return epic;
    }

    @Override
    public SubTask searchByIdInSubTasks(int searchId) {
        SubTask subTask = null;
        if (subTasksList.containsKey(searchId)) {
            subTask = subTasksList.get(searchId);
            addToHistory(subTask);
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
            if (epicList.containsKey(epic.getId())) {
                ArrayList<Integer> subTasks = epicList.get(epic.getId()).getSubIdList();
                for (Integer subTask : subTasks) {
                    epic.setSubIdList(subTask);
                }
                epicList.put(epic.getId(), epic);
                setEpicStatus(epicList.get(epic.getId()));
            } else {
                System.out.println("Эпика под номером " + epic.getId() + " не найдено");
            }
        }
    }

    @Override
    public void updateSubTask(SubTask subTask) {
        if (subTask != null) {
            epicList.get(subTask.getEpicId()).getSubIdList().add(subTask.getId());
            setEpicStatus(epicList.get(subTask.getEpicId()));
            subTasksList.put(subTask.getId(), subTask);
        }
    }

    @Override
    public void deleteEpicTask(int id) {
        if (epicList.containsKey(id)) {
            deleteAllSubTasksInEpic(id);
            epicList.remove(id);
            historyManager.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    @Override
    public void deleteTask(int id) {
        if (tasksList.containsKey(id)) {
            tasksList.remove(id);
            historyManager.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    @Override
    public void deleteSubTask(int id) {
        if (subTasksList.containsKey(id)) {
            epicList.get(subTasksList.get(id).getEpicId()).getSubIdList().remove((Integer) id);
            setEpicStatus(epicList.get(subTasksList.get(id).getEpicId()));
            subTasksList.remove(id);
            historyManager.remove(id);
        } else {
            System.out.println("Такой задачи не найдено");
        }
    }

    @Override
    public void deleteAllSubTasksInEpic(int id) {
        if (epicList.containsKey(id) && (epicList.get(id) != null)) {
            ArrayList<Integer> deleteList = epicList.get(id).getSubIdList();
            for (Integer idSubTask : deleteList) {
                subTasksList.remove(idSubTask);
                historyManager.remove(idSubTask);
            }
            epicList.get(id).getSubIdList().clear();
            setEpicStatus(epicList.get(id));
        } else {
            System.out.println("подзадач нет");
        }
    }

    @Override
    public void deleteAll() {
        epicList.clear();
        tasksList.clear();
        subTasksList.clear();
        if (historyManager.getHistory() != null) {
            historyManager.clear();
        }
        System.out.println("все задачи, эпики и подзадачи удалены");
    }

    @Override
    public void deleteAllSubTasks() {
        for (SubTask subTask : subTasksList.values()) {
            historyManager.remove(subTask.getId());
        }
        subTasksList.clear();
        for (Epic epic : epicList.values()) {
            epic.getSubIdList().clear();
            setEpicStatus(epic);
        }
    }

    @Override
    public void deleteAllTasks() {
        for (Task task : tasksList.values()) {
            historyManager.remove(task.getId());
        }
        tasksList.clear();
    }

    @Override
    public void deleteAllEpicTasks() {
        for (SubTask subTask : subTasksList.values()) {
            historyManager.remove(subTask.getId());
        }
        for (Epic epicTask : epicList.values()) {
            historyManager.remove(epicTask.getId());
        }
        epicList.clear();
        subTasksList.clear();
    }

    @Override
    public void setEpicStatus(Epic epic) {
        int newCounter = 0;
        int doneCounter = 0;
        int inProgressCounter = 0;
        if (epic.getSubIdList() != null) {
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
        } else {
            epic.setStatus(Status.NEW);
        }
    }
}
