public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();


        System.out.println("Поехали!");
        taskManager.addTask("Убраться", "навести порядок дома",Status.IN_PROGRESS);
        taskManager.addTask("Убраться", "навести порядок дома",Status.NEW);
        taskManager.addEpicTask("Отметить ДР", "поставить задачи");
        taskManager.addTask("Убраться", "навести порядок дома", Status.NEW);
        taskManager.addTask("Убраться", "навести порядок дома", Status.IN_PROGRESS);
        taskManager.addTask("Убраться", "навести порядок дома", Status.NEW);
        taskManager.addTask("Убраться", "навести порядок дома",Status.DONE);
        taskManager.addTask("Убраться", "навести порядок дома", Status.DONE);
        taskManager.addEpicTask("Привести машину в порядок", "Приготовить ее к поездке");
        taskManager.addSubTask("приготовить еду","Сделать салаты", 2, Status.DONE);
        taskManager.addSubTask("разложить стол","он в кладовке",2,Status.DONE);
        taskManager.addSubTask("накрыть на стол","как скатерть, так и еду",2,Status.DONE);
        taskManager.addSubTask("позвать гостей","можно и через мессенджеры",2,Status.DONE);
        taskManager.addSubTask("позвать гостей","можно и через мессенджеры",8,Status.IN_PROGRESS);
        taskManager.printAllTasks();
        System.out.println(taskManager.searchById(2));
        taskManager.updateTask(2,"Отпраздновать день рождения","Устроить все как надо");
        System.out.println(taskManager.searchById(2));
        taskManager.deleteAllSubTasksInEpic(8);
        System.out.println(taskManager.searchById(8));





    }
}
