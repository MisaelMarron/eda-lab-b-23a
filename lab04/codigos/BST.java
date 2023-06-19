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
    
    public E getMin() throws ExceptionIsEmpty{
    	E minData = findMin().getData();
    	return minData;
    }
    private Node<E> findMin() throws ExceptionIsEmpty{
    	Node<E> current = this.root;
    	if (isEmpty()) 
    		throw new ExceptionIsEmpty("EL ARBOL ESTA VACIO");
    	
    	while (current.getLeft() != null)
    		current = current.getLeft();
    	
    	return current;
    }
    
    public E getMax() throws ExceptionIsEmpty{
    	E maxData = findMax().getData();
    	return maxData;
    }
    private Node<E> findMax() throws ExceptionIsEmpty{
    	Node<E> current = this.root;
    	if (isEmpty()) 
    		throw new ExceptionIsEmpty("EL ARBOL ESTA VACIO");
    	
    	while (current.getRight() != null)
    		current = current.getRight();
    	
    	return current;
    }
    
    public E parent(E data) throws ExceptionIsEmpty, ExceptionNoFound{
    	if (isEmpty())
    		throw new ExceptionIsEmpty("EL ARBOL ESTA VACIO");
    	Node<E> parent = getParent(data,this.root);
    	return parent.getData();
    }
    
    private Node<E> getParent(E data,Node<E> current) throws ExceptionNoFound{
    	if (searchNode(data,current)== null)
    	throw new ExceptionNoFound ("El dato no se encuentra");
    	
    	Node parentNode = null;
    	
        while (current != null) {
            if (current.getData().compareTo(data)==-1) {
                parentNode = current;
                current = current.getLeft();
            } else if (current.getData().compareTo(data)==1) {
                parentNode = current;
                current = current.getRight();
            } else {
                break;
            }
        }
        
        return parentNode;
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
	
	
	
	
	

