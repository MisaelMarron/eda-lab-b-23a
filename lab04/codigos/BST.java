import java.util.*;
import myExceptions.*;

public class BST<E extends Comparable<E>> {
	private Node<E> root; 
	
	
	public BST() {
        this.root = null;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public E search(E x) throws ExceptionNoFound {
    	Node<E> res = searchNode(x, root);
    	
    	if(res == null)
    		throw new ExceptionNoFound ("El dato "+ x + "no se encuentra");
    		return res.getData();
    	}
    
    private Node<E> searchNode(E x, Node<E> n){
    	if (n == null) 
    		return null;
    	else {
    		int resC = n.getData().compareTo(x);
    		
    		if (resC < 0) 
    			return searchNode(x, n.getRight());
    		else if (resC > 0) 
    			return searchNode(x, n.getLeft());
    	else 
    		return n;
    	}
    }
    
    private Node<E> getMin() throws ExceptionIsEmpty{
    	Node<E> current = this.root;
    	if (isEmpty()) 
    		throw new ExceptionIsEmpty("EL ARBOL ESTA VACIO");
    	
    	while (current.getLeft() != null)
    		current = current.getLeft();
    	
    	return current;
    }
    
    public void insert(E x) throws ItemDuplicated {
    	this.root = insertNode(x, this.root);
    	}

    private Node<E> insertNode(E x, Node<E> actual) throws ItemDuplicated {
    	Node<E> res = actual;
    	if (actual == null) {res = new Node<E>(x);}
    		
    	else {
    		int resC = actual.getData().compareTo(x);
    		if (resC == 0 ) throw new ItemDuplicated(x + "esta duplicado!");
    		if (resC < 0) 
    			res.setRight( insertNode(x, actual.getRight() ));
    		else
    			res.setLeft(insertNode(x, actual.getLeft()));

    	}
    	return res;
    }
}
	
	
	
	
	

