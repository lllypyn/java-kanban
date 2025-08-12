package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.List;

public interface TaskManager {
    void addTask(Task task);

    void addEpicTask(Epic epic);

    void addSubTask(SubTask subTask);

    void printAllTasks();

    Task searchByIdInTasks(int searchId);

    Epic searchByIdInEpicTasks(int searchId);

    SubTask searchByIdInSubTasks(int searchId);

    void updateTask(Task task);

    void updateEpicTask(Epic epic);

    void updateSubTask(SubTask subTask);

    void deleteEpicTask(int id);

    void deleteTask(int id);

    void deleteSubTask(int id);

    void deleteAllSubTasksInEpic(int id);

    void deleteAll();

    void deleteAllSubTasks();

    void deleteAllTasks();

    void deleteAllEpicTasks();

    void setEpicStatus(Epic epic);

    List<Task> getHistory();

    int getId();

}
