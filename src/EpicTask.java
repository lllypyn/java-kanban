import java.util.HashMap;

public class EpicTask extends Task {
    HashMap<String, SubTask> subTasksList;


    EpicTask(int id, String name, String description, HashMap<String, SubTask> subTasksList) {
        super(id, name, description);
        this.subTasksList = subTasksList;
        this.id = id;

    }

    int getId() {
        return id;
    }


}