public class SubTask extends Task{

    int epicId;

    @Override
    public String toString() {

        return "\n  Подзадача " + id + " " + name + " (" + description + ") " +
                "относится к глобальной задаче " + epicId +
                ", статус - " + status;
    }

    SubTask(int id, String name, String description, int epicId) {
        super(id,name,description);
        this.epicId = epicId;
    }


}