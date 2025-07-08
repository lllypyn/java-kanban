package task;

import java.util.ArrayList;

public class EpicTask extends Task {
    private final ArrayList<Integer> subIdList;

    public EpicTask(int id, String name, String description) {

        super(id, name, description);
        subIdList = new ArrayList<>();
    }

    public ArrayList<Integer> getSubIdList() {
        return subIdList;
    }

    public void setSubIdList(int id) {
        subIdList.add(id);
    }

    @Override
    public String toString() {
        return "Глобальная задача - " + id + " " + name + " (" + description + "). Статус - " + status +
                ".\n Список подзадач:\n" + subIdList;
    }
}
