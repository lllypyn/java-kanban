import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Поехали!");
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Отметить ДР", "поставить задачи", true);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Привести машину в порядок", "Приготовить ее к поездке", true);
        taskManager.addSubTask(taskManager.tasksList.get(2),"приготовить еду","Сделать салаты");
        taskManager.addSubTask(taskManager.tasksList.get(2),"разложить стол","он в кладовке");
        taskManager.addSubTask(taskManager.tasksList.get(2),"накрыть на стол","как скатерть, так и еду");
        taskManager.addSubTask(taskManager.tasksList.get(2),"позвать гостей","можно и через мессенджеры");




    }
}
