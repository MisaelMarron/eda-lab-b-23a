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
    
    public boolean search(T key) {
        return searchKey(this.root, key);
    }
    
    private boolean searchKey(Nodo<T> node, T key) {
        if (node == null)
            return false;

        int cmp = key.compareTo(node.getClave());

        if (cmp == 0)
            return true;

        if (cmp < 0)
            return searchKey(node.getLeft(), key);
        else
            return searchKey(node.getRight(), key);
    }
   
    public T getMin() {
        if (root == null)
            throw new NoSuchElementException("El arbol esta vacio");
        return findMin(root).getClave();
    }
    
    private Nodo<T> findMin(Nodo<T> node) {
        while (node.getLeft() != null)
            node = node.getLeft();
        return node;
    }
    
    public T getMax() {
        if (root == null)
            throw new NoSuchElementException("El arbol esta vacio");
        return findMax(root).getClave();
    }
     
    private Nodo<T> findMax(Nodo<T> node) {
        while (node.getRight() != null)
            node = node.getRight();
        return node;
    }
  
    public T parent(T key) {
        Nodo<T> parent = findParent(this.root, key);
        if (parent == null)
            throw new NoSuchElementException("La clave no existe o es la raíz");
        return parent.getClave();
    }
    
    private Nodo<T> findParent(Nodo<T> node, T key) {
        if (node == null || node.getClave().equals(key))
            return null;

        int cmpLeft = (node.getLeft() != null) ? key.compareTo(node.getLeft().getClave()) : -1;
        int cmpRight = (node.getRight() != null) ? key.compareTo(node.getRight().getClave()) : 1;

        if (cmpLeft == 0 || cmpRight == 0)
            return node;

        if (cmpLeft < 0)
            return findParent(node.getLeft(), key);
        else
            return findParent(node.getRight(), key);
    }
    
    public List<T> children(T key) {
    Nodo<T> node = findNode(this.root, key);
    if (node == null)
        throw new NoSuchElementException("La clave no existe");

    List<T> children = new ArrayList<>();
    if (node.getLeft() != null)
        children.add(node.getLeft().getClave());
    if (node.getRight() != null)
        children.add(node.getRight().getClave());

    return children;
    }
    
    public boolean isLeaf(T key) {
        Nodo<T> node = findNode(this.root, key);
        if (node == null)
            throw new NoSuchElementException("La clave no existe");
        return (node.getLeft() == null && node.getRight() == null);
    }
    
    private Nodo<T> findNode(Nodo<T> node, T key) {
        if (node == null || node.getClave().equals(key))
            return node;

        int cmp = key.compareTo(node.getClave());

        if (cmp < 0)
            return findNode(node.getLeft(), key);
        else
            return findNode(node.getRight(), key);
    }
    
    public void inOrderTraversal() {
        inOrderTraversal(this.root);
    }
     
    private void inOrderTraversal(Nodo<T> node) {
        if (node != null) {
            inOrderTraversal(node.getLeft());
            for (int i = 0; i < node.getCount(); i++) {
                System.out.print(node.getClave() + " ");
            }
            inOrderTraversal(node.getRight());
        }
    }
  
}