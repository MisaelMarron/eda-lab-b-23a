import myExceptions.ItemDuplicated;

public class pruebas {

	public static void main(String[] args) {
		 BST<String> bst = new BST<>();
	        try {
	            bst.input("HAELO");
	            System.out.println("arbol construido:");
	            bst.displayInOrder();
	        } catch (ItemDuplicated e) {
	            System.out.println(e.getMessage());
	        }

	}

}
