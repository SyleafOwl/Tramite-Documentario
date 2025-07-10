package estructura;

class NodoDoble<T> {
    T dato;
    NodoDoble<T> siguiente, anterior;
    public NodoDoble(T dato) { this.dato = dato; }
}