public class SubTask extends Task{

    int epicId;

    SubTask(int id, String name, String description, int epicId, Status status) {
        super(id,name,description,status);
        this.epicId = epicId;
    }

    @Override
    public String toString() {

        return "\n  Подзадача " + id + " " + name + " (" + description + ") " +
                "относится к глобальной задаче " + epicId +
                ", статус - " + status;
    }

}