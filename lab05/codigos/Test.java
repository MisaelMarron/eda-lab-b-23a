import java.util.List;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVL<Character> arbol= new AVL<>();

        System.out.println("Ingresa tu texto");
        String Prueba = scanner.nextLine();
        
        arbol = imput(arbol, Prueba);  
        arbol.inOrderTraversal();
        
        char caracter;
        List<Character> childs = null;
        
        System.setProperty("org.graphstream.ui", "graphstream.ui.swing");
        arbol.imprimirArbol();
        
       arbol.insert('a');
       arbol.insert('g');
       arbol.imprimirArbol();
    }
    
    public static AVL<Character> imput(AVL<Character> arbolBinario, String Palabra){
        for (int i = 0; i < Palabra.length(); i++) {
            arbolBinario.insert(Palabra.charAt(i));
        }
        return arbolBinario;
    }
    
}
