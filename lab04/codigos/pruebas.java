import myExceptions.*;

public class pruebas {

	public static void main(String[] args) {
		try {
            // Crear un árbol BST
            BST<String> bst = new BST<>();

            // Insertar elementos en el árbol
            bst.insert("H");
            bst.insert("O");
            bst.insert("L");
            bst.insert("A");
            bst.insert("G");

            // Mostrar el árbol en orden
            System.out.println("Arbol en orden:");
            bst.displayInOrder();

            // Buscar un elemento en el árbol
            String searchElement = "B";
            try {
                String result = bst.search(searchElement);
                System.out.println("Elemento encontrado: " + result);
            } catch (ExceptionNoFound exception) {
                System.out.println(exception.getMessage());
            }

            // Obtener el valor mínimo del árbol
            try {
                String minValue = bst.getMin();
                System.out.println("Valor minimo: " + minValue);
            } catch (ExceptionIsEmpty exception) {
                System.out.println(exception.getMessage());
            }

            // Obtener el valor máximo del árbol
            try {
                String maxValue = bst.getMax();
                System.out.println("Valor maximo: " + maxValue);
            } catch (ExceptionIsEmpty exception) {
                System.out.println(exception.getMessage());
            }

            // Obtener el padre de un elemento
            String parentElement = "D";
            try {
                String parentValue = bst.parent(parentElement);
                System.out.println("Padre de " + parentElement + ": " + parentValue);
            } catch (ExceptionIsEmpty | ExceptionNoFound exception) {
                System.out.println(exception.getMessage());
            }

            // Obtener el hijo de un elemento
            String sonElement = "A";
            try {
                String sonValue = bst.son(sonElement);
                System.out.println("Hijo de " + sonElement + ": " + sonValue);
            } catch (ExceptionIsEmpty | ExceptionNoFound exception) {
                System.out.println(exception.getMessage());
            }

            // Eliminar un elemento del árbol
            String elementToRemove = "E";
            try {
                bst.remove(elementToRemove);
                System.out.println("Elemento eliminado: " + elementToRemove);
            } catch (ExceptionNoFound exception) {
                System.out.println(exception.getMessage());
            }

            // Mostrar el árbol en orden después de eliminar un elemento
            System.out.println("Arbol en orden despuEs de eliminar " + elementToRemove + ":");
            bst.displayInOrder();

        } catch (ItemDuplicated exception) {
            System.out.println(exception.getMessage());
        }
    }

}
