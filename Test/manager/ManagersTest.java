package manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.EpicTask;
import task.Status;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
  static InMemoryTaskManager manager = new InMemoryTaskManager();

  @BeforeAll
  static void create() {
    manager.addTask(new Task(manager.id, "обычная задача", "пояснение к обычной", Status.NEW));
    manager.addEpicTask(new EpicTask(manager.id, "Эпик задача", "пояснение к эпику"));
    manager.addSubTask(new SubTask(manager.id, "Субтаск", "пояснение к субтаску", 1, Status.DONE));
    manager.addSubTask(new SubTask(manager.id, "субтаск 2", "пояснение к субтаску 2", 1, Status.IN_PROGRESS));
    manager.addTask(new Task(manager.id, "обычная задача2", "пояснение к обычной2", Status.DONE));
  }

  @Test
  void checkListHistoryIsEmpty() {
    assertNull(manager.history.getHistory(), "размер списка историй не совпадает с ожидаемым 0");
  }

  @Test
  void checkForHistoryChanges() {
    manager.searchById(4);
    manager.searchById(0);
    assertEquals(2, manager.history.getHistory().size(), "История просмотров задач не изменяется");
  }

  @Test
  void updatingHistoryWhenAdding11Tasks() {
    manager.searchById(0);
    for (int i = 0; i < 9; i++) {
      manager.searchById(1);
    }
    manager.searchById(2);
    assertEquals(manager.history.getHistory().getLast(), manager.searchById(2));
    assertEquals(manager.history.getHistory().getFirst(), manager.searchById(1));
  }
  @Test
  void checkSearchingWithLists() {
    EpicTask epicTask = new EpicTask(100, "название", "пояснение");
    manager.addEpicTask(epicTask);
    SubTask subTask = new SubTask(200, "name", "description", 100, Status.DONE);
    manager.addSubTask(subTask);
    System.out.println(manager.searchById(100));
    assertEquals(epicTask,manager.searchById(100), "поиск по айди в списке эпиктасков не работает");
    assertEquals(subTask,manager.searchById(200),"поиск по айди в списке сабтасков не работает"));
  }
}