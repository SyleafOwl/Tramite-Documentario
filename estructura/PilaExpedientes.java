package estructura;
import java.util.Stack;
import modela.Expediente;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;

public class PilaExpedientes {
    private Stack<Expediente> pila = new Stack<>();
    private DefaultListModel<Expediente> finalizadosModel = new DefaultListModel<>();
    private JList<Expediente> finalizadosList = new JList<>(finalizadosModel);

    public void push(Expediente exp) {
        pila.push(exp);
    }

    public Expediente pop() {
        return pila.isEmpty() ? null : pila.pop();
    }

    public boolean isEmpty() {
        return pila.isEmpty();
    }

    public java.util.Stack<Expediente> getStack() {
        return pila;
    }
}