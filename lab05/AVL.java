import java.util.*;
import org.graphstream.graph.*;
import org.graphstream.graph.implementations.*;
import org.graphstream.ui.view.Viewer;


public class AVL<T extends Comparable<T>> {
    private Nodo<T> root;
    
    public AVL() {
        root = null;
    }
    
    
    private int nivel(Nodo<T> nodo) {
        if (nodo== null)
            return 0;
        return nodo.getNivel();
    }
    
    
    private int balance(Nodo<T> nodo) {
        if (nodo == null)
            return 0;
        return nivel(nodo.getLeft())-nivel(nodo.getRight());
    }

    
    public void insert(T key) {
        this.root = insertNode(this.root, key);
    }
    
    private Nodo<T> insertNode(Nodo<T> node, T key) {
        if (node == null)
            return new Nodo<T>(key);
        int cmp = key.compareTo(node.getClave());
        if (cmp < 0) {
            node.setLeft(insertNode(node.getLeft(), key));
            node.setRight(insertNode(node.getRight(), key));
        }
        else 
            node.setCount(node.getCount()+1);
        
        node.setNivel(1 + Math.max(node.getLeft().getNivel(),node.getRight().getNivel()));
        int balance = balance(node);
        
        if (balance > 1 && key.compareTo(node.getLeft().getClave()) < 0)
            return rotateRight(node);
        if (balance < -1 && key.compareTo(node.getRight().getClave()) > 0)
            return rotateLeft(node);
       if (balance > 1 && key.compareTo(node.getLeft().getClave()) > 0) {
            node.setLeft(rotateLeft(node.left));
            return rotateRight(node);
        }
        if (balance < -1 && key.compareTo(node.getRight().getClave()) < 0) {
            node.setRight(rotateRight(node.getRight()));
            return rotateLeft(node);
        }
        return node;
    }
    private Nodo<T> rotateRight(Nodo<T> y) {
        Nodo<T> x = y.getLeft();
        Nodo<T> T2 = x.getRight();
        x.setRight(y);
        y.setLeft(T2);
        y.setNivel(Math.max(y.getLeft().getNivel(),y.getRight().getNivel()) + 1);
        x.setNivel(Math.max(x.getLeft().getNivel(),x.getRight().getNivel()) + 1);
        return x;
    }
   
    private Nodo<T> rotateLeft(Nodo<T> x) {
        Nodo<T> y = x.getRight();
        Nodo<T> T2 = y.getLeft();
        y.setLeft(x);
        x.setRight(T2);
        x.setNivel(Math.max(x.getLeft().getNivel(),x.getRight().getNivel()) + 1);
        y.setNivel(Math.max(y.getLeft().getNivel(),y.getRight().getNivel()) + 1);
        return y;
    }
    

}