public class ListaUsar<E> implements Lista<E>{
	private Nodo<E> root;
	private int count;

    public ListaUsar() {
		this.root = null;
		this.count = 0;
	}
	
	public boolean isEmpty() {
		return this.root == null;
	}
	
	public int size() {
		return this.count;
	}
	
	public void insertFirst(E x) {
		this.root = new Nodo<E>(x, this.root);
		this.count ++;
	}
	
	public void add(E x) {
		if (isEmpty())
			insertFirst(x);
		else {
			Nodo<E> aux = this.root;
			while ( aux.nextNodo() != null)
				aux = aux.nextNodo();
			aux.setNext(new Nodo<E>(x));
			this.count ++;
		}
	}
	
	public boolean contains(E x) { 
		Nodo<E> aux = this.root;
		for(; aux != null && !aux.getData().equals(x); aux = aux.nextNodo());
		return aux != null;
	}
	
	public void remove(E x) {
		if (!isEmpty()) {
			if (this.root.getData().equals(x)) {
				this.root = this.root.nextNodo();
				this.count --;
			}
			else {
				Nodo<E> aux = this.root;
				while(aux.nextNodo() !=  null && !aux.nextNodo().getData().equals(x))
					aux = aux.nextNodo();
				if (aux.nextNodo() != null) {
					aux.setNext(aux.nextNodo().nextNodo());
					this.count --;
				}
			}
		}
	}
	
	public String toString() {
		String str = "";
		for(Nodo<E> aux = this.root; aux != null; aux = aux.nextNodo())
			str += aux.toString() + ", ";
		return str;
	}
}
