import java.util.List;
import java.util.Scanner;


public class Test {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVL<Character> arbol= new AVL<>();
        System.out.println("Ponga el Input");
        String Prueba = scanner.nextLine();
        arbol = imput(arbol, Prueba);  
        arbol.inOrderTraversal();
        int opcion = -1;
        char caracter;
        List<Character> hijos = null;
        System.setProperty("org.graphstream.ui", "graphstream.ui.swing");
        arbol.imprimirArbol();
        
        while(opcion != 0){
            System.out.println("\n0 para salir y mostrar por ultima vez ");
            System.out.println("1 para buscar el caracter");
            System.out.println("2 para buscar el minimo");
            System.out.println("3 para buscar el maximo");
            System.out.println("4 para buscar el padre");
            System.out.println("5 para buscar el hijo");
            System.out.println("6 para insertar");
            System.out.println("7 para eliminar");
            opcion = scanner.nextInt();
            switch(opcion){
                case 0:
                    System.out.println("Terminar programa");
                    arbol.cerrarArbol();
                    break;
                case 1:
                    System.out.println("Ingresa un carácter: ");
                    caracter = scanner.next().charAt(0);
                    System.out.println("El caracter "+caracter+": "+arbol.search(caracter));
                    break;
                case 2:
                    System.out.println("El valor mínimo del árbol es: " + arbol.getMin());
                    break;
                case 3:
                    System.out.println("El valor mínimo del árbol es: " + arbol.getMax());
                    break;
                case 4:
                    System.out.println("Ingresa un carácter: ");
                    caracter = scanner.next().charAt(0);
                    System.out.println("El caracter "+caracter+": "+arbol.parent(caracter));
                    break;
                case 5:
                    System.out.println("Ingresa un carácter: ");
                    caracter = scanner.next().charAt(0);
                    hijos = arbol.children(caracter);
                    System.out.println("Los hijo de "+caracter+" :"+hijos);
                    break;
                case 6:
                    System.out.println("Ingresa un carácter: ");
                    caracter = scanner.next().charAt(0);
                    arbol.insert(caracter);
                    System.out.println("Fue insertado el caracter "+caracter);
                    break;
                case 7:
                    System.out.println("Ingresa un carácter: ");
                    caracter = scanner.next().charAt(0);
                    arbol.remove(caracter);
                    System.out.println("Fue eliminado el caracter "+caracter);
                    break; 
                default:
                    System.out.println("Error de opcion");
                    break;     
            }
            if(opcion != 0)
                arbol.imprimirArbol();
        }
        scanner.close();
    }
    //Para covertirlo en arbol binario
    public static AVL<Character> imput(AVL<Character> arbolBinario, String Palabra){
        for (int i = 0; i < Palabra.length(); i++) {
            arbolBinario.insert(Palabra.charAt(i));
        }
        return arbolBinario;
    }
    
}
