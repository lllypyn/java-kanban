public class Main {

    public static void main(String[] args) {
        TaskManager taskManager = new TaskManager();


        System.out.println("Поехали!");
        taskManager.createTask("Убраться", "навести порядок дома",Status.IN_PROGRESS);
        taskManager.createTask("Убраться", "навести порядок дома",Status.NEW);
        taskManager.createEpicTask("Отметить ДР", "поставить задачи");
        taskManager.createTask("Убраться", "навести порядок дома", Status.NEW);
        taskManager.createTask("Убраться", "навести порядок дома", Status.IN_PROGRESS);
        taskManager.createTask("Убраться", "навести порядок дома", Status.NEW);
        taskManager.createTask("Убраться", "навести порядок дома",Status.DONE);
        taskManager.createTask("Убраться", "навести порядок дома", Status.DONE);
        taskManager.createEpicTask("Привести машину в порядок", "Приготовить ее к поездке");
        taskManager.createSubTask("приготовить еду","Сделать салаты", 2, Status.DONE);
        taskManager.createSubTask("разложить стол","он в кладовке",2,Status.DONE);
        taskManager.createSubTask("накрыть на стол","как скатерть, так и еду",2,Status.DONE);
        taskManager.createSubTask("позвать гостей","можно и через мессенджеры",2,Status.DONE);
        taskManager.createSubTask("позвать гостей","можно и через мессенджеры",
                8,Status.IN_PROGRESS);
        taskManager.printAllTasks();
        System.out.println(taskManager.searchById(2));
        taskManager.updateEpicTask(taskManager.epicTasksList.get(2),"Отпраздновать день рождения",
                "Устроить все как надо");
        System.out.println(taskManager.searchById(2));
        taskManager.deleteAllSubTasksInEpic(8);
        System.out.println(taskManager.searchById(8));





    }
}
