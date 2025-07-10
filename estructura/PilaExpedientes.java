package estructura;
import java.util.Stack;
import modela.Expediente;

public class PilaExpedientes {
    private Stack<Expediente> pila = new Stack<>();

    public void push(Expediente exp) {
        pila.push(exp);
    }

    public Expediente pop() {
        return pila.isEmpty() ? null : pila.pop();
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }
}