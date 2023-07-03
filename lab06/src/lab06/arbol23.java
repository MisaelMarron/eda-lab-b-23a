package lab06;

class arbol23<T extends Comparable<T>> {
	private Nodo<T> root;
	
	private class Nodo<T> {
		private T data;
		private T data2;
		
		private Nodo<T> padre;
		private Nodo<T> hijo [] = new Nodo[3] ;
		
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
	
	private void insertDato(Nodo<T> current , T dato) {
		
		//dos espacios
		if (current == null)
			current = new Nodo<T>(dato);
		
		//un espacio en el nodo
		else if (current.data2 == null) {
			if (dato.compareTo(current.data)==1)
				current.data2= dato;
			else {
				current.data2 = current.data;
				current.data = dato;
			}
		}
			
		if (current.data != null && current.data2 != null) {
			if (dato.compareTo(current.data)== 1 && dato.compareTo(current.data2)== -1)
				current = ingresarEntre(current , dato);
			
			else if (dato.compareTo(current.data2) == 1) 
				current = ingresarMayor(current,dato);
			
			else if (dato.compareTo(current.data)== -1)
				current = ingresarMenor(current , dato);
			
			
			
		}
			
		
			
	}
	
	//metodos ayuda
	public Nodo<T> ingresarEntre(Nodo<T> current , T dato){
		Nodo<T> aux = new Nodo<T>(dato);
		
		aux.hijo[0] = new Nodo<T>(current.data);
		aux.hijo[1] = new Nodo<T>(current.data2);
		
		return aux;
	}
	public Nodo<T> ingresarMenor(Nodo<T> current , T dato) {
		Nodo<T> aux = new Nodo<T>(current.data) ;
		
		aux.hijo[0] = new Nodo<T>(dato);
		aux.hijo[1] = new Nodo<T>(current.data2);
		
		return aux;
	}
	public Nodo<T> ingresarMayor(Nodo<T> current , T dato) {
		Nodo<T> aux = new Nodo<T>(current.data2) ;
		
		aux.hijo[0] = new Nodo<T>(current.data);
		aux.hijo[1] = new Nodo<T>(dato);
		
		return aux;
	}
	
}