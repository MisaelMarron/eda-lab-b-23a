import java.util.*;
public class ejer3 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Ingrese su base");
        int altura= scan.nextInt();

        trianguloRecursivo(altura);
    }

    public static void trianguloRecursivo(int altura) {
        if (altura <= 0) {
            return;
        }

        trianguloRecursivo(altura - 1);

        for (int i = 0; i < altura; i++) {
            System.out.print("*");
        }

        System.out.println(); 
    }
}








