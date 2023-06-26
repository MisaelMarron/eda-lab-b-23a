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
            node.setLeft(rotateLeft(node.getLeft()));
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
 
    public void remove(T key) {
        this.root = removeNode(this.root, key);
    }
    
    private Nodo<T> removeNode(Nodo<T> root, T key) {
        if (root == null)
            return root;
        
        int cmp = key.compareTo(root.getClave());
        if (cmp < 0)
            root.setLeft(removeNode(root.getLeft(), key));
        else if (cmp > 0)
            root.setRight(removeNode(root.getRight(), key));
        else {
            if (root.getCount() > 1) {
                root.setCount(root.getCount()-1);
                return root;
            }
            if ((root.getLeft() == null) || (root.getRight() == null)) {
                Nodo<T> temp = null;
                if (temp == root.getLeft())
                    temp = root.getRight();
                else
                    temp = root.getLeft();

                if (temp == null) {
                    temp = root;
                    root = null;
                } else
                    root = temp;
            } else {
                Nodo<T> temp = minValueNode(root.getRight());
                root.setClave(temp.getClave());
                root.setCount(temp.getCount());
                temp.setCount(1);
                root.setRight(removeNode(root.getRight(), temp.getClave()));
            }
        }
        
        if (root == null)
            return root;
        
        root.setNivel(1 + Math.max(root.getLeft().getNivel(), root.getRight().getNivel()));
        int balance = balance(root);
        if (balance > 1 && balance(root.getLeft()) >= 0)
            return rotateRight(root);
        if (balance > 1 && balance(root.getLeft()) < 0) {
            root.setLeft(rotateLeft(root.getLeft()));
            return rotateRight(root);
        }
        if (balance < -1 && balance(root.getRight()) <= 0)
            return rotateLeft(root);
        if (balance < -1 && balance(root.getRight()) > 0) {
            root.setRight(rotateRight(root.getRight()));
            return rotateLeft(root);
        }
        return root;
    }
    
    private Nodo<T> minValueNode(Nodo<T> node) {
        Nodo<T> current = node ;
        while (current.getLeft() != null)
            current = current.getLeft();
        return current;
    }

}