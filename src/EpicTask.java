import java.util.ArrayList;

public class EpicTask extends Task {
    ArrayList<SubTask> subIdList = new ArrayList<>();
    Status status = setStatus();

     Status setStatus() {
        int newCounter = 0;
        int doneCounter = 0;
        int inProgressCounter = 0;
        for (SubTask subTask : subIdList){
            if(Status.DONE.equals(subTask.status)){
                doneCounter += 1;
            }else if (subTask.status.equals(Status.IN_PROGRESS)){
                inProgressCounter += 1;
            } else {
                newCounter +=1;
            }
        }
        if (doneCounter > 0 && newCounter == 0 && inProgressCounter == 0)
            status = Status.DONE;
        else if (inProgressCounter > 0)
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
        this.id = id;


    }
}