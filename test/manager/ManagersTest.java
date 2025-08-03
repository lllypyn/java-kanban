package manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
    static TaskManager manager = Managers.getDefault();

    @BeforeAll
    static void create() {
        manager.addTask(new Task(manager.getId(), "обычная задача", "пояснение к обычной", Status.NEW));
        manager.addEpicTask(new Epic(manager.getId(), "Эпик задача", "пояснение к эпику"));
        manager.addSubTask(new SubTask(manager.getId(), "Субтаск", "пояснение к субтаску", 1, Status.DONE));
        manager.addSubTask(new SubTask(manager.getId(), "субтаск 2", "пояснение к субтаску 2", 1, Status.IN_PROGRESS));
        manager.addTask(new Task(manager.getId(), "обычная задача2", "пояснение к обычной2", Status.DONE));
    }

    @Test
    void checkListHistoryIsEmpty() {
        assertNull(manager.getHistory(), "размер списка историй не совпадает с ожидаемым 0");
    }

    @Test
    void checkListHistoryIsNotEmpty() {
        manager.searchByIdInEpicTasks(1);
        assertNotNull(manager.getHistory());
    }

    @Test
    void checkForHistoryChanges() {
        manager.searchByIdInEpicTasks(1);
        manager.clearHistory();
        manager.searchByIdInSubTasks(2);
        assertEquals(1, manager.getHistory().size(), "История просмотров задач не изменяется");
    }

    @Test
    void updatingHistoryWhenAdding11Tasks() {
        manager.searchByIdInEpicTasks(1);
        for (int i = 0; i < 9; i++) {
            manager.searchByIdInTasks(0);
        }
        manager.searchByIdInSubTasks(2);
        assertEquals(manager.getHistory().getLast(), manager.searchByIdInSubTasks(2));
        System.out.println(manager.getHistory());
        assertEquals(manager.getHistory().getFirst(), manager.searchByIdInEpicTasks(1));
    }

    @Test
    void checkSearchingWithLists() {
        Epic epic = new Epic(100, "название", "пояснение");
        manager.addEpicTask(epic);
        SubTask subTask = new SubTask(200, "name", "description", 100, Status.DONE);
        manager.addSubTask(subTask);
        System.out.println(manager.searchByIdInEpicTasks(100));
        assertEquals(epic, manager.searchByIdInEpicTasks(100), "поиск по айди в списке эпиктасков не работает");
        assertEquals(subTask, manager.searchByIdInSubTasks(200), "поиск по айди в списке сабтасков не работает");
    }


    @Test
    void testManager() {
        assertEquals(manager.getClass(), Managers.getDefault().getClass(), "менеджер вызывает неверный объект");
    }
}