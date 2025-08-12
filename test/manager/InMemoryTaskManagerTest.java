package manager;

import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryTaskManagerTest {

    @Test
    void chekEqualityTaskBySingleId() {
        Task task = new Task(1, "задача", "пояснение");
        Task task1 = new Task(1, "Сложная задача", "Сложное пояснение");
        assertEquals(task1, task, "Задачи не равны");
    }

    @Test
    void verifyEqualityOfTheHeirs() {
        Task subTask = new SubTask(1, "name", "description", 1, Status.NEW);
        Task subTask2 = new SubTask(1, "name1", "description1", 2, Status.NEW);
        Task epicTask = new Epic(1, "name", "description");
        Task epicTask2 = new Epic(1, "name2", "description2");
        assertEquals(subTask, subTask2, "Субтаски с одинаковыми айди не равны");
        assertEquals(epicTask, epicTask2, "Эпиктаски с одинаковыми айди не равны");
    }

    @Test
    void testOfAddGenerationAndEntered(){
        TaskManager manager = Managers.getDefault();
        manager.addTask(new Task(manager.getId(),"autoname1","description",Status.DONE));
        manager.addTask(new Task(manager.getId(),"autoname2","description",Status.DONE));
        System.out.println(manager.getId());
        manager.addTask(new Task(2,"name","description",Status.DONE));
        System.out.println(manager.getId());
        manager.addTask(new Task(manager.getId(), "autoname with entered","description",Status.DONE));
        System.out.println(manager.getId());

    }
}