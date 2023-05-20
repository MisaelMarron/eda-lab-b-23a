public class Lista<E> {
    private Nodo<E> cabeza;
    private int tamaño;

    private static class Nodo<E> {
        private E elemento;
        private Nodo<E> siguiente;

        public Nodo(E elemento) {
            this.elemento = elemento;
            this.siguiente = null;
        }
    }

    public Lista() {
        cabeza = null;
        tamaño = 0;
    }

    public void agregar(E elemento) {
        Nodo<E> nuevoNodo = new Nodo<>(elemento);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<E> nodoActual = cabeza;
            while (nodoActual.siguiente != null) {
                nodoActual = nodoActual.siguiente;
            }
            nodoActual.siguiente = nuevoNodo;
        }
        tamaño++;
    }

    public void eliminar(E elemento) {
        if (cabeza == null) {
            return;
        }

        if (cabeza.elemento.equals(elemento)) {
            cabeza = cabeza.siguiente;
            tamaño--;
            return;
        }

        Nodo<E> nodoActual = cabeza;
        while (nodoActual.siguiente != null && !nodoActual.siguiente.elemento.equals(elemento)) {
            nodoActual = nodoActual.siguiente;
        }

        if (nodoActual.siguiente != null) {
            nodoActual.siguiente = nodoActual.siguiente.siguiente;
            tamaño--;
        }
    }

    public boolean contiene(E elemento) {
        Nodo<E> nodoActual = cabeza;
        while (nodoActual != null) {
            if (nodoActual.elemento.equals(elemento)) {
                return true;
            }
            nodoActual = nodoActual.siguiente;
        }
        return false;
    }

    public int tamaño() {
        return tamaño;
    }

    public E obtener(int indice) {
        if (indice < 0 || indice >= tamaño) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<E> nodoActual = cabeza;
        for (int i = 0; i < indice; i++) {
            nodoActual = nodoActual.siguiente;
        }
        return nodoActual.elemento;
    }

    public void imprimir() {
        Nodo<E> nodoActual = cabeza;
        while (nodoActual != null) {
            System.out.println(nodoActual.elemento);
            nodoActual = nodoActual.siguiente;
        }
    }
}
