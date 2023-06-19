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
    	
    	Node<E> parentNode = null;
    	
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
    	
    public E son(E data) throws ExceptionIsEmpty, ExceptionNoFound{
    	if (isEmpty())
    		throw new ExceptionIsEmpty("El ARBOL ESTA VACIO");
    	
    	return (getSon(data , root)).getData();
    }
    private Node<E> getSon(E data, Node<E> current) {
    	current= searchNode(data,current);
    	
    	if (current.getRight() == null)
    		return current.getLeft();
    	else 
    		return current.getRight();
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
    public void remove(E x) throws ExceptionNoFound {
    	this.root = removeNode(x, this.root);
    	}
    	protected Node<E> removeNode(E x, Node<E> actual) throws ExceptionNoFound {
    	Node<E> res = actual;
    	if (actual == null) 
    		throw new ExceptionNoFound(x + "no esta");
    	int resC = actual.getData().compareTo(x);
    	
    	if (resC < 0) 
    		res.setRight(removeNode(x, actual.getRight()));
    	else if (resC > 0) 
    		res.setLeft(removeNode(x, actual.getLeft()));
    	else if(actual.getLeft() != null && actual.getRight() != null) {
    		res.setData(actual.getRight().getData());
    		res.setRight(minRemove(actual.getRight()));
    	} else {
    		res = (actual.getLeft() != null) ? actual.getLeft() : actual.getRight();
    	}
    		return res;
    	}
    	public E minRemove() throws ExceptionIsEmpty {
    		E min = getMin(); 
    		this.root = minRemove(this.root);
    		
    		return min;
    		}
    		
    	protected Node<E> minRemove(Node<E> actual) {
    		if (actual.getLeft() != null) { 
    		actual.setLeft(minRemove(actual.getLeft())); 
    		}
    		else { 
    		actual = actual.getRight();
    		}
    		return actual;
    		}
    
    	public void displayInOrder() {
    	    displayInOrder(root);
    	    System.out.println();
    	}

    	private void displayInOrder(Node<E> currentNode) {
    	    if (currentNode != null) {
    	        displayInOrder(currentNode.getLeft());
    	        System.out.print(currentNode.getData() + " ");
    	        displayInOrder(currentNode.getRight());
    	    }
    	}
    	 public void input(String word) throws ItemDuplicated {
    	        if (word == null || word.isEmpty()) {
    	            throw new IllegalArgumentException("La palabra no puede ser nula o vacía.");
    	        }

    	        for (int i = 0; i < word.length(); i++) {
    	            char letter = word.charAt(i);
    	            int asciiValue = (int) letter;
    	            insert((E) Character.toString(letter));
    	        }
    	    }
    
    
    
    
    
}
	
	
	
	
	

