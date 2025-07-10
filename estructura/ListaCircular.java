package estructura;

public class ListaCircular<T> {
    private Nodo<T> cabeza;

    public void agregar(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevo;
            cabeza.siguiente = cabeza;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.siguiente != cabeza) {
                actual = actual.siguiente;
            }
            actual.siguiente = nuevo;
            nuevo.siguiente = cabeza;
        }
    }
    // Método para recorrer circularmente
    public void recorrer() {
        if (cabeza == null) return;
        Nodo<T> actual = cabeza;
        do {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        } while (actual != cabeza);
    }
}