import java.util.Objects;

public class Task {
    String name;
    Status status;
    String description;
    int id;

    Task(int id, String name, String description, Status status) {
        this.name = name;
        this.description = description;
        this.id = id;
        this.status = status;
    }

    public Task(int id, String name, String description) {
        this.name = name;
        this.description = description;
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Задача " + id + ": " + name + " (" + description + ") " + "статус: " + status;
    }
}
