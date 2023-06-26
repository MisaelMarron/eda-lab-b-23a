import java.util.*;

import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;


class Nodo<T extends Comparable<T>> {
    T Clave;
    int cont;
    int Nivel;
    Nodo<T> izq, der;

    Nodo(T value) {
        Clave = value;
        cont = 1;
        Nivel = 1;
    }
    public T getClave() {
        return Clave;
    }
}


public class AVL<T extends Comparable<T>> {
    private Nodo<T> root;
    
    public AVL() {
        root = null;
    }
    
    private int Nivel(Nodo<T> nodo) {
        if (nodo== null)
            return 0;
        return nodo.Nivel;
    }
    
    private int balanceFactor(Nodo<T> nodo) {
        if (nodo == null)
            return 0;
        return Nivel(nodo.izq) - Nivel(nodo.der);
    }

    
    public void insert(T Clave) {
        root = insertNode(root, Clave);
    }
   
    private Nodo<T> insertNode(Nodo<T> node, T Clave) {
        if (node == null)
            return new Nodo<>(Clave);
        int cmp = Clave.compareTo(node.Clave);
        if (cmp < 0)
            node.izq = insertNode(node.izq, Clave);
        else if (cmp > 0)
            node.der = insertNode(node.der, Clave);
        else
            node.cont++;
        node.Nivel = 1 + Math.max(Nivel(node.izq), Nivel(node.der));
        int balance = balanceFactor(node);
        if (balance > 1 && Clave.compareTo(node.izq.Clave) < 0)
            return rotateder(node);
        if (balance < -1 && Clave.compareTo(node.der.Clave) > 0)
            return rotateizq(node);
       if (balance > 1 && Clave.compareTo(node.izq.Clave) > 0) {
            node.izq = rotateizq(node.izq);
            return rotateder(node);
        }
        if (balance < -1 && Clave.compareTo(node.der.Clave) < 0) {
            node.der = rotateder(node.der);
            return rotateizq(node);
        }
        return node;
    }
    
    public void remove(T Clave) {
        root = removeNode(root, Clave);
    }
     
    private Nodo<T> removeNode(Nodo<T> root, T Clave) {
        if (root == null)
            return root;
        int cmp = Clave.compareTo(root.Clave);
        if (cmp < 0)
            root.izq = removeNode(root.izq, Clave);
        else if (cmp > 0)
            root.der = removeNode(root.der, Clave);
        else {
            if (root.cont > 1) {
                root.cont--;
                return root;
            }
            if ((root.izq == null) || (root.der == null)) {
                Nodo<T> temp = null;
                if (temp == root.izq)
                    temp = root.der;
                else
                    temp = root.izq;

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Nodo<T> temp = minValueNode(root.der);
                root.Clave = temp.Clave;
                root.cont = temp.cont;
                temp.cont = 1;
                root.der = removeNode(root.der, temp.Clave);
            }
        }
        if (root == null)
            return root;
        root.Nivel = 1 + Math.max(Nivel(root.izq), Nivel(root.der));
        int balance = balanceFactor(root);
        if (balance > 1 && balanceFactor(root.izq) >= 0)
            return rotateder(root);
        if (balance > 1 && balanceFactor(root.izq) < 0) {
            root.izq = rotateizq(root.izq);
            return rotateder(root);
        }
        if (balance < -1 && balanceFactor(root.der) <= 0)
            return rotateizq(root);
        if (balance < -1 && balanceFactor(root.der) > 0) {
            root.der = rotateder(root.der);
            return rotateizq(root);
        }
        return root;
    }
       
    private Nodo<T> rotateder(Nodo<T> y) {
        Nodo<T> x = y.izq;
        Nodo<T> T2 = x.der;
        x.der = y;
        y.izq = T2;
        y.Nivel = Math.max(Nivel(y.izq), Nivel(y.der)) + 1;
        x.Nivel = Math.max(Nivel(x.izq), Nivel(x.der)) + 1;
        return x;
    }
   
    private Nodo<T> rotateizq(Nodo<T> x) {
        Nodo<T> y = x.der;
        Nodo<T> T2 = y.izq;
        y.izq = x;
        x.der = T2;
        x.Nivel = Math.max(Nivel(x.izq), Nivel(x.der)) + 1;
        y.Nivel = Math.max(Nivel(y.izq), Nivel(y.der)) + 1;
        return y;
    }
    
    private Nodo<T> minValueNode(Nodo<T> node) {
        Nodo<T> current = node;
        while (current.izq != null)
            current = current.izq;
        return current;
    }
    
    public boolean search(T Clave) {
        return searchClave(root, Clave);
    }
    
    private boolean searchClave(Nodo<T> node, T Clave) {
        if (node == null)
            return false;

        int cmp = Clave.compareTo(node.Clave);

        if (cmp == 0)
            return true;

        if (cmp < 0)
            return searchClave(node.izq, Clave);
        else
            return searchClave(node.der, Clave);
    }
    
