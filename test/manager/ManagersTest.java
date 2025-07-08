package manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class ManagersTest {
  static InMemoryTaskManager manager = new InMemoryTaskManager();

  @BeforeAll
  static void create() {
    manager.addTask(new Task(manager.id, "обычная задача", "пояснение к обычной", Status.NEW));
    manager.addEpicTask(new Epic(manager.id, "Эпик задача", "пояснение к эпику"));
    manager.addSubTask(new SubTask(manager.id, "Субтаск", "пояснение к субтаску", 1, Status.DONE));
    manager.addSubTask(new SubTask(manager.id, "субтаск 2", "пояснение к субтаску 2", 1, Status.IN_PROGRESS));
    manager.addTask(new Task(manager.id, "обычная задача2", "пояснение к обычной2", Status.DONE));
  }

  @Test
  void checkListHistoryIsEmpty() {
    assertNull(manager.getHistory().getHistory(), "размер списка историй не совпадает с ожидаемым 0");
  }

  @Test
  void checkForHistoryChanges() {
    manager.getHistory().getHistory().clear();
    manager.searchById(4);
    manager.searchById(0);
    assertEquals(2, manager.getHistory().getHistory().size(), "История просмотров задач не изменяется");
  }

  @Test
  void updatingHistoryWhenAdding11Tasks() {
    manager.searchById(0);
    for (int i = 0; i < 9; i++) {
      manager.searchById(1);
    }
    manager.searchById(2);
    assertEquals(manager.getHistory().getHistory().getLast(), manager.searchById(2));
    assertEquals(manager.getHistory().getHistory().getFirst(), manager.searchById(1));
  }

  @Test
  void checkSearchingWithLists() {
    Epic epic = new Epic(100, "название", "пояснение");
    manager.addEpicTask(epic);
    SubTask subTask = new SubTask(200, "name", "description", 100, Status.DONE);
    manager.addSubTask(subTask);
    System.out.println(manager.searchById(100));
    assertEquals(epic,manager.searchById(100), "поиск по айди в списке эпиктасков не работает");
    assertEquals(subTask,manager.searchById(200),"поиск по айди в списке сабтасков не работает");
  }
  @Test
  void checkSaveHistoryByOneId(){ // проверка на сохранение разных версий одной и той же задачи
    manager.searchById(1);
    manager.searchById(2);
    manager.updateEpicTask(new Epic(1,"NewEpicTask","NewDescription"));
    manager.searchById(1);
    assertNotEquals(manager.getHistory().getHistory().getFirst().getName(),manager.getHistory().getHistory().getLast().getName(),"Версии задач одинаковые");
  }

  @Test
  void testManager(){
    assertEquals(manager.getClass(),Managers.getDefault().getClass(),"менеджер вызывает неверный объект");
  }
}