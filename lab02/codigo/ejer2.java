import java.util.*;
public class ejer2 {
	public static void main(String[] args) {
		
		//Supongamos la siguiente matriz
		int []matriz = {1,2,3,4,5,6}; 
		Scanner scan = new Scanner(System.in);
		System.out.println("Ingrese desde donde quiere rotar su matriz.");
		int pos = scan.nextInt();
		
		rotarIzquierda(matriz,pos);
		
	}
	public static void rotarIzquierda(int [] matriz , int x) {
		int [] aux = new int[matriz.length];
		
		for(int i=0 ; i<matriz.length ; i++)
			aux[i]=matriz[i];
		
		for( int constante=x, i=0; constante<matriz.length; constante++,i++ )
			matriz[i] = aux[constante];
		
		for( int constant =x, i=0; i<constant ; i++ ) {
			matriz[matriz.length-x] = aux[i];
			x--;
		}
		
		
		for (int p : matriz)
			System.out.print(" "+p);
	}
}
