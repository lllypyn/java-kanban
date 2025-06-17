import java.util.HashMap;

public class EpicTask extends Task {
    HashMap<String, SubTask> subTasksList;

    private Status setStatus() {
        int newCounter = 0;
        int doneCounter = 0;
        for (SubTask subTask : subIdList){
            if(subTask.status == Status.DONE){
                doneCounter += 1;
            } else {
                newCounter += 1;
            }
        }
        if (doneCounter > 0 && newCounter == 0)
            status = Status.DONE;
        else if (doneCounter > 0 && newCounter > 0)
            status = Status.IN_PROGRESS;
        else status = Status.NEW;
        return status;
    }

    @Override
    public String toString() {
        return "Глобальная задача - "+ id + " " + name + " (" + description + "). Статус - " + status +
                ".\n Список подзадач:\n" + subIdList;
    }

    EpicTask(int id, String name, String description) {
        super(id, name, description);
        this.subTasksList = subTasksList;
        this.id = id;

    }

    int getId() {
        return id;
    }


}