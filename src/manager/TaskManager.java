package manager;

import task.EpicTask;
import task.SubTask;
import task.Task;

public interface TaskManager {
    void addTask(Task task);

    void addEpicTask(EpicTask epicTask);

    void addSubTask(SubTask subTask);

    void printAllTasks();

    Object searchById(int searchId);

    Task searchByIdInTasks(int searchId);

    EpicTask searchByIdInEpicTasks(int searchId);

    SubTask searchByIdInSubTasks(int searchId);

    void updateTask(Task task);

    void updateEpicTask(EpicTask epicTask);

    void updateSubTask(SubTask subTask);

    void deleteEpicTask(int id);

    void deleteTask(int id);

    void deleteSubTask(int id);

    void deleteAllSubTasksInEpic(int id);

    void deleteAll();

    void deleteAllSubTasks();

    void deleteAllTasks();

    void deleteAllEpicTasks();

    void setEpicStatus(EpicTask epicTask);

    HistoryManager getHistory();

}
