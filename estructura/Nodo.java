package estructura;

class Nodo<T> {
    T dato;
    Nodo<T> siguiente;
    public Nodo(T dato) { this.dato = dato; }
}