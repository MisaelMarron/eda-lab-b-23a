package lab06;

class arbol23<T extends Comparable<T>> {
	private Nodo<T> root;
	
	private class Nodo<T> {
		private T data;
		private T data2;
		
		private Nodo<T> padre;
		private T hijo [];
		
		public Nodo(T data, Nodo<T> padre) {
			this.data = data;
			this.padre = padre;
		}
		public Nodo(T data) {
			this(data, null);
		}
		public T getData() {
			return this.data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public String toString() {
			return this.data.toString();
		}
	}
	

	
	public arbol23() {
		this.root = null;
	}
	
	public arbol23(T dato) {
		this.root.data = dato;
	}
	public arbol23(T dato , T dato2) {
		this.root.data = dato;
		this.root.data2 = dato2;
	}
	
	
	public void insert(T data) {
		insertDato(this.root , data);
	}
	
	private void insertDato(Nodo<T> current , T data) {
		if (current == null)
			current = new Nodo<T>(data);
		else if (current.data2 == null) 
			current.data2 = data;
		
			
		
			
	}
	
	
	
}