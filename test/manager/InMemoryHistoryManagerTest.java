package manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class InMemoryHistoryManagerTest {

    static HistoryManager manager = Managers.getDefaultHistory();

    @BeforeAll
    public static void create() {
        Task epic1 = new Epic(1, "Эпик1", "пояснение к эпику 1");
        Task epic2 = new Epic(2, "Эпик2", "пояснение к эпику 2");
        Task task = new Task(10, "таск", "пояснение к таску", Status.DONE);
        Task sub1 = new SubTask(11, "субтаск 1", "пояснение к субтаску 1", 1, Status.NEW);
        Task sub2 = new SubTask(12, "субтаск 2", "пояснение к субтаску2 ", 2, Status.NEW);
        manager.add(epic1);
        manager.add(epic2);
        manager.add(task);
        manager.add(sub1);
        manager.add(sub2);
    }

    @Test
    void checkForDeletionAllFromHistory() {
        System.out.println(manager.getHistory());
        manager.clear();
        assertNull(manager.getHistory(), "возвращаемое значение должно быть Null");
    }

    @Test
    void checkTheHistoryWhenAddingDuplicateView() {
        System.out.println(manager.getHistory().getFirst());
        manager.add(new Epic(1, "Эпик", "пояснение к эпику"));
        assertNotEquals(manager.getHistory().getFirst(), manager.getHistory().get(1), "добавляемая задача не удалилась из начала списка");
    }
}