    public T getMin() {
        if (root == null)
            throw new NoSuchElementException("El árbol está vacío");
        return findMin(root).Clave;
    }

    private Nodo<T> findMin(Nodo<T> node) {
        while (node.izq != null)
            node = node.izq;
        return node;
    }

    public T getMax() {
        if (root == null)
            throw new NoSuchElementException("El árbol está vacío");
        return findMax(root).Clave;
    }

    private Nodo<T> findMax(Nodo<T> node) {
        while (node.der != null)
            node = node.der;
        return node;
    }

    public T parent(T Clave) {
        Nodo<T> parent = findParent(root, Clave);
        if (parent == null)
            throw new NoSuchElementException("La clave no existe o es la raíz");
        return parent.Clave;
    }

    private Nodo<T> findParent(Nodo<T> node, T Clave) {
        if (node == null || node.Clave.equals(Clave))
            return null;

        int cmpizq = (node.izq != null) ? Clave.compareTo(node.izq.Clave) : -1;
        int cmpder = (node.der != null) ? Clave.compareTo(node.der.Clave) : 1;

        if (cmpizq == 0 || cmpder == 0)
            return node;

        if (cmpizq < 0)
            return findParent(node.izq, Clave);
        else
            return findParent(node.der, Clave);
    }

    public List<T> children(T Clave) {
    Nodo<T> node = findNode(root, Clave);
    if (node == null)
        throw new NoSuchElementException("La clave no existe");

    List<T> children = new ArrayList<>();
    if (node.izq != null)
        children.add(node.izq.Clave);
    if (node.der != null)
        children.add(node.der.Clave);

    return children;
    }

    public boolean isLeaf(T Clave) {
        Nodo<T> node = findNode(root, Clave);
        if (node == null)
            throw new NoSuchElementException("La clave no existe");
        return (node.izq == null && node.der == null);
    }

    private Nodo<T> findNode(Nodo<T> node, T Clave) {
        if (node == null || node.Clave.equals(Clave))
            return node;

        int cmp = Clave.compareTo(node.Clave);

        if (cmp < 0)
            return findNode(node.izq, Clave);
        else
            return findNode(node.der, Clave);
    }

    public void inOrderTraversal() {
        inOrderTraversal(root);
    }

    private void inOrderTraversal(Nodo<T> node) {
        if (node != null) {
            inOrderTraversal(node.izq);
            for (int i = 0; i < node.cont; i++) {
                System.out.print(node.Clave + " ");
            }
            inOrderTraversal(node.der);
        }
    }
    

    public void imprimirArbol() {
    Graph graph = new SingleGraph("Árbol AVL");
    agregarNodos(graph, root, 0, 0);
    agregarAristas(graph, root);
    System.setProperty("org.graphstream.ui", "swing");
    Viewer viewer = graph.display();
    viewer.disableAutoLayout();
    viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.CLOSE_VIEWER);


}
public void cerrarArbol(){
     Graph graph = new SingleGraph("Árbol AVL");
    agregarNodos(graph, root, 0, 0);
    agregarAristas(graph, root);
    System.setProperty("org.graphstream.ui", "swing");
    Viewer viewer = graph.display();  
    viewer.disableAutoLayout();
    viewer.setCloseFramePolicy(Viewer.CloseFramePolicy.EXIT);
        
}

 private void agregarNodos(Graph graph, Nodo<T> nodo, double x, double y) {
    if (nodo != null) {
        Node node = graph.addNode(nodo.Clave.toString());
        node.setAttribute("ui.label", nodo.Clave.toString());
        node.setAttribute("ui.style", "text-size: 30px; text-alignment: center; text-background-mode: rounded-box; text-background-color: #ffffffbb; text-color: blue; ");
        node.setAttribute("xy", x, y);
        agregarNodos(graph, nodo.izq, x - 4.0, y - 4.0); 
        agregarNodos(graph, nodo.der, x + 4.0, y - 4.0);
    }
}

private void agregarAristas(Graph graph, Nodo<T> nodo) {
    if (nodo != null) {
        if (nodo.izq != null) {
            Edge edge = graph.addEdge(nodo.Clave + "-" + nodo.izq.Clave, nodo.Clave.toString(), nodo.izq.Clave.toString());
            edge.setAttribute("ui.style", "fill-color: black;");
        }
        if (nodo.der != null) {
            Edge edge = graph.addEdge(nodo.Clave + "-" + nodo.der.Clave, nodo.Clave.toString(), nodo.der.Clave.toString());
            edge.setAttribute("ui.style", "fill-color: black;");
        }

        agregarAristas(graph, nodo.izq);
        agregarAristas(graph, nodo.der);
    }
}

    
}