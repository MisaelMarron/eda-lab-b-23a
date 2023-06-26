public class Nodo<T extends Comparable<T>> {
    T clave;
    int count,nivel;
    Nodo<T> left, right;

    Nodo(T value) {
        this.clave = value;
        this.count = 1;
        this.nivel = 1;
    }
    public T getClave() {
        return clave;
    }
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getNivel() {
		return nivel;
	}
	public void setNivel(int nivel) {
		this.nivel = nivel;
	}
	public Nodo<T> getLeft() {
		return left;
	}
	public void setLeft(Nodo<T> left) {
		this.left = left;
	}
	public Nodo<T> getRight() {
		return right;
	}
	public void setRight(Nodo<T> right) {
		this.right = right;
	}
	public void setClave(T clave) {
		this.clave = clave;
	
}
}
