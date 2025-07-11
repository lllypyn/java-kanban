import manager.Managers;
import manager.TaskManager;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

public class Main {

    public static void main(String[] args) {
        TaskManager manager = Managers.getDefault();

        for (int i = 0; i < 5; i++) {
            System.out.println("добавляем глобальную задачу " + manager.getId());
            manager.addEpicTask(new Epic(manager.getId(), "Гл.задача", "Гл.пояснение"));
            for (int j = 0; j < 9; j++) {
                manager.addSubTask(new SubTask(manager.getId(), "подзадача", "пояснение", (i * 10), Status.DONE));
            }
        }
        for (int i = 0; i < 5; i++) {
            System.out.println("добавляем обычную задачу" + manager.getId());
            manager.addTask(new Task(manager.getId(), "об.задача", "об.пояснение", Status.NEW));
        }

        manager.printAllTasks();
        //# обновление
        manager.updateTask(new Task(2, "купить продукты", "сходить в магазин", Status.NEW));
        manager.updateSubTask(new SubTask(9, "сесть", "встать", 0, Status.NEW));
        manager.updateEpicTask(new Epic(3, "Отпраздновать день рождения", "Устроить все как надо"));
        System.out.println(manager.searchByIdInEpicTasks(2));
        //# удаление
        manager.deleteTask(4);
        manager.deleteSubTask(14);
        manager.deleteEpicTask(10);
        manager.deleteAllSubTasks();
        manager.addTask(new Task(manager.getId(), "Убраться", "навести порядок дома", Status.DONE));
        manager.addEpicTask(new Epic(88, "Привести машину в порядок", "Приготовить ее к поездке"));
        manager.addSubTask(new SubTask(manager.getId(), "приготовить еду", "Сделать салаты", 0, Status.DONE));
        manager.addSubTask(new SubTask(manager.getId(), "разложить стол", "он в кладовке", 3, Status.IN_PROGRESS));
        manager.addSubTask(new SubTask(manager.getId(), "накрыть на стол", "как скатерть, так и еду", 3, Status.DONE));
        manager.addSubTask(new SubTask(manager.getId(), "позвать гостей", "можно и через мессенджеры", 3, Status.DONE));
        manager.deleteAllTasks();
        System.out.println(manager.searchByIdInEpicTasks(88));

        manager.deleteAllSubTasksInEpic(0);
        manager.printAllTasks();
        manager.deleteAllEpicTasks();
        manager.deleteAll();
        manager.printAllTasks();
    }
}
