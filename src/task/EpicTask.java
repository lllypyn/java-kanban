package task;

import java.util.ArrayList;

public class EpicTask extends Task {
    public ArrayList<SubTask> subIdList = new ArrayList<>();


    public EpicTask(int id, String name, String description) {
        super(id,name,description);
    }

    @Override
    public String toString() {
        return "Глобальная задача - "+ id + " " + name + " (" + description + "). Статус - " + status +
                ".\n Список подзадач:\n" + subIdList;
    }

}