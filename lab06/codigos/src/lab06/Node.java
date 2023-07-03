package lab06;
import java.util.*;
public class Node<T extends Comparable<T>> {
    private List<T> valores;
    private List<Node<T>> hijos;
    private Node<T> padre;

    public Node(T valor) {
        valores = new ArrayList<>();
        hijos = new ArrayList<>();
        padre = null;
        valores.add(valor);
    }

    public List<T> getValores() {
        return valores;
    }

    public List<Node<T>> getHijos() {
        return hijos;
    }

    public Node<T> getPadre() {
        return padre;
    }

    public void setPadre(Node<T> padre) {
        this.padre = padre;
    }
}