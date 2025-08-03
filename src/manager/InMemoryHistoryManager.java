package manager;

import task.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    public Node<Task> head;
    public Node<Task> tail;
    private int size = 0;

    HashMap<Integer, Node<Task>> historyMap = new HashMap<>();

    public static List<Task> history;

    InMemoryHistoryManager(){
    history = new ArrayList<>(10);
    }

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
