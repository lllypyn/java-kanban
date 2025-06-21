import manager.TaskManager;
import task.EpicTask;
import task.Status;
import task.SubTask;
import task.Task;

public class Main{

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();

        //## Tests
        //# добавление

        for (int i = 0; i < 5; i++){
            System.out.println("добавляем глобальную задачу " + taskManager.id);
            taskManager.addEpicTask(new EpicTask(taskManager.id, "Гл.задача", "Гл.пояснение"));
            for (int j = 0; j < 9; j++){
                taskManager.addSubTask(new SubTask(taskManager.id, "подзадача", "подз.пояснение",
                        (i * 10), Status.DONE));
            }
        }
        for (int i = 0; i < 5; i++){
            System.out.println("добавляем обычную задачу" + taskManager.id);
            taskManager.addTask(new Task(taskManager.id, "об.задача", "об.пояснение", Status.NEW));
        }

        taskManager.printAllTasks();
        //# обновление
        taskManager.updateTask(new Task(2, "купить продукты", "сходить в магазин", Status.NEW));
        taskManager.updateSubTask(new SubTask(9, "сесть", "встать", 0, Status.NEW));
        taskManager.updateEpicTask(new EpicTask(3, "Отпраздновать день рождения", "Устроить все как надо"));
        System.out.println(taskManager.searchById(2));
        //# удаление
        taskManager.deleteTask(4);
        taskManager.deleteSubTask(14);
        taskManager.deleteEpicTask(10);
        taskManager.deleteAllSubTasks();
        taskManager.addTask(new Task(taskManager.id, "Убраться", "навести порядок дома", Status.DONE));
        taskManager.addEpicTask(new EpicTask(taskManager.id, "Привести машину в порядок", "Приготовить ее к поездке"));
        taskManager.addSubTask(new SubTask(taskManager.id, "приготовить еду", "Сделать салаты", 0, Status.DONE));
        taskManager.addSubTask(new SubTask(taskManager.id, "разложить стол", "он в кладовке", 3, Status.IN_PROGRESS));
        taskManager.addSubTask(new SubTask(taskManager.id, "накрыть на стол", "как скатерть, так и еду", 3, Status.DONE));
        taskManager.addSubTask(new SubTask(taskManager.id, "позвать гостей", "можно и через мессенджеры", 3, Status.DONE));
        taskManager.deleteAllTasks();
        System.out.println(taskManager.searchById(5));
        taskManager.deleteAllSubTasksInEpic(0);
        taskManager.printAllTasks();
        taskManager.deleteAllEpicTasks();
        taskManager.deleteAll();
        taskManager.printAllTasks();
    }
}
