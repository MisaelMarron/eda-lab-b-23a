import java.util.*;
import myExceptions.ItemDuplicated;

public class BST<E extends Comparable<E>> {
	private Node<E> root; 
	
	
	public BST() {
        this.root = null;
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void insert(E x) throws ItemDuplicated {
    	this.root = insertNode(x, this.root);
    	}
    
    protected Node<E> insertNode(E x, Node<E> actual) throws ItemDuplicated {
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
	
	
	
	
	

