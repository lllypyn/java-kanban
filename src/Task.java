import java.util.Objects;

public class Task {
    String name;
    Status status = Status.NEW;
    String description;
    int id;

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return id == task.id && Objects.equals(name, task.name) && status == task.status && Objects.equals(description, task.description);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }



    Task(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }



}
