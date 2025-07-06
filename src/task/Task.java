package task;

import java.util.Objects;

public class Task {
    protected Status status;
    protected int id;
    protected String name;
    protected String description;

    public Task(int id, String name, String description, Status status) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        Task task = (Task) object;
        return id == task.id;
    }

    @Override
    public String toString() {
        return "Задача " + id + ": " + name + " (" + description + ") " + "статус: " + status;
    }


    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
