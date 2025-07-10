package estructura;

import modela.Expediente;

public class ListaDoble<T> {
    private NodoDoble<T> cabeza, cola;

    public void agregarAlFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);
        if (cabeza == null) {
            cabeza = cola = nuevo;
        } else {
            cola.siguiente = nuevo;
            nuevo.anterior = cola;
            cola = nuevo;
        }
    }
    // Métodos para navegar adelante y atrás
    public void recorrerAdelante() {
        NodoDoble<T> actual = cabeza;
        while (actual != null) {
            System.out.println(actual.dato);
            actual = actual.siguiente;
        }
    }
    public void insertarFinal(Expediente exp) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertarFinal'");
    }
}