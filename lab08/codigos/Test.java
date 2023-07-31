import java.util.*;
public class Test { 
    // Ejemplo de uso
    public static void main(String[] args){
    	Scanner scan = new Scanner(System.in);
    	HuffmanTree arbol = new HuffmanTree();
    	
        System.out.println("Ingrese su texto: ");
        String text = scan.nextLine().toLowerCase();
        arbol.buildHuffmanTree(text);
        System.setProperty("org.graphstream.ui", "graphstream.ui.swing");
        arbol.imprimirArbol();
               
    }
}