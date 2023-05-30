public class Nodo<E> {
	private E data;
	private Nodo<E> next;
	
	public Nodo(E data, Nodo<E> next) {
		this.data = data;
		this.next = next;
	}
	public Nodo(E data) {
		this(data, null);
	}
	public E getData() {
		return this.data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public Nodo<E> nextNodo() {
		return this.next;
	}
	public void setNext(Nodo<E> next) {
		this.next = next;
	}
	public String toString() {
		return this.data.toString();
	}
}