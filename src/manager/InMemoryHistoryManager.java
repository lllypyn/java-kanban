package manager;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    public static List<Task> history = new ArrayList<>(10);

    @Override
    public List<Task> getHistory() {
        if (history.isEmpty()) {
            return null;
        } else {
            return history;
        }
    }

    @Override
    public String toString() {
        return history.toString();
    }

    @Override
    public void add(Task task) {
        if (history.size() == 10 && task != null) {
            history.removeFirst();
        }
        history.add(task);
    }
}
