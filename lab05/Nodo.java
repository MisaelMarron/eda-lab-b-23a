public class Nodo<T extends Comparable<T>> {
    T clave;
    int count,nivel;
    Nodo<T> left, right;

    Nodo(T value) {
        this.clave = value;
        this.count = 1;
        this.nivel = 1;
    }
    public T getClave() {
        return clave;
    }
}
