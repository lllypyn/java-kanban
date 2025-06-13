import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Поехали!");
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Убраться", "навести порядок дома", false);
        taskManager.addTask("Привести машину в порядок", "Приготовить ее к поездке", true);




    }
}
