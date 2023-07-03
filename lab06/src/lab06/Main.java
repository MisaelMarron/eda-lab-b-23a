package lab06;
public class Main {
    public static void main(String[] args) {
        arbol23<Integer> arbol = new arbol23<>();

        // Insertar elementos
        arbol.insert(10);
        arbol.insert(5);
        arbol.insert(15);
        arbol.insert(3);
        arbol.insert(7);
        arbol.insert(12);
        arbol.insert(20);
        arbol.insert(9);

        // Mostrar el árbol
        System.out.println("Árbol 2-3:");
        arbol.display();

        // Eliminar un elemento
        arbol.delete(7);

        // Mostrar el árbol después de eliminar
        System.out.println("\nÁrbol 2-3 después de eliminar:");
        arbol.display();
    }
}