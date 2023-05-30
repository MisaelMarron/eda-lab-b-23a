public interface Lista<E> {
	
	void add(E x);
	boolean contains(E x);
	void remove(E x);	
	boolean isEmpty();
	int size();
}
