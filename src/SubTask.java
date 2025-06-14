public class SubTask extends Task{

    int epicId;


    SubTask(int id, String name, String description, int epicId) {
        super(id,name,description);
        this.epicId = epicId;
    }


}