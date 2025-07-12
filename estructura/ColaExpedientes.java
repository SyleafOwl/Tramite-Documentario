package estructura;
import java.util.LinkedList;
import java.util.Queue;
import modela.Expediente;

public class ColaExpedientes {
    private Queue<Expediente> cola = new LinkedList<>();

    public void enqueue(Expediente exp) {
        cola.add(exp);
    }

    public Expediente dequeue() {
        return cola.isEmpty() ? null : cola.poll();
    }

    public boolean isEmpty() {
        return cola.isEmpty();
    }

    public java.util.List<Expediente> getAll() {
        return new java.util.ArrayList<>(cola);
    }
}