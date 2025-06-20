import manager.TaskManager;
import task.Status;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();


        System.out.println("Поехали!");
        taskManager.createEpicTask("Отметить ДР", "поставить задачи");
        taskManager.createTask("Убраться", "навести порядок дома", Status.IN_PROGRESS);
        taskManager.createTask("Убраться", "навести порядок дома",Status.NEW);
        taskManager.createEpicTask("Отметить ДР", "поставить задачи");
        taskManager.createTask("Убраться", "навести порядок дома", Status.NEW);
        taskManager.createTask("Убраться", "навести порядок дома", Status.IN_PROGRESS);
        taskManager.createTask("Убраться", "навести порядок дома", Status.NEW);
        taskManager.createTask("Убраться", "навести порядок дома",Status.DONE);
        taskManager.createTask("Убраться", "навести порядок дома", Status.DONE);
        taskManager.createEpicTask("Привести машину в порядок", "Приготовить ее к поездке");
        taskManager.createSubTask("приготовить еду","Сделать салаты", 0, Status.DONE);
        taskManager.createSubTask("разложить стол","он в кладовке",0,Status.DONE);
        taskManager.createSubTask("накрыть на стол","как скатерть, так и еду",0,Status.DONE);
        taskManager.createSubTask("позвать гостей","можно и через мессенджеры",0,Status.DONE);
        taskManager.createSubTask("позвать гостей","можно и через мессенджеры",
                0,Status.IN_PROGRESS);
        taskManager.printAllTasks();
        taskManager.updateTask(taskManager.tasksList.get(1),"сделать мебель", "собрать стол", Status.IN_PROGRESS);
        taskManager.updateSubTask(taskManager.subTasksList.get(11),"сесть","встать",Status.NEW);
        System.out.println(taskManager.searchById(2));
        taskManager.updateEpicTask(taskManager.epicTasksList.get(2),"Отпраздновать день рождения",
                "Устроить все как надо");
        taskManager.deleteTask(4);
        taskManager.deleteSubTask(15);
        taskManager.deleteEpicTask(8);
        taskManager.deleteAllSubTasks();
        taskManager.createTask("Убраться", "навести порядок дома", Status.DONE);
        taskManager.createEpicTask("Привести машину в порядок", "Приготовить ее к поездке");
        taskManager.createSubTask("приготовить еду","Сделать салаты", 0, Status.DONE);
        taskManager.createSubTask("разложить стол","он в кладовке",3,Status.DONE);
        taskManager.createSubTask("накрыть на стол","как скатерть, так и еду",3,Status.DONE);
        taskManager.createSubTask("позвать гостей","можно и через мессенджеры",3,Status.DONE);
        taskManager.deleteAllTasks();

        System.out.println(taskManager.searchById(2));
        taskManager.deleteAllSubTasksInEpic(3);
        taskManager.deleteAllEpicTasks();
        taskManager.deleteAll();


        System.out.println(taskManager.searchById(8));





    }
}
