import manager.InMemoryTaskManager;
import manager.Managers;
import task.EpicTask;
import task.Status;
import task.SubTask;
import task.Task;

public class Main {

    public static void main(String[] args) {
        InMemoryTaskManager manager = Managers.getDefault();

        for (int i = 0; i < 5; i++) {
            System.out.println("добавляем глобальную задачу " + manager.id);
            manager.addEpicTask(new EpicTask(manager.id, "Гл.задача", "Гл.пояснение"));
            for (int j = 0; j < 9; j++) {
                manager.addSubTask(new SubTask(manager.id, "подзадача", "подз.пояснение", (i * 10), Status.DONE));
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("добавляем обычную задачу" + manager.id);
            manager.addTask(new Task(manager.id, "об.задача", "об.пояснение", Status.NEW));
        }

        manager.printAllTasks();
        //# обновление
        manager.updateTask(new Task(2, "купить продукты", "сходить в магазин", Status.NEW));
        manager.updateSubTask(new SubTask(9, "сесть", "встать", 0, Status.NEW));
        manager.updateEpicTask(new EpicTask(3, "Отпраздновать день рождения", "Устроить все как надо"));
        System.out.println(manager.searchById(2));
        //# удаление
        manager.deleteTask(4);
        manager.deleteSubTask(14);
        manager.deleteEpicTask(10);
        manager.deleteAllSubTasks();
        manager.addTask(new Task(manager.id, "Убраться", "навести порядок дома", Status.DONE));
        manager.addEpicTask(new EpicTask(manager.id, "Привести машину в порядок", "Приготовить ее к поездке"));
        manager.addSubTask(new SubTask(manager.id, "приготовить еду", "Сделать салаты", 0, Status.DONE));
        manager.addSubTask(new SubTask(manager.id, "разложить стол", "он в кладовке", 3, Status.IN_PROGRESS));
        manager.addSubTask(new SubTask(manager.id, "накрыть на стол", "как скатерть, так и еду", 3, Status.DONE));
        manager.addSubTask(new SubTask(manager.id, "позвать гостей", "можно и через мессенджеры", 3, Status.DONE));
        manager.deleteAllTasks();
        System.out.println(manager.searchById(5));
        manager.deleteAllSubTasksInEpic(0);
        manager.printAllTasks();
        manager.deleteAllEpicTasks();
        manager.deleteAll();
        manager.printAllTasks();
    }
}
