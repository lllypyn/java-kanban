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


    @Override
    public void remove(int id){  // удаляем по айди
        if(historyMap.containsKey(id)){ // проверяем есть ли в мапе
            removeNode(historyMap.get(id)); // удаляем ноду
            historyMap.remove(id);  // удаляем ноду из мапы
        }
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
        if(historyMap.containsKey(task.getId())){
             removeNode(historyMap.get(task.getId()));
             historyMap.remove(task.getId());
        }
        linkLast(task);
    }
    @Override
    public void clear() {
        Node<Task> current = head;
        while (current != null) {
            Node<Task> next = current.next;
            current.data = null;
            current.prev = null;
            current.next = null;
            current = next;
        }
        head = null;
        tail = null;
        size = 0;
    }

    List<Task> getTasks() {
        List<Task> list = new ArrayList<>();
        Node<Task> current = head;
        while (current != null){
            list.add(current.data);
            current = current.next;
        }
        return list;
    }

    void removeNode(Node<Task> node){
        Node<Task> prev = node.prev;
        Node<Task> next = node.next;
        if (prev == null){
            head = next;
        } else {
            prev.next = next;
        }
        if (next == null){
            tail = prev;
        } else {
            next.prev = prev;
        }
        size--;
    }

    void linkLast(Task task) {

        Node<Task> node = new Node<>(task);
        if (tail == null) {
            head = tail = node;
        } else {
            tail.next = node;
            node.prev =tail;
            tail = node;
        }
        history.add(task);
    }
}
